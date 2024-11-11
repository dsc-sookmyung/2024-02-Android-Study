package com.gdg.android.presentation.main

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gdg.android.presentation.login.LoginScreen
import com.gdg.android.presentation.user.UserCreateScreen
import com.gdg.android.presentation.user.UserScreen
import com.gdg.android.ui.theme.GDGAndroidTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        enableEdgeToEdge()
        lifecycleScope.launch {
            val isLoggedIn = mainViewModel.getAutoLoginState(applicationContext).first() // 자동 로그인 상태 확인
            setContent {
                val navController = rememberNavController()
                GDGAndroidTheme {
                    NavHost(
                        navController = navController,
                        startDestination = if (isLoggedIn) "main" else "login" // 자동 로그인 여부에 따라 시작 화면 설정
                    ) {
                        composable("login") {
                            LoginScreen(navController, mainViewModel)
                        }
                        composable("main") {
                            MainScreen(navController, mainViewModel)
                        }
                        composable("user") {
                            UserScreen(navController)
                        }
                        composable("userCreate") {
                            UserCreateScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
