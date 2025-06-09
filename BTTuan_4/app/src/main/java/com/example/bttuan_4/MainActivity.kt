package com.example.bttuan_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bttuan_4.data.LibraryDatabase
import com.example.bttuan_4.data.MainViewModel
import com.example.bttuan_4.data.MainViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavScreen()
        }
    }
}


@Composable
fun NavScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("book") { BookScreen(navController) }
        composable("user") { UserScreen(navController) }
    }
}


@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val db = LibraryDatabase.getDatabase(context)
    val viewModel: MainViewModel = viewModel(
        factory = MainViewModelFactory(context)
    )

    var userName by remember { mutableStateOf("") }
    val borrowedBooks by viewModel.borrowedBooks.collectAsState()

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
        // Sinh viên
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            Text(
                text = "Sinh viên",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = userName,
                    onValueChange = {userName = it},
                    modifier = Modifier
                        .background(Color.White)
                        .width(210.dp),
                    shape = RoundedCornerShape(10.dp)
                )
                Button(
                    onClick = {viewModel.loadBooksForUser(userName)},
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0b58ac),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Thay đổi",
                        fontSize = 20.sp,
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Sachs
            Text(
                text = "Danh sách sách",
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
                    items(borrowedBooks) { bookName ->
                        BookCard(bookName)
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

            Spacer(modifier = Modifier.height(60.dp))
            //Menu
            MenuBar(navController)

        }
    }

}


@Composable
fun BookCard(bookName: String) {
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
            verticalAlignment = Alignment.CenterVertically

        ) {
            Checkbox(
                checked = true,
                onCheckedChange = {},
                modifier = Modifier.size(30.dp)

            )
            Text(
                text = bookName,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
        }
    }
}

@Composable
fun MenuBar(navController: NavController) {
    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier
                .height(80.dp)
                .padding(start = 15.dp, end = 15.dp)
                .width(80.dp)
                .clickable { navController.navigate("main") },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "Home",
                modifier = Modifier
                    .size(50.dp)

            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                "Quản lý",
                fontSize = 18.sp
            )
        }

        Column(
            modifier = Modifier
                .height(80.dp)
                .padding(start = 15.dp, end = 15.dp)
                .width(80.dp)
                .clickable { navController.navigate("book") },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.list),
                contentDescription = "Home",
                modifier = Modifier
                    .size(50.dp)

            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                "DS Sách",
                fontSize = 18.sp
            )
        }

        Column(
            modifier = Modifier
                .height(80.dp)
                .padding(start = 15.dp, end = 15.dp)
                .width(80.dp)
                .clickable { navController.navigate("user") },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.person),
                contentDescription = "Home",
                modifier = Modifier
                    .size(50.dp)

            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                "Sinh viên",
                fontSize = 18.sp
            )
        }
    }
}

