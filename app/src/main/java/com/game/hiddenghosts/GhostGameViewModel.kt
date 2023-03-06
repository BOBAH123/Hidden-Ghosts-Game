package com.game.hiddenghosts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GhostGameViewModel : ViewModel() {
    private val ghosts: MutableLiveData<MutableList<GhostCardModel>> by lazy {
        MutableLiveData<MutableList<GhostCardModel>>()
    }

    private val ghostImages = listOf(
        R.drawable.icon_ghost_1,
        R.drawable.icon_ghost_2,
        R.drawable.icon_ghost_3,
        R.drawable.icon_ghost_4,
        R.drawable.icon_ghost_5,
    )

    var tries: Int = 0
    var ghostsNumber = 0
    var fieldWidth = 4
    var fieldHeight = 0

    fun getCards(): LiveData<MutableList<GhostCardModel>> = ghosts

    fun loadGhostCards(level: Int) {
        ghostsNumber = level + 3
        tries = ghostsNumber
        when {
            level < 4 -> fieldHeight = ghostsNumber
            else -> {
                fieldWidth = 5
                fieldHeight = ghostsNumber - 1
            }
        }
        val list = mutableListOf<GhostCardModel>()
        repeat(fieldWidth * fieldHeight - ghostsNumber) {
            list.add(GhostCardModel())
        }
        repeat(ghostsNumber) {
            list.add(
                GhostCardModel(
                    icon = ghostImages[Random.nextInt(ghostImages.size)],
                    isRight = true
                )
            )
        }
        ghosts.value = list.apply { shuffle() }
    }
}