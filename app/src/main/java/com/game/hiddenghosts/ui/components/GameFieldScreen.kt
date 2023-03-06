package com.game.hiddenghosts.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.game.hiddenghosts.GhostCardModel
import com.game.hiddenghosts.GhostGameViewModel
import com.game.hiddenghosts.navigation.Screen
import com.game.hiddenghosts.ui.theme.RestartButtonBackground

@Composable
fun GameFieldScreen(
    level: Int,
    viewModel: GhostGameViewModel,
    navController: NavHostController
) {
    var score by remember {
        mutableStateOf(0)
    }

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
                navController.navigate(Screen.GameField.withArgs(level.toString()))
                navController.popBackStack()
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
            cards = viewModel.getCards().value!!
        ) {
            if (it) {
                score += 5
            }
            viewModel.tries--
            if (viewModel.tries == 0
                && (score / 5) == viewModel.ghostsNumber
            ) {
                navController.navigate(Screen.Home.route)
                navController.popBackStack()
            } else if (viewModel.tries == 0) {
                navController.navigate(Screen.GameField.withArgs(level.toString()))
                navController.popBackStack()
                score = 0
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameField(
    fieldWidth: Int,
    fieldHeight: Int,
    cards: List<GhostCardModel>,
    onClick: (Boolean) -> Unit
) {
    LazyVerticalGrid(cells = GridCells.Fixed(fieldWidth)) {
        items(fieldWidth * fieldHeight) { cardIndex ->
            GameCard(
                data = cards[cardIndex]
            ) { onClick(it) }
        }
    }
}
