// app/src/main/java/com/gdg/android/presentation/LoginPage.kt
package com.gdg.android.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import bodyLarge
import bodyMedium
import buttontext
import com.gdg.android.ui.theme.BackgroundBox

@Composable
fun LoginPage(
    navController: NavController? = null,
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current

    val majorTextValue = remember { mutableStateOf("") }
    val nameTextValue = remember { mutableStateOf("") }
    var majorError by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf("") }

    BackgroundBox {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
            TextFieldSection(label = "학부", textValue = majorTextValue, errorText = majorError)

            Spacer(modifier = Modifier.height(16.dp))

            // 이름 입력
            TextFieldSection(label = "이름", textValue = nameTextValue, errorText = nameError)

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    majorError = if (majorTextValue.value.isEmpty()) "학부를 입력해주세요." else ""
                    nameError = if (nameTextValue.value.isEmpty()) "이름을 입력해주세요." else ""

                    if (majorTextValue.value.isNotEmpty() && nameTextValue.value.isNotEmpty()) {
                        Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                        navController?.let {
                            it.navigate("main") {
                                popUpTo(it.graph.startDestinationId) { inclusive = true }
                            }
                            mainViewModel.saveAutoLoginState(context, true)
                        }
                    }
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(320.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF313131)
                )
            ) {
                Text(
                    text = "LOGIN",
                    style = buttontext
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun TextFieldSection(label: String, textValue: MutableState<String>, errorText: String) {
    val placeholderText = if (label.endsWith("이름")) "${label}을 입력해주세요" else "${label}를 입력해주세요"

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = bodyLarge
        )

        TextField(
            value = textValue.value,
            onValueChange = { textValue.value = it },
            label = { Text(placeholderText, color = Color.Black) },
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
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        )

        if (errorText.isNotEmpty()) {
            Text(
                text = errorText,
                style = bodyMedium,
                color = Color.Red
            )
        } else {
            Spacer(modifier = Modifier.height(20.dp)) // 오류 메시지가 없을 때 공간 유지
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    LoginPage(navController = null, mainViewModel = MainViewModel())
}