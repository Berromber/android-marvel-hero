package es.adrianromanb.marvelhero.data.server

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheHeroDbService {
    @GET("/v1/public/characters")
    suspend fun listMarvelHeroesAsync(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("limit") limit: Int
    ): TheHeroDbResult

    @GET("/v1/public/characters/{heroId}")
    suspend fun heroMarvelHeroesAsync(
        @Path("heroId") heroId: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): TheHeroDbResult
}