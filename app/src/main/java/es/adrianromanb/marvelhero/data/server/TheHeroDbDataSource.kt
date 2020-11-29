package es.adrianromanb.marvelhero.data.server

import es.adrianromanb.data.source.RemoteDataSource
import es.adrianromanb.domain.Hero

class TheHeroDbDataSource(private val theHeroDb: TheHeroDb) : RemoteDataSource {

    override suspend fun getHeroes(apiKey: String, hash: String, ts: String): List<Hero> =
        theHeroDb.service
            .listMarvelHeroesAsync(apiKey, hash, ts, 100)
            .data
            .results

    override suspend fun getHeroById(apiKey: String, hash: String, ts: String, heroId: Int): Hero =
        theHeroDb.service
            .heroMarvelHeroesAsync(heroId.toString(), apiKey, hash, ts)
            .data
            .results[0]

}