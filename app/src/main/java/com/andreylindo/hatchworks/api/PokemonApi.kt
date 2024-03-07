package com.andreylindo.hatchworks.api

import com.andreylindo.hatchworks.data.response.CardsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/5/24
 */
interface PokemonApi {
    @GET("v2/cards")
    suspend fun getPokemons(
        @Query("pageSize") pageSize: Int = 50,
    ): Response<CardsResponse>
}