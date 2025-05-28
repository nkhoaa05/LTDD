package com.example.bttuan_3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun TextFDetail(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.backicon),
                contentDescription = "Back Icon",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(36.dp)
                    .offset(x = 15.dp)
                    .clickable {
                        navController.navigate("menu")
                    }
            )

            Text(
                text = "TextField Detail",
                fontSize = 24.sp,
                color = Color(0xFF2196F3),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var text by remember { mutableStateOf ("") }
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Nhập thông tin") },
                modifier = Modifier
                    .offset(y = (-60).dp)
                    .clip(RoundedCornerShape(10.dp))

            )
        }
    }
}


@Composable
fun PasswordInField(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.backicon),
                contentDescription = "Back Icon",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(36.dp)
                    .offset(x = 15.dp)
                    .clickable {
                        navController.navigate("menu")
                    }
            )

            Text(
                text = "TextField Detail",
                fontSize = 24.sp,
                color = Color(0xFF2196F3),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var password by remember { mutableStateOf("") }
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Nhập mật khẩu") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .offset(y = (-60).dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
}

