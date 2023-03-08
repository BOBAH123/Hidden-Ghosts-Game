package com.game.hiddenghosts.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.game.hiddenghosts.model.GhostCardModel

@Composable
fun GameField(
    fieldWidth: Int,
    cards: List<GhostCardModel>,
    onClick: (Boolean) -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(isVisible) {
        isVisible = true
    }
    LazyVerticalGrid(columns = GridCells.Fixed(fieldWidth)) {
        items(cards.size) { cardIndex ->
            AnimatedVisibility(
                visible = isVisible,
                modifier = Modifier
                    .clip(RoundedCornerShape(2.dp))
                    .height(72.dp)
                    .width(72.dp)
            ) {
                GameCard(
                    data = cards[cardIndex]
                ) {
                    onClick(it)
                }
            }
        }
    }
}
