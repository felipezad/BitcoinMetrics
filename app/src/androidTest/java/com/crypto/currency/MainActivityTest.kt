package com.crypto.currency

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.crypto.currency.bitcoin.R
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun openingAppAndFindTitleToolbar() {
        onView(withText("BitcoinMetrics")).check(matches(isDisplayed()))
    }

    @Test
    fun checkFilterActivityIcon() {
        val filtersView = onView(
            Matchers.allOf(
                withId(R.id.navigation_filters),
                withContentDescription("Settings"),
                withParent(withParent(withId(R.id.action_bar))),
                isDisplayed()
            )
        )
        filtersView.check(matches(isDisplayed()))
    }

    @Test
    fun navigateToFiltersActivity() {
        onView(
            withId(R.id.navigation_filters)
        ).perform(click())

        onView(
            withId(R.id.filtersHeader)
        ).check(matches(isDisplayed()))
    }
}