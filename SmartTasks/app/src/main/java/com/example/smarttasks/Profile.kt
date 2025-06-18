package com.example.smarttasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp





@Preview(showBackground = true)
@Composable
fun ProfileScreen() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {

            Image(
                painter = painterResource(R.drawable.back_icon),
                contentDescription = "back",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterStart)
                    .offset(x = 10.dp)
            )

            Text(
                "Profile",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier.size(140.dp)
        ){
            Image(
                painter = painterResource(R.drawable.g_logo),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
            )

            Image(
                painter = painterResource(R.drawable.camera_icon),
                contentDescription = "Edit",
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.BottomEnd)
                    .offset(5.dp, 5.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(start =  10.dp, end =  10.dp),
            horizontalAlignment = Alignment.Start
        ){
            ProfileField("Name", "Name")
            ProfileField("Email", "Email")
            ProfileField("Date of Birth", "24/5/2025")
        }
    }
}


@Composable
fun ProfileField(label: String, value: String) {
    Text(
        text = label,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(5.dp))
    OutlinedTextField(
        value = value,
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.LightGray,
            unfocusedIndicatorColor = Color.LightGray
        ),
        singleLine = true
    )
    Spacer(modifier = Modifier.height(25.dp))
}


