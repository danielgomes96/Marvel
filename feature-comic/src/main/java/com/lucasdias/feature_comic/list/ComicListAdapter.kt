package com.lucasdias.feature_comic.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasdias.domain.model.Comic
import com.lucasdias.feature_comic.R
import com.lucasdias.feature_comic.databinding.ComicListItemBinding
import com.lucasdias.ui_components.card.model.CardProperties
import com.lucasdias.ui_components.card.model.CardThumbnailProperties

class ComicListAdapter(private val navigateToComicDetailAction: (Int?) -> Unit) : RecyclerView.Adapter<ComicListAdapter.ViewHolder>() {

    private val comicList = mutableListOf<Comic>()

    fun updateComicList(comics: List<Comic>) {
        this.comicList.addAll(comics)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ComicListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(itemBinding, navigateToComicDetailAction)
    }

    override fun getItemCount(): Int = comicList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (comicList.isNotEmpty()) {
            holder.bind(comic = comicList[position])
        }
    }

    class ViewHolder(
        private val itemBinding: ComicListItemBinding,
        private val navigateToComicDetailAction: (Int?) -> Unit
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(comic: Comic) {
            val cardProperties = comic.getCardProperties()
            itemBinding.layoutComicListItem.applyProperties(cardProperties)
        }

        private fun Comic.getCardProperties(): CardProperties {

            val cardThumbnailProperties = CardThumbnailProperties(
                url = thumbnail?.getUrl(),
                placeHolder = R.drawable.thumbnail_place_holder
            )

            return CardProperties(id, title, cardThumbnailProperties) { navigateToComicDetailAction(id) }
        }
    }
}
