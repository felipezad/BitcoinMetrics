package com.crypto.currency.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

class ErrorActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(ErrorActivity::class.java)


    @Test
    fun checkFilterActivityIcon() {
        val errorView = onView(
            allOf(
                ViewMatchers.withId(R.id.iconNoInternet),
                ViewMatchers.withContentDescription(R.string.there_is_no_internet_connection_try_again_later),
                ViewMatchers.isDisplayed()
            )
        )
        errorView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}