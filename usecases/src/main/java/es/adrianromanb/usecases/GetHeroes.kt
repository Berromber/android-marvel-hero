package es.adrianromanb.usecases

import es.adrianromanb.data.repository.HeroRepository
import es.adrianromanb.domain.Hero


class GetHeroes(private val heroRepository: HeroRepository) {
    suspend fun invoke(): List<Hero> = heroRepository.getHeroes()
}