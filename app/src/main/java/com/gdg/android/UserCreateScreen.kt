package com.gdg.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// UserCreateScreen.kt

@Composable
fun UserCreateScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current
    val roomDB = UserDatabase.getDatabase(context)
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(40.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            text = "유저 등록하기",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Icon(
            modifier = Modifier
                .fillMaxWidth()
                .size(150.dp),
            imageVector = Icons.Default.Person,
            contentDescription = null,
            tint = Color.Gray
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 15.dp),
            value = name,
            onValueChange = { name = it },
            placeholder = {
                Text(
                    text = "이름을 입력해주세요",
                    color = Color.Gray
                )
            }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            value = email,
            onValueChange = { email = it },
            placeholder = {
                Text(
                    text = "이메일을 입력해주세요",
                    color = Color.Gray
                )
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                coroutineScope.launch {
                    if (name.isNotEmpty() && email.isNotEmpty()) {
                        withContext(Dispatchers.IO) {
                            val newUser = UserEntity(name = name, email = email)
                            roomDB.userDao().insert(newUser) // 새로운 유저 데이터 저장
                        }
                    }
                }
                navController.popBackStack() // 데이터 저장 후 이전 화면으로 되돌아가기
            }
        ) {
            Text(
                text = "등록하기"
            )
        }
    }
}

@Preview
@Composable
fun UserCreateScreenPreview() {
    UserCreateScreen(navController = rememberNavController())
}