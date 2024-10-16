package com.gdg.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.gdg.android.ui.theme.GDGAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "signIn",
            ) {
                composable("signIn") {
                    SignInScreen(navController)
                }
                composable("greeting") {
                    GreetingScreen(
                        name = "백서연",
                        depart = "소프트웨어융합전공",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(name: String, depart: String, modifier: Modifier = Modifier) {
    val subjects = listOf("네트워크보안", "컴퓨터특강", "데이터마이닝및분석", "파이썬데이터분석", "경영정보시스템")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "$name",
            modifier = modifier.padding(bottom = 10.dp)
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
            text = "$depart",
            modifier = modifier.padding(top = 10.dp),
            color = Color.Gray,
        )

        Text(
            text = stringResource(R.string._24_2),
            modifier = modifier.padding(top = 10.dp),
            color = Color.Black,
        )

        LazyColumn {
            items(subjects) { subject ->
                Text(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 5.dp),
                    text = subject
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
fun SignInScreen(navController: NavController) {
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
        Text("안녕하세요, 여러분", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Text("학부", fontSize = 20.sp)
        TextField(value = department, onValueChange = setDepartment,
            placeholder = {
                Text(
                    "학부를 입력해주세요",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            })
        Spacer(modifier = Modifier.height(16.dp))

        Text("이름", fontSize = 20.sp)
        TextField(value = name, onValueChange = setName,
            placeholder = {
                Text(
                    "이름을 입력해주세요",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            })
        Spacer(modifier = Modifier.height(80.dp))

        Button(onClick = {
            if (name.isNotEmpty() and department.isNotEmpty()) {
                Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                navController.navigate("greeting")
            }
        }) { Text("로그인") }

    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GDGAndroidTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 30.dp)
        ) {
            GreetingScreen(
                name = "백서연",
                depart = "소프트웨어융합전공",
                modifier = Modifier.padding(16.dp)
            )


        }
    }
}