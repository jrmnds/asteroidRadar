package com.jrmnds.asteroidradar.ui.listener

import com.jrmnds.asteroidradar.domain.model.Asteroid

class AsteroidClickListener(val clickListener: (asteroid: Asteroid) -> Unit) {
    fun onClick(asteroid: Asteroid) = clickListener(asteroid)
}