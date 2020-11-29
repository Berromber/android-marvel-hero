package es.adrianromanb.marvelhero.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.adrianromanb.domain.Hero
import es.adrianromanb.marvelhero.R
import es.adrianromanb.marvelhero.ui.common.basicDiffUtil
import es.adrianromanb.marvelhero.ui.common.inflate
import es.adrianromanb.marvelhero.ui.common.loadUrl
import kotlinx.android.synthetic.main.view_hero.view.*

class HeroesAdapter(private val listener: (Hero) -> Unit) :
    RecyclerView.Adapter<HeroesAdapter.ViewHolder>() {

    var heroes: List<Hero> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_hero, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = heroes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = heroes[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(hero: Hero) {
            itemView.heroTitle.text = hero.name
            itemView.heroCover.loadUrl("${hero.thumbnail.path}"+".${hero.thumbnail.extension}")
        }
    }
}