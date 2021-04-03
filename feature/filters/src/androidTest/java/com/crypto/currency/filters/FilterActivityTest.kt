package com.crypto.currency.filters

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class FilterActivityTest {

    @Rule
    @JvmField
    var hiltRule = HiltAndroidRule(this)

    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(FilterActivity::class.java)


    @Before
    fun init() {
        hiltRule.inject()
    }

    @After
    fun destroy() {
        activityRule.scenario.close()
    }

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

        onView(withId(R.id.filterButton)).check(ViewAssertions.doesNotExist())
    }
}