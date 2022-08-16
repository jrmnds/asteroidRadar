package com.jrmnds.asteroidradar.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AsteroidsList(
    val asteroidsList: MutableList<Asteroid>
): Parcelable


