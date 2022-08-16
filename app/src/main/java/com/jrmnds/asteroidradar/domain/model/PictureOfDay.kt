package com.jrmnds.asteroidradar.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class PictureOfDay(
    @Json(name = "media_type")
    val mediaType: String,
    val title: String,
    val url: String
) : Parcelable
