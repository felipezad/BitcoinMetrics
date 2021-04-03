package com.crypto.currency.filters

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class FilterActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(FilterActivity::class.java)

    @Test
    fun addFiltersParametersAndConfirmAction() {
        onView(
            withId(R.id.filtersHeader)
        ).check(matches(isDisplayed()))

        onView(
            withId(R.id.timeSpanEditText)
        ).perform(click(), typeText("1"))

        onView(
            withId(R.id.timeSpanEditText)
        ).check(matches(withText("1")))

        onView(
            withId(R.id.rollingAverageEditText)
        ).perform(click(), typeText("1"))

        onView(
            withId(R.id.rollingAverageEditText)
        ).check(matches(withText("1")))

        onView(
            withId(R.id.filterButton)
        ).perform(click())
    }
}