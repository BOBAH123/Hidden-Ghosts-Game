package com.game.hiddenghosts.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.MutableState
import com.game.hiddenghosts.R

data class GhostCardModel(
    @DrawableRes
    var icon: Int = R.drawable.icon_ghost_wrong,
    var isSelected: Boolean = false,
    var isRight: Boolean = false,
    var isClickable: Boolean = false,
    var isVisible: MutableState<Boolean>
)
