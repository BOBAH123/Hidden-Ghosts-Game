package com.game.hiddenghosts.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.game.hiddenghosts.GhostCardModel
import com.game.hiddenghosts.ui.theme.CardStandardBackground


@Composable
fun GameCard(
    modifier: Modifier = Modifier,
    data: GhostCardModel,
    onClick: (Boolean) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(2.dp))
            .padding(all = 2.dp)
            .background(CardStandardBackground)
            .height(72.dp)
            .width(72.dp)
            .clickable {
                if (!data.isSelected) {
                    data.isSelected = true
                    onClick(data.isRight)
                }
            }
            .then(modifier)
    ) {
//        if (data.isSelected) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = data.icon
                ),
                tint = Color.White,
                contentDescription = "Wrong choice"
            )
//        }
    }
}