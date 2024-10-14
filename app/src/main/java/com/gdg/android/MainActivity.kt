package com.gdg.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gdg.android.ui.theme.GDGAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            GDGAndroidTheme {
                NavHost(
                    navController = navController,
                    startDestination = "login",
                ) {
                    composable("login") {
                        LoginScreen(navController)
                    }
                    composable("main") {
                        MainScreen(navController)
                    }
                }
            }
        }
    }
}
