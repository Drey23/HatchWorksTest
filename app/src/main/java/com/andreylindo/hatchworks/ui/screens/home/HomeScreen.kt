package com.andreylindo.hatchworks.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.andreylindo.hatchworks.R
import com.andreylindo.hatchworks.navigation.RouteScreens
import com.andreylindo.hatchworks.ui.custom_composables.CustomTopAppBar

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/5/24
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeNavController: NavHostController, viewModel: HomeViewModel) {

    val pokemons = viewModel.pokemons.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getPokemons()
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = LocalContext.current.getString(R.string.home),
                navigationIcon = null,
            )
        },
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .padding(innerPadding),
        ) {
            items(count = pokemons.value?.size ?: 0) { index ->
                val imageUrl = pokemons.value?.get(index)?.images?.large.orEmpty()

                PokemonCard(
                    imageUrl
                ) {
                    homeNavController.navigate(RouteScreens.Details.route + "?imageUrl=$imageUrl")
                }
            }
        }
    }
}