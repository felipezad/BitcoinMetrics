package com.crypto.currency.di

import android.content.Context
import com.bumptech.glide.RequestManager
import com.crypto.currency.bitcoin.BitcoinFragment
import dagger.BindsInstance
import dagger.Component
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FeatureDependencies {

    fun requestGlideManager(): RequestManager
}

@Component(dependencies = [FeatureDependencies::class])
interface FeaturesComponent {

    fun inject(fragment: BitcoinFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(featureDependencies: FeatureDependencies): Builder
        fun build(): FeaturesComponent
    }
}