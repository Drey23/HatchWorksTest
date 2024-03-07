package com.andreylindo.hatchworks.di

import com.andreylindo.hatchworks.api.PokemonApi
import com.andreylindo.hatchworks.data.repository.pokemon_repository.PokemonRepository
import com.andreylindo.hatchworks.data.repository.pokemon_repository.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/6/24
 */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePokemonRepository(api: PokemonApi): PokemonRepository {
        return PokemonRepositoryImpl(api)
    }
}