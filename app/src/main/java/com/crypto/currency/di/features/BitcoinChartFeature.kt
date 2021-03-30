package com.crypto.currency.di

import android.content.Context
import com.bumptech.glide.RequestManager
import com.crypto.currency.bitcoin.BitcoinChartFragment
import dagger.BindsInstance
import dagger.Component
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface BitcoinChartFeatureDependencies {

    fun requestGlideManager(): RequestManager
}

@Component(dependencies = [BitcoinChartFeatureDependencies::class])
interface BitcoinChartFeatureComponent {

    fun inject(fragment: BitcoinChartFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(featureDependencies: BitcoinChartFeatureDependencies): Builder
        fun build(): BitcoinChartFeatureComponent
    }
}