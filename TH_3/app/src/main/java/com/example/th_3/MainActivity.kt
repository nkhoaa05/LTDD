package com.example.th_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.font.FontWeight


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting() {
    var msg by remember { mutableStateOf("Hello") }
    val name = "Nguyễn Đăng Khoa"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(150.dp))

        Text(
            text =  "My first app",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(200.dp))

        if (msg == "Hello"){
            Text(
                text = msg,
                fontSize = 24.sp,
            )
        }
        else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text =  msg,
                    fontSize = 22.sp,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text =  name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(200.dp))

        Button(
            onClick = {
                msg = "I'm"
            },
            modifier = Modifier
                .height(50.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3),
                contentColor =  Color.White,
            ),
            shape = RoundedCornerShape(12.dp)
        ){
            Text("Say Hi!")
        }
    }
}

