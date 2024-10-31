// MainActivity.kt
package com.gdg.android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()


            // NavHost 설정
            NavHost(
                navController = navController,
                startDestination = "login"
            ) {
                composable("login") {
                    LoginPage(navController)
                }
                composable("profile") {
                    ProfileScreen(navController)
                }
                composable("user") {
                    UserScreen(navController)
                }
            }
                }
            }
        }
