package com.gdg.android.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auto_login")

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel // mainViewModel 변수를 미리 생성
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java] // mainViewModel 변수 정의

        lifecycleScope.launch {
            val isLoggedIn =
                mainViewModel.getAutoLoginState(applicationContext).first() // 자동 로그인 상태 확인
            setContent {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = if (isLoggedIn) "main" else "login" // 자동 로그인 여부에 따라 시작 화면 설정
                ) {
                    composable("login") {
                        LoginPage(navController, mainViewModel)
                    }
                    composable("main") {
                        ProfileScreen(navController, mainViewModel)
                    }
                    composable("user") {
                        UserScreen(navController, mainViewModel)
                    }
                    composable("userCreate") {
                        UserCreateScreen(navController)
                    }
                }
            }
        }
    }
}