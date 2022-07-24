package com.jrmnds.asteroidradar.api

import com.jrmnds.asteroidradar.api.network.NasaApiServices
import com.jrmnds.asteroidradar.api.network.RetrofitBuilder

object NasaAPI {

    val NasaApiService: NasaApiServices by lazy { RetrofitBuilder.RETROFIT_INSTANCE.create(NasaApiServices::class.java) }

}