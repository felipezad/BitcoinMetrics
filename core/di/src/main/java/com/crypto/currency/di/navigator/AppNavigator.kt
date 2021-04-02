package com.crypto.currency.di.navigator

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

interface AppNavigator {

    fun navigateToError(context: Context)

    fun navigateToFilters(context: Context)

    fun navigateTo(context: Context, intent: Intent) {
        startActivity(context, intent, null)
    }
}