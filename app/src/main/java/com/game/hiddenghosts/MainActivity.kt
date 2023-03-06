package com.game.hiddenghosts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.game.hiddenghosts.navigation.SetupNavHost
import com.game.hiddenghosts.ui.theme.HiddenGhostsTheme

class MainActivity : ComponentActivity() {
    private val viewModel: GhostGameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            HiddenGhostsTheme {
                val navController = rememberNavController()
                SetupNavHost(navController = navController, viewModel)
            }
        }
    }
}
