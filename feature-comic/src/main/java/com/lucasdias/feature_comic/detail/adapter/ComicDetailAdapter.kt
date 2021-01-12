package com.lucasdias.feature_comic.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasdias.feature_comic.databinding.ComicDetailSectionWithListListItemBinding

class ComicDetailAdapter : RecyclerView.Adapter<ComicDetailAdapter.ViewHolder>() {

    private val list = mutableListOf<String>()

    fun updateList(list: List<String>?) {
        if (list == null) return

        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ComicDetailSectionWithListListItemBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list.isNotEmpty()) {
            holder.bind(text = list[position])
        }
    }

    class ViewHolder(
        private val itemBinding: ComicDetailSectionWithListListItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(text: String) {
            itemBinding.textComicDetailListItem.text = text
        }
    }
}
