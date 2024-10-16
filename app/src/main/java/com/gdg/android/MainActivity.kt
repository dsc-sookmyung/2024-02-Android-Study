package com.gdg.android

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.gdg.android.ui.theme.GDGAndroidTheme

val hobbies = listOf(
    "🍿 혼자 영화 보기", "🛹 스케이트보드 타기", "🎨 그림 그리기", "🎮 게임하기", "📚 독서하기",
    "💥 만화 보기", "👩‍🍳 요리 하기", "🥁 드럼 연주하기", "🎧 음악 감상하기", "📺 애니 정주행하기"
)

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {
                composable("profile") {
                    ProfileScreen()
                }
                composable("login") {
                    LoginPage(navController = navController)
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(name: String = "조영서", major: String = "소프트웨어학부, 2학년", modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // 프로필 이미지
        AsyncImage(
            modifier = Modifier
                .padding(top = 30.dp)
                .clip(CircleShape)
                .size(130.dp),
            model = "https://avatars.githubusercontent.com/u/152948170?s=400&u=e554cfdf67c75f47e6ee8ab2680afcbe78fb4292&v=4",
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_launcher_background)
        )

        // 전공 텍스트
        Text(
            text = major,
            modifier = Modifier.padding(top = 20.dp),
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        // 이름 텍스트
        Text(
            text = name,
            modifier = Modifier.padding(top = 5.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        // 취미 제목 텍스트
        Text(
            text = "나의 취미",
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )

        // 취미 리스트
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(hobbies) { hobby ->
                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    fontSize = 14.sp,
                    text = hobby
                )
                // 구분선
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun LoginPage(navController: NavController? = null) {
    val context = LocalContext.current

    val majorTextValue = remember { mutableStateOf("") }
    val nameTextValue = remember { mutableStateOf("") }
    var majorError by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(30.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "안녕하세요, 여러분",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 학부 입력
        Text(
            text = "학부",
            fontSize = 16.sp,
            color = Color(0xFF1C1C1C),
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                TextField(
                    value = majorTextValue.value,
                    onValueChange = { majorTextValue.value = it },
                    label = { Text("학부를 입력해주세요", color = Color.Black) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFC4C4C4),
                        unfocusedContainerColor = Color(0xFFE0E0E0),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                )

                // 학부 입력 검증 메시지 표시
                if (majorError.isNotEmpty()) {
                    Text(text = majorError, color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 이름 입력
        Text(
            text = "이름",
            fontSize = 16.sp,
            color = Color(0xFF1C1C1C),
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                TextField(
                    value = nameTextValue.value,
                    onValueChange = { nameTextValue.value = it },
                    label = { Text("이름을 입력해주세요", color = Color.Black) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFC4C4C4),
                        unfocusedContainerColor = Color(0xFFE0E0E0),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                )

                // 이름 입력 검증 메시지 표시
                if (nameError.isNotEmpty()) {
                    Text(text = nameError, color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                // 입력 검증
                majorError = if (majorTextValue.value.isEmpty()) "학부를 입력해주세요." else ""
                nameError = if (nameTextValue.value.isEmpty()) "이름을 입력해주세요." else ""

                // 모두 입력된 경우에만 프로필 페이지로 이동
                if (majorTextValue.value.isNotEmpty() && nameTextValue.value.isNotEmpty()) {
                    Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()

                    // 토스트가 보여지는 후에 프로필 페이지로 이동하기 위한 딜레이 설정
                    navController?.currentBackStackEntry?.savedStateHandle?.set("navigateToProfile", true)

                    // 다음 프레임에서 프로필 페이지로 이동
                    navController?.navigate("profile")
                }
            },
            modifier = Modifier
                .padding(top = 20.dp)
                .width(320.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF313131),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "LOGIN",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = TextStyle(
                    letterSpacing = 5.sp
                )
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    GDGAndroidTheme {
        LoginPage()
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    GDGAndroidTheme {
        ProfileScreen()
    }
}
