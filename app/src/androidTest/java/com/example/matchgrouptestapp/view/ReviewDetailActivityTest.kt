package com.example.matchgrouptestapp.view

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.matchgrouptestapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReviewDetailActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ReviewDetailActivity>()

    @Test
    fun testThatActivityTitleIsCorrect() {
        val pageTitle = composeTestRule.activity.getString(R.string.coffee_shop_details)
        composeTestRule.onNodeWithText(pageTitle).assertExists()
    }

}