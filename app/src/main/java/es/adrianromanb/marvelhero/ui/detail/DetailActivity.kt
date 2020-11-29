package es.adrianromanb.marvelhero.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import es.adrianromanb.marvelhero.R
import es.adrianromanb.marvelhero.ui.common.loadUrl
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity : AppCompatActivity() {

    companion object {
        const val HERO = "DetailActivity:hero"
    }

    private val viewModel: DetailViewModel by lifecycleScope.viewModel(this) {
        parametersOf(intent.getIntExtra(HERO, -1))
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: DetailViewModel.UiModel) = with(model.hero) {
        heroDetailToolbar.title = name
        heroDetailImage.loadUrl("${thumbnail.path}"+".${thumbnail.extension}")
        heroDetailSummary.text = description
        heroDetailInfo.setHero(this)
    }
}
