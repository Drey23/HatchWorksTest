package com.andreylindo.hatchworks.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.andreylindo.hatchworks.api.PokemonApi
import com.andreylindo.hatchworks.data.NetworkResult
import com.andreylindo.hatchworks.data.repository.pokemon_repository.PokemonRepositoryImpl
import com.andreylindo.hatchworks.data.response.CardsData
import com.andreylindo.hatchworks.data.response.CardsImage
import com.andreylindo.hatchworks.data.response.CardsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/7/24
 */
@OptIn(ExperimentalCoroutinesApi::class)
class PokemonRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var pokemonApi: PokemonApi

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun test_getPokemons_success() = runTest {
        val repository = PokemonRepositoryImpl(pokemonApi)

        val pokemons = listOf(
            CardsData(images = CardsImage(large = "www.google.com"))
        )

        Mockito.`when`(pokemonApi.getPokemons())
            .thenReturn(
                Response.success(CardsResponse(pokemons))
            )

        val result = repository.getPokemons()


        assert(result is NetworkResult.Success)
        Mockito.verify(pokemonApi, Mockito.atLeastOnce()).getPokemons()
    }

    @Test
    fun test_getPokemons_error() = runTest {
        val repository = PokemonRepositoryImpl(pokemonApi)

        Mockito.`when`(pokemonApi.getPokemons())
            .thenReturn(
                Response.error(400, byteArrayOf().toResponseBody())
            )

        val result = repository.getPokemons()

        assert(result is NetworkResult.Error)
        Mockito.verify(pokemonApi, Mockito.atLeastOnce()).getPokemons()
    }

    @Test
    fun test_getPokemons_http_exception() = runTest {
        val repository = PokemonRepositoryImpl(pokemonApi)

        Mockito.`when`(pokemonApi.getPokemons())
            .thenThrow(
                HttpException(Response.error<Any>(400, byteArrayOf().toResponseBody()))
            )

        val result = repository.getPokemons()

        assert(result is NetworkResult.Error)
        Mockito.verify(pokemonApi, Mockito.atLeastOnce()).getPokemons()
    }

    @Test
    fun test_getPokemons_exception() = runTest {
        val repository = PokemonRepositoryImpl(pokemonApi)

        Mockito.`when`(pokemonApi.getPokemons())
            .thenThrow(
                NullPointerException()
            )

        val result = repository.getPokemons()

        assert(result is NetworkResult.Exception)
        Mockito.verify(pokemonApi, Mockito.atLeastOnce()).getPokemons()
    }
}