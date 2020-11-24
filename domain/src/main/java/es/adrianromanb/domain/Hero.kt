package es.adrianromanb.domain

data class Hero(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: HeroImage
)