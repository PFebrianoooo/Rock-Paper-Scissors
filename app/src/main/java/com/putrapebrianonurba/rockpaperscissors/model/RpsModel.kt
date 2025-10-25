package com.putrapebrianonurba.rockpaperscissors.model

import androidx.annotation.DrawableRes

data class RpsModel(
    val id: Int,
    @DrawableRes val icon: Int,
    val name: String
)
