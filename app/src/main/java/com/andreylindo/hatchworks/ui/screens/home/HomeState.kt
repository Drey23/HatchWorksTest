package com.andreylindo.hatchworks.ui.screens.home

import com.andreylindo.hatchworks.data.response.CardsData

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/7/24
 */
sealed class HomeState {
    data class Loaded(val pokemons: List<CardsData>) : HomeState()
    object Loading : HomeState()
    object Nothing : HomeState()
    data class Error(val message: String) : HomeState()
}

sealed class HomeSideEffect {
    data class ShowErrorMessage(val message: String) : HomeSideEffect()
}