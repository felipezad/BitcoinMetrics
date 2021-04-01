package com.crypto.currency

import android.content.Context
import com.crypto.currency.di.navigator.AppNavigator
import com.crypto.currency.filters.FilterActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeaturesNavigator @Inject constructor() : AppNavigator {

    override fun navigateToFilters(context: Context) {
        navigateTo(context, FilterActivity.newInstance(context))
    }
}