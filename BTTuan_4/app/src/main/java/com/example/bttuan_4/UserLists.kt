package com.example.bttuan_4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.example.bttuan_4.data.MainViewModel
import com.example.bttuan_4.data.MainViewModelFactory



@Composable
fun UserScreen(navController: NavController) {
    val context = LocalContext.current
    val factory = MainViewModelFactory(context)
    val viewModel: MainViewModel = viewModel(factory = factory)

    val users by viewModel.users.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(12.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Hệ thống",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Quản lý thư viện",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(90.dp))
        // Sachs
        Text(
            text = "Danh sách người dùng",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .height(280.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn(
                modifier = Modifier
                    .height(250.dp)
                    .width(360.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.LightGray)
                    .padding(top = 10.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                items(users) { user ->
                    UserCard(userName = user.userName)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        // Thêm sách
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Button(
                onClick = {},
                modifier = Modifier
                    .height(55.dp)
                    .width(180.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0b58ac),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            )
            {
                Text(
                    "Thêm",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(185.dp))
        //Menu
        MenuBar(navController)

    }
}


@Composable
fun UserCard(userName: String) {
    Column(
        modifier = Modifier
            .height(50.dp)
            .width(320.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Text(
                text = userName,
                fontSize = 24.sp,
            )
        }
    }
}
