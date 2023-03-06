package com.game.hiddenghosts.ui.components

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.game.hiddenghosts.GhostCardModel
import com.game.hiddenghosts.GhostGameViewModel
import com.game.hiddenghosts.R
import com.game.hiddenghosts.navigation.Screen
import com.game.hiddenghosts.ui.theme.RestartButtonBackground
import kotlinx.coroutines.delay

@Composable
fun GameFieldScreen(
    level: Int,
    viewModel: GhostGameViewModel,
    cards: List<GhostCardModel>,
    navController: NavHostController
) {
    var score by remember {
        mutableStateOf(0)
    }
    var resultVisibility by remember { mutableStateOf(false) }
    var successResult by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$score",
                fontWeight = FontWeight(700),
                fontSize = 32.sp,
                lineHeight = 40.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 40.dp)
            )
            Button(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 38.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .height(44.dp),
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.GameField.withArgs(level.toString()))
                    score = 0
                },
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = RestartButtonBackground,
                    contentColor = RestartButtonBackground
                )
            ) {
                Text(
                    text = "Restart",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight(600),
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .align(Alignment.CenterVertically)
                )
            }
            GameField(
                fieldWidth = viewModel.fieldWidth,
                fieldHeight = viewModel.fieldHeight,
                cards = cards
            ) {
                if (it) {
                    score += 5
                }
                viewModel.tries--
                if (viewModel.tries == 0) {
                    resultVisibility = true
                    if ((score / 5) == viewModel.ghostsNumber)
                        successResult = true
                }
            }
        }
        LaunchedEffect(resultVisibility) {
            if (resultVisibility) {
                delay(1000L)
                navController.popBackStack()
                navController.navigate(
                    Screen.Results.route + "?level=${level}" +
                            "&score=${score}" +
                            "&result=${successResult}"
                )
            }
        }
        val transition = updateTransition(targetState = resultVisibility, label = "")

        val alpha by transition.animateFloat(
            transitionSpec = {
                if (false isTransitioningTo true) {
                    tween(durationMillis = 500)
                } else {
                    TweenSpec(0)
                }
            }, label = ""
        ) {
            if (it) 1f else 0f
        }
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .alpha(alpha),
            imageVector = ImageVector.vectorResource(
                id = if (successResult) R.drawable.icon_correct_result_mark
                else R.drawable.icon_incorrect_result_mark
            ),
            tint = Color.Unspecified,
            contentDescription = ""
        )
    }
}
