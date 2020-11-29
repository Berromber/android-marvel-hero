package es.adrianromanb.marvelhero.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import es.adrianromanb.marvelhero.R
import es.adrianromanb.marvelhero.ui.common.startActivity
import es.adrianromanb.marvelhero.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HeroesAdapter

    private val viewModel: MainViewModel by lifecycleScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = HeroesAdapter (viewModel::onMovieClicked)
        recycler.adapter = adapter
        viewModel.model.observe(this, Observer(::updateUi))
        viewModel.onHeroesCalled();
    }

    private fun updateUi(model: MainViewModel.UiModel) {

        progress.visibility = if (model is MainViewModel.UiModel.Loading) View.VISIBLE else View.GONE

//        viewModel.onHeroesCalled()

        when (model) {
            is MainViewModel.UiModel.Content -> adapter.heroes = model.heroes
            is MainViewModel.UiModel.Navigation -> startActivity<DetailActivity> {
                putExtra(DetailActivity.HERO, model.hero.id)
            }
        }
    }
}