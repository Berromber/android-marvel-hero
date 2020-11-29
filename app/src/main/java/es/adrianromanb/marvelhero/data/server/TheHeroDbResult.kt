package es.adrianromanb.marvelhero.data.server

import com.google.gson.annotations.SerializedName
import es.adrianromanb.domain.Hero

data class TheHeroDbResult (
    val code: Int,
    val status: String,
    val data: TheHeroData
)

data class TheHeroData (
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<Hero>
)