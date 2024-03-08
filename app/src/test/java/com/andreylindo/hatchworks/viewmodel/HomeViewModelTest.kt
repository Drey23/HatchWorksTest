package com.andreylindo.hatchworks.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.andreylindo.hatchworks.common.EMPTY_STRING
import com.andreylindo.hatchworks.common.ResourcesProvider
import com.andreylindo.hatchworks.data.NetworkResult
import com.andreylindo.hatchworks.data.repository.pokemon_repository.PokemonRepository
import com.andreylindo.hatchworks.data.response.CardsData
import com.andreylindo.hatchworks.data.response.CardsImage
import com.andreylindo.hatchworks.ui.screens.home.HomeState
import com.andreylindo.hatchworks.ui.screens.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/7/24
 */
@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var pokemonRepository: PokemonRepository

    @Mock
    lateinit var resourcesProvider: ResourcesProvider

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun test_getPokemons_success() = runTest {
        val vm = HomeViewModel(pokemonRepository, resourcesProvider)

        val pokemons = listOf(
            CardsData(images = CardsImage(large = "www.google.com"))
        )

        Mockito.`when`(pokemonRepository.getPokemons())
            .thenReturn(
                NetworkResult.Success(
                    data = pokemons
                )
            )

        vm.state.test {
            val state = awaitItem()
            assert(state is HomeState.Nothing)
            cancel()
        }

        vm.getPokemons()

        vm.state.test {
            val state = awaitItem()
            assert(state is HomeState.Error)
            cancel()
        }

        verify(pokemonRepository, atLeastOnce()).getPokemons()
    }

    @Test
    fun test_getPokemons_error() = runTest {
        val vm = HomeViewModel(pokemonRepository, resourcesProvider)

        Mockito.`when`(pokemonRepository.getPokemons())
            .thenReturn(
                NetworkResult.Error(
                    message = EMPTY_STRING,
                    code = 404
                )
            )

        vm.state.test {
            val state = awaitItem()
            assert(state is HomeState.Nothing)
            cancel()
        }

        vm.getPokemons()

        vm.state.test {
            val state = awaitItem()
            assert(state is HomeState.Error)
            awaitComplete()
        }

        verify(pokemonRepository, atLeastOnce()).getPokemons()
    }

    @Test
    fun test_getPokemons_exception() = runTest {
        val vm = HomeViewModel(pokemonRepository, resourcesProvider)

        Mockito.`when`(pokemonRepository.getPokemons())
            .thenReturn(
                NetworkResult.Exception(
                    throwable = Throwable()
                )
            )

        vm.state.test {
            val state = awaitItem()
            assert(state is HomeState.Nothing)
            cancel()
        }

        vm.getPokemons()

        vm.state.test {
            val state = awaitItem()
            assert(state is HomeState.Error)
            cancel()
        }

        verify(pokemonRepository, atLeastOnce()).getPokemons()
    }
}