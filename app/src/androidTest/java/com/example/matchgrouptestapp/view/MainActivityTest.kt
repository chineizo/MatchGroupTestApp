package com.example.matchgrouptestapp.view

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.matchgrouptestapp.R
import com.example.matchgrouptestapp.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun cleanUp() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testThatApplicationNameIsCorrect() {
        val pageTitle = composeTestRule.activity.getString(R.string.app_name)
        composeTestRule.onNodeWithText(pageTitle).assertExists()
    }

    @Test
    fun testThatReviewDetailActivityIsLaunchedWhenReviewItemIsClicked () {
        val reviewDetailsActivityTitle = composeTestRule.activity.getString(R.string.coffee_shop_details)
        composeTestRule.onNodeWithTag(COFFEE_REVIEW__LIST_TEST)
            .onChildren().onFirst().performClick()
        composeTestRule.onNodeWithText(reviewDetailsActivityTitle).assertExists()
    }

    @Test
    fun testThatTheCoffeeReviewListHasAtItemDisplayed () {
        composeTestRule.apply{
            onNodeWithTag(COFFEE_REVIEW__LIST_TEST)
                .onChildren().onFirst().assertIsDisplayed()
        }
    }
}