package com.example.bttuan_3

import android.view.Menu
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

//navController: NavHostController
//@Preview(showBackground = true)
@Composable
fun MenuScreen(navController: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            // Tiêu đề CỐ ĐỊNH
            Text(
                text = "UI Components List",
                fontSize = 24.sp,
                color = Color(0xFF2196F3),
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }


        Column(
            modifier = Modifier
                .padding(top = 80.dp) // Để không đè lên tiêu đề
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Section 1
            SectionTitle("Display")
            MenuCard("Text", "Displays text", ) { navController.navigate("text-detail") }
            MenuCard("Image", "Displays an image") { navController.navigate("img-detail")}

            // Section 2
            SectionTitle("Input")
            MenuCard("TextField", "Input field for text") { navController.navigate("textf-detail")}
            MenuCard("PasswordField", "Input field for passwords") { navController.navigate("passf-detail")}
            MenuCard("Checkbox", "Input for binary choice (checked/unchecked)") { }
            MenuCard("RadioButton", "Input for selecting a single option in a group") { }
            MenuCard("Switch", "Toggle switch input for on/off state") { }
            MenuCard("Slider", "Input for selecting a value from a range") { }
            MenuCard("Button", "Clickable input element for actions") { }

            // Section 3
            SectionTitle("Layout")
            MenuCard("Column", "Arranges elements vertically") { }
            MenuCard("Row", "Arranges elements horizontally") { }
            MenuCard("Box", "Stacks elements on top of each other") { }
            MenuCard("LazyRow", "Efficiently displays a horizontal scrolling list") { }
            MenuCard("LazyColumn", "Efficiently displays a vertical scrolling list") { }
            MenuCard("LazyVerticalGrid", "Displays a vertically scrolling grid layout") { }
            MenuCard("ConstraintLayout", "Positions elements using constraints") { }
            MenuCard("FlowRow", "Wraps elements to the next row automatically") {}
            MenuCard("FlowColumn", "Wraps elements to the next column automatically") { }
            MenuCard("CustomLayout", "Allows full control over measuring & placing elements") { }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start

    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun MenuCard(title: String, description: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(340.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0x4D2196F3))
            .clickable { onClick() },
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, bottom = 5.dp)
        )
        Text(
            text = description,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 20.dp, bottom = 10.dp)
        )
    }
    Spacer(modifier = Modifier.height(15.dp))
}

