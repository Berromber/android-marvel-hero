package es.adrianromanb.usecases

import com.nhaarman.mockitokotlin2.whenever
import es.adrianromanb.data.repository.HeroRepository
import es.adrianromanb.testshared.mockedHero
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FindHeroByIdTest {

    @Mock
    lateinit var heroRepository: HeroRepository

    lateinit var findHeroById: FindHeroById

    @Before
    fun setup() {
        findHeroById = FindHeroById(heroRepository)
    }

    @Test
    fun t00WhenFindHeroByIdCalled() {
        runBlocking {
            val hero = mockedHero.copy(1)
            whenever(heroRepository.getHeroById(1)).thenReturn(hero)

            val result = findHeroById.invoke(1)

            assertEquals(hero, result)
        }
    }
}