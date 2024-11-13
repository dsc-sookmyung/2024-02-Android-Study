package com.gdg.android

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gdg.android.ui.theme.button1Bold
import com.gdg.android.ui.theme.text1Regular
import com.gdg.android.ui.theme.text2Light
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(navController: NavController, mainViewModel: MainViewModel) {
    val userViewModel: UserViewModel = viewModel()
    val users by userViewModel.users.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val roomDB = UserDatabase.getDatabase(context)
    val coroutineScope = rememberCoroutineScope()
    val userList = remember { mutableStateListOf<UserEntity>() }

    /*LaunchedEffect(Unit) {
        userViewModel.getUsers()
    }*/

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "유저 목록",
                        fontWeight = FontWeight.Bold,
                        style = button1Bold
                    )
                }
            )
        },
        floatingActionButton = {
            SmallFloatingActionButton(
                shape = CircleShape,
                containerColor = Color.Gray,
                contentColor = Color.White,
                onClick = { navController.navigate("userCreate") } // 유저 등록 화면으로 이동
            ) {
                Icon(
                    modifier = Modifier.padding(15.dp),
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
            ) {
                LazyColumn {
                    items(users) { user ->
                        UserItem(user)
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "직접 추가한 유저 목록",
                            modifier = Modifier.padding(start = 18.dp, bottom = 8.dp),
                            color = Color.Gray,
                            fontWeight = FontWeight.SemiBold,
                            style = button1Bold
                        )
                    }
                    itemsIndexed(userList) { _, user ->
                        UserCreateItem(
                            user = user,
                            onDeleteClick = {
                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {
                                        roomDB.userDao().delete(user) // 유저 삭제
                                    }
                                    userList.remove(user) // UI에서 유저 제거
                                }
                            }
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun UserItem(user: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = user.firstName,
                color = Color.Black,
                style = text2Light
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = user.email,
                color = Color.Gray,
                style = text2Light
            )
        }
        AsyncImage(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp)),
            model = user.avatar,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun UserCreateItem(
    user: UserEntity,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = user.name, color = Color.Black, style = text2Light)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = user.email, color = Color.Gray, style = text2Light)
        }
        Icon(
            modifier = Modifier.clickable { onDeleteClick() }, // 삭제 클릭 이벤트
            imageVector = Icons.Filled.Delete,
            contentDescription = null,
        )
    }
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = Color.LightGray
    )
}
