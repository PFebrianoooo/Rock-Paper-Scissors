package com.putrapebrianonurba.rockpaperscissors.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.putrapebrianonurba.rockpaperscissors.viewmodel.RockPaperScissorsViewModel
import com.putrapebrianonurba.rockpaperscissors.R
import androidx.compose.runtime.getValue

@Composable
fun RockPaperScissorsScreen(viewModel: RockPaperScissorsViewModel) {
    val rpsUIState by viewModel.rpsUIState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        // Opening image -> Computer Choice
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentAlignment = Alignment.Center,
        ) {
            when {
                !rpsUIState.isPlaying -> {
                    Image(
                        painter = painterResource(R.drawable.ic_logo_rps),
                        contentDescription = "PlaceHolder",
                        modifier = Modifier
                            .fillMaxSize()
                            .rotate(180f)
                    )
                }
                rpsUIState.computer == null -> {
                    Image(
                        painter = painterResource(R.drawable.ic_thinking),
                        contentDescription = "Thinking",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                } else -> {
                rpsUIState.computer?.let {
                    Image(
                        painter = painterResource(it.icon),
                        contentDescription = "Computer Choice",
                        modifier = Modifier
                            .fillMaxSize()
                            .rotate(180f)
                    )
                }
                }
            }
        }

        if (!rpsUIState.isPlaying) {
            // Lets Play -> Result, Score, Play Again Button
            Text(
                text = "Let's Play!",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 50.sp
            )
        } else {
            // it will displayed after user chosen and computer get his chosen, get delay little
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = rpsUIState.result,
                    modifier = Modifier ,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 30.sp
                )

                Row {
                    Text(
                        text = "Win: ${rpsUIState.win}" ,
                        modifier = Modifier ,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Draw: ${rpsUIState.draw}",
                        modifier = Modifier ,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Lose: ${rpsUIState.lose}",
                        modifier = Modifier ,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        // Shadow layer
                        Box(
                            modifier = Modifier
                                .offset(x = 4.dp, y = 4.dp) // ubah ini buat lebar arah kanan & bawah
                                .height(40.dp)
                                .width(150.dp)
                                .background(
                                    color = Color(0x50C4C1C1), // ubah transparansi (#66 = lebih tebal)
                                    shape = RoundedCornerShape(10.dp)
                                )
                        )

                        // Main button
                        Button(
                            onClick = {
                                viewModel.playAgain()
                            },
                            modifier = Modifier
                                .height(40.dp)
                                .width(150.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF9C27B0),
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Play Again",
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        // Shadow layer
                        Box(
                            modifier = Modifier
                                .offset(x = 4.dp, y = 4.dp) // ubah ini buat lebar arah kanan & bawah
                                .height(40.dp)
                                .width(150.dp)
                                .background(
                                    color = Color(0x50C4C1C1), // ubah transparansi (#66 = lebih tebal)
                                    shape = RoundedCornerShape(10.dp)
                                )
                        )

                        // Main button
                        Button(
                            onClick = {
                                viewModel.resetGame()
                            },
                            modifier = Modifier
                                .height(40.dp)
                                .width(150.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFE91E63),
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Reset",
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp
                            )
                        }
                    }

                }
            }
        }

        // User Choice -> Result user choice
        if (!rpsUIState.isPlaying) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Pick an option
                Text(
                    text = "Pick an option",
                    modifier = Modifier ,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    fontSize = 25.sp
                )

                // Options
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    viewModel.choice.forEach { choice ->
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(110.dp)
                                .background(Color(0xFF9C27B0))
                                .clickable {
                                    viewModel.onUserChoice(choice)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(choice.icon),
                                contentDescription = choice.name,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(15.dp)
                            )
                        }
                    }
                }
            }
        } else {
            rpsUIState.player?.let { player ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(player.icon),
                        contentDescription = player.name,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}