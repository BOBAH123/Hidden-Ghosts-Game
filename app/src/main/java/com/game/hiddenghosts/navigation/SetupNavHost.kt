package com.game.hiddenghosts.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.game.hiddenghosts.GhostGameViewModel
import com.game.hiddenghosts.ui.components.GameFieldScreen
import com.game.hiddenghosts.ui.theme.GamePrimary

@Composable
fun SetupNavHost(navController: NavHostController, viewModel: GhostGameViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            Box(modifier = Modifier.fillMaxSize()) {
                Button(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .align(Alignment.Center)
                        .width(240.dp)
                        .height(48.dp),
                    onClick = {
                        navController.navigate(Screen.GameField.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    )
                ) {
                    Text(
                        text = "Start game",
                        color = GamePrimary,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
            }
        }
        composable(route = Screen.GameField.route + "?level={level}",
            arguments = listOf(
                navArgument("level") {
                    type = NavType.IntType
                    defaultValue = 5
                    nullable = false
                }
            )) {
            it.arguments?.getInt("level")?.let { level ->
                viewModel.loadGhostCards(level)
                GameFieldScreen(level = level, viewModel, navController)
            }
        }
    }
}
