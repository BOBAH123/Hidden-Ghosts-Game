package com.game.hiddenghosts.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.game.hiddenghosts.ui.theme.RestartButtonBackground

@Composable
fun RestartGameButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 38.dp)
            .clip(RoundedCornerShape(24.dp))
            .height(44.dp),
        onClick = onClick,
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = RestartButtonBackground
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
}