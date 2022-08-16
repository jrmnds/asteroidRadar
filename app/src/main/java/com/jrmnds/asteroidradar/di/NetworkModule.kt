package com.jrmnds.asteroidradar.di

import com.jrmnds.asteroidradar.common.Constants
import com.jrmnds.asteroidradar.data.remote.AsteroidApiServices
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {

    @Provides
    fun provideRetrofit(moshi : Moshi): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(Constants.BASE_URL)
        .build()

    @Provides
    fun provideAsteroidApi(retrofit: Retrofit): AsteroidApiServices =
        retrofit.create(AsteroidApiServices::class.java)

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}