package com.gdg.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gdg.android.ui.theme.GDGAndroidTheme
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gdg.android.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {
                composable("login") { LoginScreen(navController) }
                composable("main") { MainScreen(navController) }
                composable("user") { UserScreen(navController) }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: androidx.navigation.NavHostController) {
    val context = LocalContext.current
    var department by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "안녕하세요, 여러분",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)

        )
        Column(
            modifier = Modifier.padding(horizontal = 40.dp)
        ) {
            Text(
                text = "학부",
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Start)
            )
            TextField(
                value = department,
                onValueChange = { department = it },
                placeholder = { Text("학부를 입력해주세요", fontSize = 14.sp, color = Color.Gray) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Text(
                text = "이름",
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 10.dp, bottom = 10.dp)
            )
            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("이름을 입력해주세요", fontSize = 14.sp, color = Color.Gray) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    if (department.isNotEmpty() && name.isNotEmpty()) {
                        Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                        navController.navigate("main")
                    } else {
                        Toast.makeText(context, "학부와 이름을 모두 입력해주세요", Toast.LENGTH_SHORT).show()
                    }
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RectangleShape,
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "로그인", fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Composable
fun Greeting(name: String, major: String, myFavorites: String, modifier: Modifier = Modifier, navController: androidx.navigation.NavController) {
    val favorites = listOf(
        "야구 직관⚾️", "빵집 다니기🥐", "노래 듣기🎧", "여행 다니기✈️",
        "친구 만나기👭", "독립 서점 찾기📚", "필름 카메라로 사진 찍기📷", "영화 보기🍿"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(130.dp),
            model = "https://avatars.githubusercontent.com/u/177762020?s=400&u=d6f59ce663b67e0590b4a9ff3fc032a7c227edef&v=4",
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_launcher_background)
        )
        Text(
            text = major,
            modifier = modifier.padding(top = 20.dp),
            color = Color.Gray
        )
        Text(
            text = name,
            modifier = modifier
                .padding(top = 10.dp),
            fontSize = 24.sp
        )

        Button(
            onClick = { navController.navigate("user") }, // UserScreen으로 이동
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(0.3f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RectangleShape

        ) {
            Text(text = "유저 목록")
        }

        Text(
            text = myFavorites,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 30.dp, top = 10.dp),
            color = Color.Black,
            fontSize = 20.sp
        )

        LazyColumn {
            items(favorites) { favorite ->
                Text(
                    modifier = Modifier.padding(vertical = 15.dp, horizontal = 30.dp),
                    text = favorite
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun MainScreen(navController: androidx.navigation.NavController) {
    GDGAndroidTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 150.dp)
        ) {
            Greeting(
                name = "김나현",
                major = "소프트웨어학부, 3학년",
                myFavorites = "My favorites",
                navController = navController
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen(navController = rememberNavController())
}
