package es.adrianromanb.data.source

import es.adrianromanb.domain.Hero

interface RemoteDataSource {
    suspend fun getHeroes(apiKey: String, hash: String, ts: String): List<Hero>
}