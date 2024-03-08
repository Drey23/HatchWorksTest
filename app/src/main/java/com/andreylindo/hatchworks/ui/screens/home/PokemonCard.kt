package com.andreylindo.hatchworks.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.andreylindo.hatchworks.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/5/24
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonCard(url: String, onTapped: ()-> Unit) {
    GlideImage(
        model = url,
        contentDescription = LocalContext.current.getString(R.string.pokemon_card),
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .clickable { onTapped.invoke() },
    )
}