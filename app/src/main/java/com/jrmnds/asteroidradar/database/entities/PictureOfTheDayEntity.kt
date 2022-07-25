package com.jrmnds.asteroidradar.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pictureOfTheDay")
data class PictureOfTheDayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val mediaType: String,
    val title: String,
    val url: String
)
