package com.andreylindo.hatchworks.data.response

import com.google.gson.annotations.SerializedName

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/5/24
 */

data class CardsResponse(
    @SerializedName("data")
    val data: List<CardsData> = emptyList()
)

data class CardsData(
    val id: String = "",
    val name: String = "",
    val images: CardsImage? = null
)

data class CardsImage(
    val small: String = "",
    val large: String = ""
)