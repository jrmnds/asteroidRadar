package com.jrmnds.asteroidradar.di

import com.jrmnds.asteroidradar.data.repository.AsteroidRepositoryImpl
import com.jrmnds.asteroidradar.domain.repository.AsteroidRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAsteroidRepository(
        asteroidRepositoryImpl: AsteroidRepositoryImpl
    ): AsteroidRepository

}