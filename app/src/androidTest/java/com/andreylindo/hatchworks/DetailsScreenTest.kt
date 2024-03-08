package com.andreylindo.hatchworks

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.andreylindo.hatchworks.ui.screens.details.DetailsScreen
import com.andreylindo.hatchworks.ui.theme.HatchWorksTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/8/24
 */
@RunWith(AndroidJUnit4::class)
class DetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_home_screen_success() {
        composeTestRule.setContent {
            HatchWorksTheme {
                val navHostController = rememberNavController()
                DetailsScreen(
                    navHostController,
                    "https://images.pokemontcg.io/sm9/1_hires.png"
                )
            }
        }

        composeTestRule.onNodeWithText("Details").assertIsDisplayed()
        composeTestRule.onAllNodesWithContentDescription("Pokemon Card")
            .assertCountEquals(1)
    }
}