package com.crypto.currency.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ErrorActivityTest {

    @Rule
    @JvmField
    var hiltRule = HiltAndroidRule(this)

    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(ErrorActivity::class.java)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @After
    fun destroy() {
        activityRule.scenario.close()
    }

    @Test
    fun checkNoInternetConnectivityIcon() {
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