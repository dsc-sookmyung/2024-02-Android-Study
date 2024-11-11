package com.gdg.android.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.gdg.android.main.MainActivity
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    val name = remember { mutableStateOf("") }
    val department = remember { mutableStateOf("") }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    fun onLoginClick() {
        coroutineScope.launch {
            if (name.value.isNotEmpty() && department.value.isNotEmpty()) {
                Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                (context as? MainActivity)?.saveAutoLoginState(context, true)
                navController.navigate("main") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
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
                onClick = { onLoginClick()
                    (context as? MainActivity)?.lifecycleScope?.launch {
                        (context as? MainActivity)?.saveAutoLoginState(context, true)
                    }
                    navController.navigate("main") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true}
                    }
                          },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "로그인",
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}