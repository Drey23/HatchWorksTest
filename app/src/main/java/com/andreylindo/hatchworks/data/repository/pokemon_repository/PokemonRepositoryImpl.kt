package com.andreylindo.hatchworks.data.repository.pokemon_repository

import com.andreylindo.hatchworks.api.PokemonApi
import com.andreylindo.hatchworks.data.NetworkResult
import com.andreylindo.hatchworks.data.response.CardsData
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/6/24
 */
class PokemonRepositoryImpl @Inject constructor(private val api: PokemonApi) : PokemonRepository {
    override suspend fun getPokemons(): NetworkResult<List<CardsData>> {
        return try {
            val response = api.getPokemons()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                NetworkResult.Success(body.data)
            } else {
                NetworkResult.Error(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            NetworkResult.Error(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            NetworkResult.Exception(e)
        }
    }
}