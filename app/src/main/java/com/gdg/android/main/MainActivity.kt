package com.gdg.android.main

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.gdg.android.user.UserScreen
import kotlinx.coroutines.launch
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHost
import com.gdg.android.login.LoginScreen
import com.gdg.android.ui.theme.GDGAndroidTheme
import com.gdg.android.ui.theme.Pink80
import com.gdg.android.ui.theme.button3Bold
import com.gdg.android.user.UserCreateScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class MainActivity : ComponentActivity() {
    /*
    private val AUTO_LOGIN_KEY = booleanPreferencesKey("auto_login")

    //자동 로그인 상태 저장 함수
    suspend fun saveAutoLoginState(context: Context, isLoggedIn: Boolean) {
        //key에 로그인 여부 나타내는 불리언 값 datastore에 저장
        context.dataStore.edit { preferences ->
            preferences[AUTO_LOGIN_KEY] = isLoggedIn
        }
    }


    fun getAutoLoginState(context: Context): Flow<Boolean> {
        return context.dataStore.data
            .map { preferences ->
                preferences[AUTO_LOGIN_KEY] ?: false
            }
    }
     */
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        lifecycleScope.launch {
            val isLoggedIn =
                mainViewModel.getAutoLoginState(applicationContext).first()
            setContent {
                val navController = rememberNavController()
                GDGAndroidTheme {
                    NavHost(
                        navController = navController,
                        startDestination = if (isLoggedIn) "main" else "login"
                    ) {
                        composable("login") {
                            LoginScreen(navController, mainViewModel)
                        }
                        composable("main") {
                            MainScreen(navController, mainViewModel)
                        }
                        composable("users") {
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

}

@Composable
fun MainScreen(
    navController: NavController = rememberNavController(),
    mainViewModel: MainViewModel = MainViewModel()
) {
    val hobbies = listOf(
        "독서", "영화 감상", "음악 감상", "산책", "뜨개질", "기타 연주"
    )

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ProfileHeader(
            imageUrl = "https://avatars.githubusercontent.com/u/140608054?v=4",
            name = "이현진",
            status = "숙명여자대학교 인공지능공학부 23학번입니다."
        )

        Row() {
            Button(
                onClick = {
                    navController.navigate("users") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Pink80,
                    contentColor = White
                )
            ) {
                Text(
                    text = "유저 목록",
                    style = button3Bold
                ) }
        }

        Button(
            onClick = {
                mainViewModel.saveAutoLoginState(context, false)
                navController.navigate("login") {
                    popUpTo("login") { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Pink80,
                contentColor = White
            )
        ) {
            Text(
                text = "로그아웃",
                style = button3Bold
            )
        }

        Text(
            text = "취미",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn {
            items(hobbies) { hobby: String ->
                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = hobby
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = LightGray,
                    thickness = 1.dp
                )

            }
        }
    }
}

@Composable
fun ProfileHeader(imageUrl: String, name: String, status: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Profile Image",
            modifier = Modifier
                .padding(8.dp)
                .clip(CircleShape)
                .size(130.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = name,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = status,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(navController = rememberNavController())
}