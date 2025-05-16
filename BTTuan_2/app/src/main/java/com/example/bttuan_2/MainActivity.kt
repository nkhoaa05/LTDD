package com.example.bttuan_2

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
import androidx.compose.foundation.background
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.font.FontWeight



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InputAge()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun InputAge() {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var msg by remember { mutableStateOf("") }
    var resultInfo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Họ và tên",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(end = 15.dp)
                    )

                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                            if(name.isNotEmpty()) msg = ""
                        },
                        modifier = Modifier.width(200.dp)

                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Tuổi",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(end = 50.dp)
                    )

                    OutlinedTextField(
                        value = age,
                        onValueChange = {
                            age = it
                            if(age.isNotEmpty()) msg = ""
                        },
                        modifier = Modifier.width(200.dp)
                    )
                }
            }

                Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    var checkEmpty = true
                    if (name.isEmpty()) {
                        msg = "Vui lòng nhập tên"
                        checkEmpty = false
                    }
                    if (age.isEmpty())
                    {
                        msg = "Vui lòng nhập tuổi"
                        checkEmpty = false
                    }
                    if (age.isEmpty() && name.isEmpty()){
                        msg = "Vui lòng nhập thông tin"
                        checkEmpty = false
                    }

                    if(checkEmpty) {
                        val inpAge = age.toInt()
                        val result = when {
                            inpAge <= 2 -> "Em bé"
                            inpAge in 3..6 -> "Trẻ em"
                            inpAge in 7..65 -> "Người lớn"
                            else -> "Người già"
                        }
                        resultInfo = "Họ tên: $name\nTuổi: $age\nPhân loại: $result"
                    }

                },
                modifier = Modifier
                    .width(200.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                )

            ){
                Text(
                    text = "Kiểm tra",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(  // Thông báo
                text = msg,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(10.dp)

            )

            Text( // Kết quả
                text =  resultInfo,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(20.dp)
            )
        }

}
