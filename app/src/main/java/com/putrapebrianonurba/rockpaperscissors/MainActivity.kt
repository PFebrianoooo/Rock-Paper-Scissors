package com.putrapebrianonurba.rockpaperscissors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.putrapebrianonurba.rockpaperscissors.view.RockPaperScissorsScreen
import com.putrapebrianonurba.rockpaperscissors.viewmodel.RockPaperScissorsViewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val viewModel: RockPaperScissorsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RockPaperScissorsScreen(viewModel)
        }
    }
}