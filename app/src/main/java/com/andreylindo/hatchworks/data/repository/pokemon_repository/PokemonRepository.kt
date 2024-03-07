package com.andreylindo.hatchworks.data.repository.pokemon_repository

import com.andreylindo.hatchworks.data.NetworkResult
import com.andreylindo.hatchworks.data.response.CardsData

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/6/24
 */
interface PokemonRepository {
    suspend fun getPokemons(): NetworkResult<List<CardsData>>
}