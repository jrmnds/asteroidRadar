package com.jrmnds.asteroidradar.data

object NasaApi {

    val nasaService: NasaApiService by lazy { RetrofitBuilderHelper.retrofit.create(NasaApiService::class.java) }

}