package com.andreylindo.hatchworks.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        if (state.value !is HomeState.Loaded) {
            viewModel.getPokemons()
        }

        viewModel.sideEffect.collect {
            when (it) {
                is HomeSideEffect.ShowErrorMessage -> {
                    snackbarHostState.showSnackbar(it.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            CustomTopAppBar(
                title = LocalContext.current.getString(R.string.home),
                navigationIcon = null,
            )
        },
    ) { innerPadding ->
        when (val stateValue = state.value) {
            is HomeState.Loading -> {
                LinearProgressIndicator(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }

            is HomeState.Error -> {
                Text(stateValue.message, modifier = Modifier.padding(innerPadding))
            }

            is HomeState.Nothing -> {
                Box(modifier = Modifier)
            }

            is HomeState.Loaded -> {
                val pokemons = stateValue.pokemons

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .padding(innerPadding),
                ) {
                    items(count = pokemons.size) { index ->
                        val pokemon = pokemons[index]
                        val imageUrl = pokemon.images?.large.orEmpty()
                        val name = pokemon.name

                        PokemonCard(
                            imageUrl,
                            name
                        ) {
                            navController.navigate(RouteScreens.Details(imageUrl).route)
                        }
                    }
                }
            }
        }
    }
}