package es.adrianromanb.data.repository

import es.adrianromanb.data.source.RemoteDataSource
import es.adrianromanb.domain.Hero

class HeroRepository(
    private val remoteDataSource: RemoteDataSource,
    private val apiKey: String,
    private val hash: String,
    private val ts: String
) {

    suspend fun getHeroes(): List<Hero> {
        return remoteDataSource.getHeroes(apiKey, hash, ts)
    }

    suspend fun getHeroById(id: Int): Hero {
        return remoteDataSource.getHeroById( apiKey, hash, ts, id)
    }
}