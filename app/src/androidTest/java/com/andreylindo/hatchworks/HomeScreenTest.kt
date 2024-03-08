package com.andreylindo.hatchworks

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.andreylindo.hatchworks.api.PokemonApi
import com.andreylindo.hatchworks.common.ResourcesProvider
import com.andreylindo.hatchworks.data.repository.pokemon_repository.PokemonRepositoryImpl
import com.andreylindo.hatchworks.data.response.CardsData
import com.andreylindo.hatchworks.data.response.CardsImage
import com.andreylindo.hatchworks.data.response.CardsResponse
import com.andreylindo.hatchworks.ui.screens.home.HomeScreen
import com.andreylindo.hatchworks.ui.screens.home.HomeViewModel
import com.andreylindo.hatchworks.ui.theme.HatchWorksTheme
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/7/24
 */

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_home_screen_success() {
        composeTestRule.setContent {
            HatchWorksTheme {
                val navHostController = rememberNavController()
                HomeScreen(
                    navHostController,
                    HomeViewModel(
                        pokemonRepository = PokemonRepositoryImpl(FakePokemonsSuccess()),
                        resourcesProvider = ResourcesProvider(context = LocalContext.current)
                    ),
                )
            }
        }

        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
        composeTestRule.onAllNodesWithContentDescription("Pokemon Celebi & Venusaur-GX")
            .assertCountEquals(6)
    }

    @Test
    fun test_home_screen_error() {
        var couldNotGetDataString = ""

        composeTestRule.setContent {
            couldNotGetDataString = LocalContext.current.getString(R.string.could_not_get_data)

            HatchWorksTheme {
                val navHostController = rememberNavController()
                HomeScreen(
                    navHostController,
                    HomeViewModel(
                        pokemonRepository = PokemonRepositoryImpl(FakePokemonsError()),
                        resourcesProvider = ResourcesProvider(context = LocalContext.current)
                    ),
                )
            }
        }

        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
        composeTestRule.onNodeWithText(couldNotGetDataString).assertIsDisplayed()
    }

    @Test
    fun test_home_screen_exception() {
        var couldNotGetDataString = ""

        composeTestRule.setContent {
            couldNotGetDataString = LocalContext.current.getString(R.string.could_not_get_data)

            HatchWorksTheme {
                val navHostController = rememberNavController()
                HomeScreen(
                    navHostController,
                    HomeViewModel(
                        pokemonRepository = PokemonRepositoryImpl(FakePokemonsException()),
                        resourcesProvider = ResourcesProvider(context = LocalContext.current)
                    ),
                )
            }
        }

        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
        composeTestRule.onNodeWithText(couldNotGetDataString).assertIsDisplayed()
    }
}

class FakePokemonsSuccess : PokemonApi {
    override suspend fun getPokemons(pageSize: Int): Response<CardsResponse> {
        return Response.success(
            CardsResponse(
                listOf(
                    CardsData(
                        name = "Celebi & Venusaur-GX",
                        images = CardsImage(large = "https://images.pokemontcg.io/sm9/1_hires.png")
                    ),
                    CardsData(
                        name = "Celebi & Venusaur-GX",
                        images = CardsImage(large = "https://images.pokemontcg.io/sm9/1_hires.png")
                    ),
                    CardsData(
                        name = "Celebi & Venusaur-GX",
                        images = CardsImage(large = "https://images.pokemontcg.io/sm9/1_hires.png")
                    ),
                    CardsData(
                        name = "Celebi & Venusaur-GX",
                        images = CardsImage(large = "https://images.pokemontcg.io/sm9/1_hires.png")
                    ),
                    CardsData(
                        name = "Celebi & Venusaur-GX",
                        images = CardsImage(large = "https://images.pokemontcg.io/sm9/1_hires.png")
                    ),
                    CardsData(
                        name = "Celebi & Venusaur-GX",
                        images = CardsImage(large = "https://images.pokemontcg.io/sm9/1_hires.png")
                    ),
                )
            )
        )
    }
}

class FakePokemonsError : PokemonApi {
    override suspend fun getPokemons(pageSize: Int): Response<CardsResponse> {
        return Response.error(400, byteArrayOf().toResponseBody())
    }
}

class FakePokemonsException : PokemonApi {
    override suspend fun getPokemons(pageSize: Int): Response<CardsResponse> {
        throw throw Exception()
    }
}