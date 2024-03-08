package com.andreylindo.hatchworks.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreylindo.hatchworks.R
import com.andreylindo.hatchworks.common.ResourcesProvider
import com.andreylindo.hatchworks.data.NetworkResult
import com.andreylindo.hatchworks.data.repository.pokemon_repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val pokemonRepository: PokemonRepository,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Nothing)
    private val _sideEffect = MutableSharedFlow<HomeSideEffect>()

    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    val sideEffect: SharedFlow<HomeSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun getPokemons() {
        viewModelScope.launch {
            _state.value = HomeState.Loading

            when (val response = pokemonRepository.getPokemons()) {
                is NetworkResult.Success -> {
                    _state.value = HomeState.Loaded(response.data)
                }

                is NetworkResult.Error -> {
                    _state.value =
                        HomeState.Error(resourcesProvider.getString(R.string.could_not_get_data))
                }

                is NetworkResult.Exception -> {
                    _state.value =
                        HomeState.Error(resourcesProvider.getString(R.string.could_not_get_data))

                    _sideEffect.emit(
                        HomeSideEffect.ShowErrorMessage(
                            resourcesProvider.getString(R.string.unexpected_error)
                        )
                    )
                }
            }
        }
    }
}