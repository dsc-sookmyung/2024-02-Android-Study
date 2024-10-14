package com.gdg.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.gdg.android.ui.theme.GDGAndroidTheme

@Composable
fun MainScreen(
    navController: NavController,
) {
    val hobbies =
        listOf("독서", "영화 감상", "음악 감상", "요리", "운동", "코딩", "게임 하기", "여행", "친구들과 수다 떨기", "쇼핑")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 50.dp, start = 40.dp, end = 40.dp),
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(180.dp),
                model = stringResource(R.string.img_main_url),
                contentDescription = stringResource(R.string.img_main_description),
                placeholder = painterResource(id = R.drawable.ic_launcher_background)
            )
            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = stringResource(R.string.header_main_description),
                color = Color.Gray,
            )
            Text(
                modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
                text = stringResource(R.string.header_main_name),
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = Modifier.padding(bottom = 15.dp),
            text = stringResource(R.string.header_main_hobbies),
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        LazyColumn {
            items(hobbies) { hobby ->
                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = hobby
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

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    GDGAndroidTheme {
        MainScreen(navController = rememberNavController())
    }
}