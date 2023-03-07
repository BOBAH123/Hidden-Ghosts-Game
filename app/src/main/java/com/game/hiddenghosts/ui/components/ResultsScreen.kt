package com.game.hiddenghosts.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.game.hiddenghosts.navigation.Screen
import com.game.hiddenghosts.ui.theme.GamePrimary

@Composable
fun ResultsScreen(
    score: Int?,
    level: Int?,
    navController: NavHostController,
    result: Boolean?
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(top = 210.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$score",
                color = Color.White,
                fontSize = 72.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight(700),
            )
            Text(
                text = "Score",
                color = Color.White,
                fontSize = 32.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight(700),
            )
        }

        Button(
            modifier = Modifier
                .padding(bottom = 36.dp)
                .clip(RoundedCornerShape(24.dp))
                .align(Alignment.BottomCenter)
                .width(240.dp)
                .height(48.dp),
            onClick = {
                level?.let {
                    navController.popBackStack()
                    if (result == true) {
                        if (it < 5) {
                            navController.navigate(
                                Screen.GameField.withArgs(
                                    (level + 1).toString()
                                )
                            )
                        } else {
                            navController.navigate(Screen.GameField.route)
                        }
                    } else
                        navController.navigate(Screen.GameField.withArgs(it.toString()))
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            )
        ) {
            Text(
                text = "Next level",
                color = GamePrimary,
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}
