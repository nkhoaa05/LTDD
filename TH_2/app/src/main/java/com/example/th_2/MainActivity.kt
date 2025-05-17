package com.example.th_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                CheckEmail()
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun CheckEmail() {
    var inputEmail by remember { mutableStateOf("") }
    var msg by remember { mutableStateOf("") }
    var check by remember { mutableStateOf(false) }
    Column(
        modifier =  Modifier
            .padding(15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(
            text =  "Thực hành 2",
            fontSize = 26.sp,
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value =  inputEmail,
            onValueChange = {
                inputEmail = it
                if (inputEmail.isNotEmpty())
                    msg = ""
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text =  msg,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(10.dp),
            color =  if (!check) Color.Red else Color.Green
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                when {
                    inputEmail == "" -> {
                        msg = "Email không hợp lệ"
                        check = false
                    }

                    !inputEmail.contains("@") -> {
                        msg = "Email không đúng định dạng"
                        check = false
                    }

                    else -> {
                        msg = "Bạn đã nhập email hợp lệ"
                        check = true
                    }
                }
            },
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)

        ){
            Text(
                text = "Kiểm tra",
                fontSize =  18.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }

}
