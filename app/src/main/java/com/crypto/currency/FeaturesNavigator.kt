package com.crypto.currency

import android.content.Context
import com.crypto.currency.di.navigator.AppNavigator
import com.crypto.currency.filters.FilterActivity
import com.crypto.currency.ui.ErrorActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeaturesNavigator @Inject constructor() : AppNavigator {

    override fun navigateToError(context: Context) {
        navigateTo(context, ErrorActivity.newInstance(context))
    }

    override fun navigateToFilters(context: Context) {
        navigateTo(context, FilterActivity.newInstance(context))
    }
}