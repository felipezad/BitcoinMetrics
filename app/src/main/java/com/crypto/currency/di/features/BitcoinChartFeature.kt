package com.crypto.currency.di.features

import android.content.Context
import com.bumptech.glide.RequestManager
import com.crypto.currency.bitcoin.BitcoinChartFragment
import com.crypto.currency.di.network.NetworkReceiver
import com.crypto.currency.filters.FilterActivity
import dagger.BindsInstance
import dagger.Component
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface BitcoinChartFeatureDependencies {
    fun requestGlideManager(): RequestManager
    fun requestNetworkReceiver(): NetworkReceiver
}

@Component(dependencies = [BitcoinChartFeatureDependencies::class])
interface BitcoinChartFeatureComponent {

    fun inject(fragment: BitcoinChartFragment)
    fun inject(activity: FilterActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(featureDependencies: BitcoinChartFeatureDependencies): Builder
        fun build(): BitcoinChartFeatureComponent
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FilterFeatureDependencies

@Component(dependencies = [FilterFeatureDependencies::class])
interface FilterFeatureComponent {

    fun inject(activity: FilterActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(filterDependencies: FilterFeatureDependencies): Builder
        fun build(): FilterFeatureComponent
    }
}