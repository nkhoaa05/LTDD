package com.example.smarttasks

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun LoginScreen(navController: NavController) {

    val context = LocalContext.current
    val token = "4448650353-hja62itnrri1pm8mcuqq9camg5ekv3c9.apps.googleusercontent.com"
    val authHelper = remember { GoogleAuthHelper(context, token) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            authHelper.handleSignInResult(
                result.data,
                onSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onError = {

                }
            )
        }
    }




    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(14.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            painter = painterResource(R.drawable.logo_login),
            contentDescription = "Logo Login",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            "SmartTasks",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2196F3)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "A simple and efficient to-do app",
            fontSize = 16.sp,
            color = Color(0xFF2196F3)
        )
        Spacer(modifier = Modifier.height(150.dp))
        Text(
            "Welcome",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            "Ready to explore? Log in to get started.",
            fontSize = 16.sp,
            color = Color(0xFF4A4646)
        )
        Spacer(modifier = Modifier.height(30.dp))
        GGLoginBtn(
            onClick = { launcher.launch(authHelper.getSignInIntent()) }
        )
    }
}

@Composable
fun GGLoginBtn(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFD5EDFF),
            contentColor = Color(0xFF130160)
        ),
        modifier = Modifier
            .height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.g_logo),
                contentDescription = "gg icon"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                "SIGN IN WITH GOOGLE",
                fontSize = 16.sp
            )
        }
    }
}