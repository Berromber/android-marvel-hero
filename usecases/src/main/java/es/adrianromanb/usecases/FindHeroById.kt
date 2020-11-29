package es.adrianromanb.usecases

import es.adrianromanb.data.repository.HeroRepository
import es.adrianromanb.domain.Hero

class FindHeroById(private val heroRepository: HeroRepository) {
    suspend fun invoke(id: Int): Hero = heroRepository.getHeroById(id)
}