package com.andreylindo.hatchworks.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreylindo.hatchworks.data.NetworkResult
import com.andreylindo.hatchworks.data.repository.pokemon_repository.PokemonRepository
import com.andreylindo.hatchworks.data.response.CardsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/5/24
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _pokemons = MutableLiveData<List<CardsData>>()

    val pokemons: LiveData<List<CardsData>>
        get() = _pokemons

    fun getPokemons() {
        viewModelScope.launch {
            try {
                when(val response = pokemonRepository.getPokemons()) {
                    is NetworkResult.Success -> {
                        val listOfPokemons = response.data
                        _pokemons.postValue(listOfPokemons)
                    }

                    is NetworkResult.Error -> {

                    }

                    is NetworkResult.Exception -> {

                    }
                }
            } catch (e: Exception) {
                print(e)
            }
        }
    }
}