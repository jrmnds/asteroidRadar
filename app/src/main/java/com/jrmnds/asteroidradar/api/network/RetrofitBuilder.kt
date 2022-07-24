package com.jrmnds.asteroidradar.api.network

import com.jrmnds.asteroidradar.utils.Constants.BASE_URL
import com.jrmnds.asteroidradar.utils.MoshiBuilder
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {

    val RETROFIT_INSTANCE: Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(MoshiBuilder.MOSHI_INSTANCE))
        .baseUrl(BASE_URL)
        .build()
}