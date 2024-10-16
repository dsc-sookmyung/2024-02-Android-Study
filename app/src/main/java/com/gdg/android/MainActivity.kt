package com.gdg.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "login") {
                composable("login") { LoginScreen(navController) }
                composable("main") { MainScreen() }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    //상태 변수
    val name = remember { mutableStateOf("") }
    val department = remember { mutableStateOf("") }

    //coroutinescope 이용해 toast 메시지 표시
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    fun onLoginClick() {
        coroutineScope.launch {
            if (name.value.isNotEmpty() && department.value.isNotEmpty()) {
                Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                //메인 페이지로 이동
                navController.navigate("main")
            } else {
                Toast.makeText(context, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "로그인",
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            //학부 입력
            TextField(
                value = department.value,
                onValueChange = { department.value = it },
                placeholder = {
                    Text(text = "학부를 입력해주세요", fontSize = 14.sp, color = Color.Gray)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            TextField(
                value = name.value,
                onValueChange = { name.value = it },
                placeholder = {
                    Text(text = "이름을 입력해주세요", fontSize = 14.sp, color = Color.Gray)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)

            )

            Button(
                onClick = { onLoginClick() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "로그인")
            }
        }

    }
}

@Composable
fun MainScreen() {
    val hobbies = listOf(
        "독서", "영화 감상", "음악 감상", "산책", "뜨개질", "기타 연주"
    )

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

        Spacer(modifier = Modifier.height(24.dp))

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
    MainScreen()
}