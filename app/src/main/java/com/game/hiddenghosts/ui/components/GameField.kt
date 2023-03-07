package com.game.hiddenghosts.ui.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import com.game.hiddenghosts.model.GhostCardModel

@Composable
fun GameField(
    fieldWidth: Int,
    cards: List<GhostCardModel>,
    onClick: (Boolean) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(fieldWidth)) {
        items(cards.size) { cardIndex ->
            GameCard(
                data = cards[cardIndex]
            ) { onClick(it) }
        }
    }
}
