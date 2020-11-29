package es.adrianromanb.usecases

import com.nhaarman.mockitokotlin2.whenever
import es.adrianromanb.data.repository.HeroRepository
import es.adrianromanb.testshared.mockedHero
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetHeroesTest {

    @Mock
    lateinit var heroRepository: HeroRepository

    lateinit var getHeroes: GetHeroes

    @Before
    fun setup() {
        getHeroes = GetHeroes(heroRepository)
    }

    @Test
    fun t00WhenFindHeroByIdCalled() {
        runBlocking {
            val heroes = listOf(mockedHero.copy(1))
            whenever(heroRepository.getHeroes()).thenReturn(heroes)

            val result = getHeroes.invoke()

            Assert.assertEquals(heroes, result)
        }
    }
}