package es.adrianromanb.marvelhero.ui.detail

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import es.adrianromanb.domain.Hero

class HeroDetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setHero(hero: Hero) = with(hero) {
        text = description
    }
}