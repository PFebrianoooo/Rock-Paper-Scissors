package com.putrapebrianonurba.rockpaperscissors.view

import com.putrapebrianonurba.rockpaperscissors.model.RpsModel

data class RockPaperScissorsUIState(
    val computer: RpsModel? = null,
    val player: RpsModel? = null,
    val result: String = "",
    val win: Int = 0,
    val draw: Int = 0,
    val lose: Int = 0,
    val isPlaying: Boolean = false
)
