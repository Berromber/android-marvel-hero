package es.adrianromanb.marvelhero

import android.app.Application
import es.adrianromanb.data.repository.HeroRepository
import es.adrianromanb.data.source.RemoteDataSource
import es.adrianromanb.marvelhero.data.server.TheHeroDb
import es.adrianromanb.marvelhero.data.server.TheHeroDbDataSource
import es.adrianromanb.marvelhero.ui.detail.DetailActivity
import es.adrianromanb.marvelhero.ui.detail.DetailViewModel
import es.adrianromanb.marvelhero.ui.main.MainActivity
import es.adrianromanb.marvelhero.ui.main.MainViewModel
import es.adrianromanb.usecases.FindHeroById
import es.adrianromanb.usecases.GetHeroes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin{
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single(named("apiKey")){ androidApplication().getString(R.string.api_key)}
    single(named("apiKeySecret")){ androidApplication().getString(R.string.api_secret_key)}
    factory<RemoteDataSource> { TheHeroDbDataSource(get())}
    single<CoroutineDispatcher>{Dispatchers.Main}
    single(named("baseUrl")){ "https://gateway.marvel.com" }
    single { TheHeroDb(get(named("baseUrl")))}
}

val dataModule = module {
    factory { HeroRepository(get(), get(named("apiKey")), get(named("apiKeySecret")), "1") }
}

private val scopesModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get(), get()) }
        scoped { GetHeroes(get())}
    }

    scope(named<DetailActivity>()) {
        viewModel { (id: Int) -> DetailViewModel(id, get(), get()) }
        scoped { FindHeroById(get())}
    }
}