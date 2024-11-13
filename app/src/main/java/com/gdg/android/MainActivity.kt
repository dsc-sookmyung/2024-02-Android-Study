package com.gdg.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.gdg.android.ui.theme.Gray600
import com.gdg.android.ui.theme.preBold1
import com.gdg.android.ui.theme.preReg
import com.gdg.android.ui.theme.preSemi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

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
                        SignInScreen(navController, mainViewModel)
                    }
                    composable("main") {
                        GreetingScreen(navController, mainViewModel)
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

@Composable
fun GreetingScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val subjects = listOf("네트워크보안", "컴퓨터특강", "데이터마이닝및분석", "파이썬데이터분석", "경영정보시스템")
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "서연", style = preBold1,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        AsyncImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(130.dp),
            model = "https://avatars.githubusercontent.com/u/166610834?s=400&u=568eacc2e4696d563a4fd732c148edba2196e4f6&v=4",
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_launcher_background)
        )

        Text(
            text = "소프트웨어융합전공",
            modifier = Modifier.padding(top = 10.dp),
            color = Gray600, style = preReg
        )

        Row() {
            Button(modifier = Modifier.padding(horizontal = 10.dp),
                onClick = {
                navController.navigate("user")
            }) { Text("유저 목록", style = preReg) }

            Button(onClick = {
                mainViewModel.saveAutoLoginState(
                    context,
                    false
                ) // mainViewModel의 saveAutoLoginState() 호출
                navController.navigate("login") {
                    popUpTo("login") { inclusive = true }
                }
            }
            ) {
                Text("로그아웃", style = preReg)
            }
        }


        Text(
            text = stringResource(R.string._24_2),
            modifier = Modifier.padding(top = 10.dp),
            color = Color.Black, style = preSemi
        )

        LazyColumn {
            items(subjects) { subject ->
                Text(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 10.dp),
                    text = subject, style = preReg, color = Gray600
                )
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
fun SignInScreen(navController: NavController, mainViewModel: MainViewModel) {
    val (department, setDepartment) = remember {
        mutableStateOf("")
    }

    val (name, setName) = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("안녕하세요, 여러분", fontSize = 30.sp, style = preBold1)
        Spacer(modifier = Modifier.height(30.dp))

        Text("학부", fontSize = 20.sp, style = preReg)
        TextField(value = department, onValueChange = setDepartment,
            placeholder = {
                Text(
                    "학부를 입력해주세요",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    style = preReg
                )
            })
        Spacer(modifier = Modifier.height(16.dp))

        Text("이름", fontSize = 20.sp)
        TextField(value = name, onValueChange = setName,
            placeholder = {
                Text(
                    "이름을 입력해주세요",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    style = preReg
                )
            })
        Spacer(modifier = Modifier.height(80.dp))

        Button(onClick = {
            Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show()
            navController.navigate("main")
            mainViewModel.saveAutoLoginState(
                context,
                true
            )
        }
        ) { Text("로그인", style = preReg) }


    }

}