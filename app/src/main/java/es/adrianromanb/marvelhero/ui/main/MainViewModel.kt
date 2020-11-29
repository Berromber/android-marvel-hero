package es.adrianromanb.marvelhero.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.adrianromanb.domain.Hero
import es.adrianromanb.marvelhero.ui.common.ScopedViewModel
import es.adrianromanb.usecases.GetHeroes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(
    private val getHeroes: GetHeroes,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    sealed class UiModel {
        object Loading : UiModel()
        data class Content(val heroes: List<Hero>) : UiModel()
        data class Navigation(val hero: Hero) : UiModel()
        object RequestLocationPermission : UiModel()
    }

    init {
        initScope()
    }

    private fun refresh() {
        _model.value = UiModel.RequestLocationPermission
    }

    fun onHeroesCalled() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(getHeroes.invoke())
        }
    }

    fun onMovieClicked(hero: Hero) {
        _model.value = UiModel.Navigation(hero)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}