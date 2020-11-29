package es.adrianromanb.marvelhero.ui

import es.adrianromanb.data.source.RemoteDataSource
import es.adrianromanb.domain.Hero
import es.adrianromanb.marvelhero.dataModule
import es.adrianromanb.testshared.mockedHero
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun initMockedDi(vararg modules: Module) {
    startKoin {
        modules(listOf(mockedAppModule, dataModule) + modules)
    }
}

private val mockedAppModule = module {
    single(named("apiKey")) { "12456" }
    single<RemoteDataSource> { FakeRemoteDataSource() }
    single { Dispatchers.Unconfined }
}

val defaultFakeMovies = listOf(
    mockedHero.copy(1),
    mockedHero.copy(2),
    mockedHero.copy(3),
    mockedHero.copy(4)
)

class FakeRemoteDataSource : RemoteDataSource {

    var heroes = defaultFakeMovies

    override suspend fun getHeroes(apiKey: String, hash: String, ts: String): List<Hero> = heroes
    override suspend fun getHeroById(apiKey: String, hash: String, ts: String, heroId: Int): Hero = heroes[0]
}