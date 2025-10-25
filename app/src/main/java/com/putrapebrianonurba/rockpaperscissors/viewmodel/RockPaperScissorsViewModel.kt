package com.putrapebrianonurba.rockpaperscissors.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putrapebrianonurba.rockpaperscissors.model.RpsModel
import com.putrapebrianonurba.rockpaperscissors.view.RockPaperScissorsUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.putrapebrianonurba.rockpaperscissors.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RockPaperScissorsViewModel: ViewModel() {
    private val _rpsUIState = MutableStateFlow(RockPaperScissorsUIState())
    val rpsUIState = _rpsUIState.asStateFlow()

    val choice = listOf(
        RpsModel(
            id = 1,
            icon = R.drawable.ic_paper,
            name = "Paper"
        ),
        RpsModel(
            id = 2,
            icon = R.drawable.ic_rock,
            name = "Rock"
        ),
        RpsModel(
            id = 3,
            icon = R.drawable.ic_scissors,
            name = "Scissors"
        )
    )

    fun onUserChoice(player: RpsModel) {
        viewModelScope.launch {
            _rpsUIState.value = _rpsUIState.value.copy(
                player =  player,
                computer = null,
                result = "Computer Thinking!",
                isPlaying = true
            )

            delay(1500)

            val computer = choice.random()
            val result = getResult(player, computer)

            _rpsUIState.value = rpsUIState.value.copy(
                computer = computer,
                player = player,
                result = result,
                win = _rpsUIState.value.win + if (result == "You Win!") 1 else 0,
                lose = _rpsUIState.value.lose + if (result == "You Lose!") 1 else 0,
                draw = _rpsUIState.value.draw + if (result == "Draw!") 1 else 0,
                isPlaying = true
            )
        }
    }

    private fun getResult(player: RpsModel, computer: RpsModel): String {
        return when {
            player.name == computer.name -> "Draw!"
            player.name == "Rock" && computer.name == "Scissors" -> "You Win!"
            player.name == "Paper" && computer.name == "Rock" -> "You Win!"
            player.name == "Scissors" && computer.name == "Paper" -> "You Win!"
            else -> "You Lose!"
        }
    }

    fun playAgain() {
        _rpsUIState.value = _rpsUIState.value.copy(
            computer = null,
            player = null,
            result = "",
            isPlaying = false
        )
    }

    fun resetGame() {
        _rpsUIState.value = _rpsUIState.value.copy(
            win = 0,
            lose = 0,
            draw = 0
        )
    }

}