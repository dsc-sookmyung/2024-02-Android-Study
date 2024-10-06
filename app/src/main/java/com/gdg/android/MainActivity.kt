package com.gdg.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gdg.android.ui.theme.GDGAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val subjects = listOf("네트워크보안", "컴퓨터특강", "데이터마이닝및분석", "파이썬데이터분석", "경영정보시스템")
            GDGAndroidTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp)
                        .background(Color.White)
                ) {
                    Greeting(
                        name = "백서연",
                        depart = "소프트웨어융합전공",
                        modifier = Modifier.padding(16.dp)
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
        }
    }
}

@Composable
fun Greeting(name: String, depart: String, modifier: Modifier = Modifier) {
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
            text = "24-2 수강 과목",
            modifier = modifier.padding(top = 10.dp),
            color = Color.Black,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val subjects = listOf("네트워크보안", "컴퓨터특강", "데이터마이닝및분석", "파이썬데이터분석", "경영정보시스템")
    GDGAndroidTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
                .background(Color.White)
        ) {
            Greeting(
                name = "백서연",
                depart = "소프트웨어융합전공",
                modifier = Modifier.padding(16.dp)
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
}