package es.adrianromanb.marvelhero.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.adrianromanb.domain.Hero
import es.adrianromanb.marvelhero.ui.common.ScopedViewModel
import es.adrianromanb.usecases.FindHeroById
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailViewModel(
    private val heroId: Int,
    private val findHeroById: FindHeroById,
    override val uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    data class UiModel(val hero: Hero)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findMovie()
            return _model
        }

    private fun findMovie() = launch {
        _model.value = UiModel(findHeroById.invoke(heroId))
    }
}