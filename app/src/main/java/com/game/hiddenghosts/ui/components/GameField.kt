package com.game.hiddenghosts.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import com.game.hiddenghosts.GhostCardModel

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
