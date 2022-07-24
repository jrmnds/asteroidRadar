package com.jrmnds.asteroidradar.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object MoshiBuilder {

val MOSHI_INSTANCE: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
}