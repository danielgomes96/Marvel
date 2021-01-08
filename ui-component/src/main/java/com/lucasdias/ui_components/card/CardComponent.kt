package com.lucasdias.ui_components.card

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.lucasdias.extensions.gone
import com.lucasdias.extensions.loadImage
import com.lucasdias.extensions.visible
import com.lucasdias.ui_components.R
import com.lucasdias.ui_components.card.model.CardProperties

class CardComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var image: ImageView
    private var title: TextView
    private var description: TextView
    private var informationCard: CardView

    init {
        View.inflate(context, R.layout.card_component, this)
        image = findViewById(R.id.image_card_component)
        title = findViewById(R.id.title_card_component)
        description = findViewById(R.id.description_card_component)
        informationCard = findViewById(R.id.information_card_view_card_component)
        informationCardSetup()
    }

    private fun informationCardSetup() {
        informationCard.setBackgroundColor(context.getColor(R.color.mine_shaft))
    }

    fun applyProperties(properties: CardProperties) {
        title.text = properties.name
        description.text = properties.description
        image.loadImage(
            url = properties.thumbnail?.url,
            errorPlaceHolderId = properties.thumbnail?.placeHolder
        )

        descriptionVisibilitySetup(properties.description)
    }

    private fun descriptionVisibilitySetup(description: String?) {
        if (description.isNullOrEmpty().not()) {
            this.description.visible()
        } else {
            this.description.gone()
        }
    }
}
