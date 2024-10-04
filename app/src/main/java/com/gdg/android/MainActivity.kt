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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gdg.android.ui.theme.GDGAndroidTheme

// 초기 셋팅
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val favorites = listOf(
                "야구 직관⚾️", "빵집 다니기🥐", "노래 듣기🎧", "여행 다니기✈️",
                "친구 만나기👭", "독립 서점 찾기📚", "필름 카메라로 사진 찍기📷", "영화 보기🍿"
            )
            GDGAndroidTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 150.dp)
                        .background(Color.White)
                ) {
                    Greeting(
                        name = "김나현",
                        major = "소프트웨어학부, 3학년",
                        myFavorites = "My favorites"
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
        }
    }
}

@Composable
fun Greeting(name: String, major: String, myFavorites: String, modifier: Modifier = Modifier) {
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
        Text(
            text = myFavorites,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 30.dp, top = 10.dp),
            color = Color.Black,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val favorites = listOf(
        "야구 직관⚾️", "빵집 다니기🥐", "노래 듣기🎧", "여행 다니기✈️",
        "친구 만나기👭", "독립 서점 찾기📚", "필름 카메라로 사진 찍기📷", "영화 보기🍿"
    )
    GDGAndroidTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp)
                .background(Color.White)
        ) {
            Greeting(
                name = "김나현",
                major = "소프트웨어학부, 3학년",
                myFavorites = "My favorites"
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
}
