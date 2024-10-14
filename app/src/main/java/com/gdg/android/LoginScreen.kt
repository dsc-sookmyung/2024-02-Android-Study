package com.gdg.android

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginScreen(
    navController: NavController,
) {
    var department by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 40.dp, vertical = 40.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 140.dp, bottom = 40.dp),
            text = stringResource(R.string.tv_login_title),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            modifier = Modifier.padding(bottom = 18.dp),
            text = stringResource(R.string.tv_login_department),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            value = department,
            onValueChange = { department = it },
            placeholder = {
                Text(
                    text = stringResource(R.string.tv_login_department_placeholder),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )
        Text(
            modifier = Modifier.padding(bottom = 18.dp),
            text = stringResource(R.string.tv_login_name),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = { name = it },
            placeholder = {
                Text(
                    text = stringResource(R.string.tv_login_name_placeholder),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3A61B6),
            ),
            contentPadding = PaddingValues(vertical = 12.dp),
            onClick = {
                if (department.isNotBlank() && name.isNotBlank()) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.toast_login_success), Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate("main")
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.toast_login_failure),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        ) {
            Text(
                text = stringResource(id = R.string.btn_login_text),
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navController = rememberNavController()
    )
}