package com.example.bttuan_3

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun TextDetail(navController: NavHostController) {
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
            text = "Text Detail",
            fontSize = 24.sp,
            color = Color(0xFF2196F3),
            modifier = Modifier.align(Alignment.Center)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "The",
            fontSize = 24.sp,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "quick",
            fontSize = 24.sp,
            textDecoration = TextDecoration.LineThrough
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Brown",
            fontSize = 32.sp,
            color = Color(0xFFd27d05)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "fox",
            fontSize = 24.sp,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "jumps",
            fontSize = 24.sp,
            letterSpacing = 5.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "over",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "The",
            fontSize = 24.sp,
            textDecoration = TextDecoration.Underline
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "lazy",
            fontSize = 24.sp,
            style = TextStyle(fontFamily = FontFamily.Serif)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "dog",
            fontSize = 24.sp,
        )

    }
}

@Composable
fun ImgDetail(navController: NavHostController) {
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
                text = "Image Detail",
                fontSize = 24.sp,
                color = Color(0xFF2196F3),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = "https://giaothongvantaitphcm.edu.vn/wp-content/uploads/2025/01/Logo-GTVT.png",
                contentDescription = "Network Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(240.dp)
                    .fillMaxWidth()

            )

            Text(
                "https://giaothongvantaitphcm.edu.vn/wp-content/uploads/2025/01/Logo-GTVT.png",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 18.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Image(
                painter = painterResource(id = R.drawable.uth_bg),
                contentDescription = "Local Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(360.dp)
                    .fillMaxWidth()
            )

            Text(
                "In App",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .offset(y = (-45).dp)
            )
        }
    }
}