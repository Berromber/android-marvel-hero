package es.adrianromanb.marvelhero.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.jakewharton.espresso.OkHttp3IdlingResource
import es.adrianromanb.marvelhero.R
import es.adrianromanb.marvelhero.data.server.TheHeroDb
import es.adrianromanb.marvelhero.ui.main.MainActivity
import es.adrianromanb.marvelhero.utils.fromJson
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.get

class UiTest : KoinTest {

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("marvelheroes.json")
        )
        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("marvelhero.json")
        )

        val resource = OkHttp3IdlingResource.create("OkHttp", get<TheHeroDb>().okHttpClient)
        IdlingRegistry.getInstance().register(resource)
    }

    @Test
    fun clickAHeroNavigatesToDetail() {
        activityTestRule.launchActivity(null)

        onView(withId(R.id.recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                4,
                click()
            )
        )

        onView(withId(R.id.heroDetailToolbar))
            .check(matches(hasDescendant(withText("Aaron Stack"))))

    }
}