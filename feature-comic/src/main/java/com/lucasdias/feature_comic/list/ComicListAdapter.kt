package com.lucasdias.feature_comic.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasdias.domain.model.ComicSummary
import com.lucasdias.feature_comic.R
import com.lucasdias.feature_comic.databinding.ComicListItemBinding
import com.lucasdias.ui_components.card.model.CardProperties
import com.lucasdias.ui_components.card.model.CardThumbnailProperties

class ComicListAdapter(private val navigateToComicDetailAction: (Int) -> Unit) : RecyclerView.Adapter<ComicListAdapter.ViewHolder>() {

    private val comicList = mutableListOf<ComicSummary>()

    fun updateComicList(comicSummaries: List<ComicSummary>) {
        this.comicList.addAll(comicSummaries)
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
            holder.bind(comicSummary = comicList[position])
        }
    }

    class ViewHolder(
        private val itemBinding: ComicListItemBinding,
        private val navigateToComicDetailAction: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(comicSummary: ComicSummary) {
            val cardProperties = comicSummary.getCardProperties()
            itemBinding.layoutComicListItem.applyProperties(cardProperties)
        }

        private fun ComicSummary.getCardProperties(): CardProperties {

            val cardThumbnailProperties = CardThumbnailProperties(
                url = thumbnail?.getUrl(),
                placeHolder = R.drawable.thumbnail_place_holder
            )

            return CardProperties(id, title, cardThumbnailProperties) { navigateToComicDetailAction(id) }
        }
    }
}
