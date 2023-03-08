package com.game.hiddenghosts.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.hiddenghosts.R
import com.game.hiddenghosts.model.GhostCardModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GhostGameViewModel : ViewModel() {
    private var tries: Int = 0
    private var ghostsNumber = 0
    var fieldWidth = 4
    var fieldHeight = 4
    var score = mutableStateOf(0)
    var resultVisibility = mutableStateOf(false)
    var successResult = mutableStateOf(false)
    var cards = mutableStateOf<List<GhostCardModel>>(listOf())

    private val ghostImages = listOf(
        R.drawable.icon_ghost_1,
        R.drawable.icon_ghost_2,
        R.drawable.icon_ghost_3,
        R.drawable.icon_ghost_4,
        R.drawable.icon_ghost_5,
    )

    fun loadGameField(level: Int) {
        resultVisibility.value = false
        successResult.value = false
        score.value = 0
        ghostsNumber = level + 3
        tries = ghostsNumber
        when {
            level < 4 -> {
                fieldWidth = 4
                fieldHeight = ghostsNumber
            }
            else -> {
                fieldWidth = 5
                fieldHeight = ghostsNumber - 1
            }
        }
        val cardsList = mutableListOf<GhostCardModel>()
        viewModelScope.launch {
            cardsList.addAll(generateCardsList())
        }
        cards.value = cardsList
    }

    private suspend fun generateCardsList(): List<GhostCardModel> {
        val cardsList = mutableListOf<GhostCardModel>()
        val addWrongCards = viewModelScope.async {
            repeat(fieldWidth * fieldHeight - ghostsNumber) {
                cardsList.add(
                    GhostCardModel(
                        isVisible = mutableStateOf(false)
                    )
                )
            }
        }
        val addCorrectCards = viewModelScope.async {
            repeat(ghostsNumber) {
                cardsList.add(
                    GhostCardModel(
                        icon = ghostImages[
                                if (it < ghostImages.size) it
                                else it - ghostImages.size
                        ],
                        isRight = true,
                        isVisible = mutableStateOf(true)
                    )
                )
            }
            cardsList.shuffle()
        }
        addWrongCards.await()
        addCorrectCards.await()
        return cardsList
    }

    fun onClick(value: Boolean) {
        if (value) {
            score.value += 5
        }
        tries--
        if (tries == 0) {
            resultVisibility.value = true
            cards.value.map {
                if (it.isRight)
                    it.isVisible.value = true
                it.isClickable = false
            }
            if ((score.value / 5) == ghostsNumber)
                successResult.value = true
        }
    }
}