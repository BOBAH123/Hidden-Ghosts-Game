package com.game.hiddenghosts.ui.components

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.game.hiddenghosts.R
import com.game.hiddenghosts.navigation.Screen
import com.game.hiddenghosts.ui.theme.Animations
import com.game.hiddenghosts.viewModel.GhostGameViewModel
import kotlinx.coroutines.delay

@Composable
fun GameFieldScreen(
    level: Int,
    viewModel: GhostGameViewModel,
    navController: NavHostController
) {
    LaunchedEffect(level) {
        viewModel.loadGhostCards(level)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${viewModel.score.value}",
                fontWeight = FontWeight(700),
                fontSize = 32.sp,
                lineHeight = 40.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 40.dp)
            )
            RestartGameButton {
                navController.popBackStack()
                navController.navigate(Screen.GameField.withArgs(level.toString()))
            }
            GameField(
                fieldWidth = viewModel.fieldWidth,
                cards = viewModel.cards.value,
                onClick = { viewModel.onClick(it) }
            )
        }
        LaunchedEffect(viewModel.resultVisibility.value) {
            if (viewModel.resultVisibility.value) {
                delay(Animations.delayDuration.toLong())
                navController.popBackStack()
                navController.navigate(
                    Screen.Results.route + "?level=$level" +
                            "&score=${viewModel.score.value}" +
                            "&result=${viewModel.successResult.value}"
                )
            }
        }
        val transition =
            updateTransition(targetState = viewModel.resultVisibility.value, label = "")

        val alpha by transition.animateFloat(
            transitionSpec = {
                if (false isTransitioningTo true) {
                    tween(durationMillis = Animations.animationDuration)
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
                id = if (viewModel.successResult.value) R.drawable.icon_correct_result_mark
                else R.drawable.icon_incorrect_result_mark
            ),
            tint = Color.Unspecified,
            contentDescription = null
        )
    }
}
