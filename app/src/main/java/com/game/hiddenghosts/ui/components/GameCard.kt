package com.game.hiddenghosts.ui.components

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import androidx.compose.ui.unit.dp
import com.game.hiddenghosts.GhostCardModel
import com.game.hiddenghosts.ui.theme.CardStandardBackground
import com.game.hiddenghosts.ui.theme.SelectedCellBackground
import com.game.hiddenghosts.ui.theme.WrongCellBackground
import kotlinx.coroutines.delay


@Composable
fun GameCard(
    modifier: Modifier = Modifier,
    data: GhostCardModel,
    onClick: (Boolean) -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    val transition = updateTransition(targetState = isVisible, label = "")

    val alpha by transition.animateFloat(
        transitionSpec = {
            if (false isTransitioningTo true) {
                tween(durationMillis = 500)
            } else {
                if (data.isSelected) {
                    TweenSpec(0)
                } else {
                    tween(durationMillis = 500, delayMillis = 1000)
                }
            }
        }, label = ""
    ) {
        if (it) 1f else 0f
    }

    LaunchedEffect(isVisible) {
        if (isVisible) {
            delay(1000L)
            if (!data.isSelected) {
                isVisible = false
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(all = 2.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(CardStandardBackground)
            .height(72.dp)
            .width(72.dp)
            .clickable {
                if (!data.isSelected) {
                    isVisible = true
                    data.isSelected = true
                    onClick(data.isRight)
                }
            }
            .then(modifier)
    ) {
        Icon(
            modifier = Modifier
                .alpha(alpha)
                .fillMaxSize()
                .background(
                    if (data.isRight) SelectedCellBackground
                    else if (data.isSelected) WrongCellBackground
                    else Color.Transparent
                ),
            imageVector = ImageVector.vectorResource(
                id = data.icon
            ),
            tint = Color.Unspecified,
            contentDescription = "Wrong choice"
        )
    }
}