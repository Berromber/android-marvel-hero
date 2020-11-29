package es.adrianromanb.data.repository

import com.nhaarman.mockitokotlin2.whenever
import es.adrianromanb.data.source.RemoteDataSource
import es.adrianromanb.testshared.mockedHero
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HeroRepositoryTest {

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    lateinit var heroRepository: HeroRepository

    private val apiKey = "4184513"
    private val apiKeySecret = "1345781055678"
    private val ts = "12"

    @Before
    fun setUp() {
        heroRepository = HeroRepository(remoteDataSource, apiKey, apiKeySecret, ts)
    }

    @Test
    fun t00WhenGetHeroesCalled() {
        runBlocking {

            val heroes = listOf(mockedHero.copy(1))
            whenever(heroRepository.getHeroes()).thenReturn(heroes)

            val result = heroRepository.getHeroes()
            assert(!result.isEmpty())
        }
    }

    @Test
    fun t01WhenGetHeroesByIdCalled() {
        runBlocking {

            val hero = mockedHero.copy(1)
            whenever(heroRepository.getHeroById(1)).thenReturn(hero)

            val result = heroRepository.getHeroById(1)

            assert(result.id.equals(1))
        }
    }
}