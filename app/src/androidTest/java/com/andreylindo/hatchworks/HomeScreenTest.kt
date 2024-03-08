package com.andreylindo.hatchworks

import androidx.compose.material3.Button
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.andreylindo.hatchworks.common.ResourcesProvider
import com.andreylindo.hatchworks.data.repository.pokemon_repository.PokemonRepositoryImpl
import com.andreylindo.hatchworks.ui.screens.home.HomeScreen
import com.andreylindo.hatchworks.ui.screens.home.HomeViewModel
import com.andreylindo.hatchworks.ui.screens.home.PokemonCard
import com.andreylindo.hatchworks.ui.theme.HatchWorksTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/7/24
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

//    @get:Rule
//    var hiltRule = HiltAndroidRule(this)

    @Mock
    lateinit var pokemonRepository: PokemonRepositoryImpl

    @Mock
    lateinit var resourcesProvider: ResourcesProvider

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun homeTest() {
        composeTestRule.setContent {
            HatchWorksTheme {
                val navHostController = rememberNavController()
                val vm = HomeViewModel(pokemonRepository, resourcesProvider)
                HomeScreen(navHostController, vm)
            }

            //composeTestRule.onNodeWithText("Error").assertIsNotDisplayed()
        }
    }
}