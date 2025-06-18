package com.example.smarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smarttasks.repository.TaskRepositoryImpl
import com.example.smarttasks.ui.theme.SmartTasksTheme
import com.example.smarttasks.viewmodel.TaskViewModel
import com.example.smarttasks.viewmodel.TaskViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartTasksTheme {
                App()
            }

        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val isUserLoggedIn = FirebaseAuth.getInstance().currentUser != null

    val startDestination = if (isUserLoggedIn) "home" else "intro"
    NavHost(navController = navController, startDestination = startDestination) {
        composable("intro") { IntroScreen(navController) }
        composable("onboarding") { OnBoardingPager(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") { SmartTasksApp()}  // App chính
    }
}

@Composable
fun SmartTasksApp() {
    val taskRepository = remember { TaskRepositoryImpl() }

    val viewModel: TaskViewModel = viewModel(
        factory = TaskViewModelFactory(taskRepository)
    )

    val uiState by viewModel.uiState.collectAsState()
    var currentScreen by remember { mutableStateOf("taskList") }


    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        when (currentScreen) {
            "taskList" -> {
                TaskListScreen(
                    uiState = uiState,
                    onTaskClick = { task ->
                        viewModel.selectTask(task)
                        currentScreen = "taskDetail"
                    },
                    onAddTaskClick = {
                        // TODO: Điều hướng đến màn hình thêm task
                    },
                    onTaskToggle = { taskId ->
                        viewModel.toggleTaskCompletion(taskId)
                    }
                )
            }
            "taskDetail" -> {
                uiState.selectedTask?.let { task ->
                    TaskDetailScreen(
                        task = task,
                        onBackClick = {
                            viewModel.clearSelectedTask()
                            currentScreen = "taskList"
                        },
                        onSubtaskToggle = { subtaskId ->
                            // TODO: Xử lý toggle subtask nếu có
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun IntroScreen(navController: NavController) {
    LaunchedEffect(true) {
        delay(3000)
        navController.navigate("onboarding") {
            popUpTo("intro") { inclusive = true }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(150.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "UTH SmartTasks",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

data class OnBoardingPage(
    val imageResId: Int,
    val title: String,
    val description: String,
    val showBackButton: Boolean,
    val showNextButton: Boolean
)

@Composable
fun OnBoardingScreen(
    imageResId: Int,
    title: String,
    description: String,
    showBackButton: Boolean,
    showNextButton: Boolean,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageSize: Dp = 320.dp,
    titleFontSize: TextUnit = 20.sp,
    descriptionFontSize: TextUnit = 18.sp,
    descriptionColor: Color = Color(0xFF4A4646)
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(14.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(120.dp))
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Onboarding Image",
            modifier = Modifier.size(320.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = description,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = descriptionColor
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (showBackButton) {
                Button(
                    onClick = onBackClick,
                    modifier = Modifier
                        .weight(0.8f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(33, 150, 243, 255),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)

                ) {
                    Text("Back")
                }
                Button(
                    onClick = onNextClick,
                    modifier = Modifier
                        .weight(2f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(33, 150, 243, 255),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Next")
                }
            } else {
                Button(
                    onClick = onNextClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(33, 150, 243, 255),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    val nextLabel =
                        if (!showBackButton && !showNextButton) "Get Started" else "Next"
                    Text(nextLabel)
                }
            }
        }

    }
}


@Composable
fun OnBoardingPager(navController: NavController) {
    val pages = listOf(
        OnBoardingPage(
            imageResId = R.drawable.ld2,
            title = "Easy Time Management",
            description = "With management based on priority and daily tasks, " +
                    "it will give you convenience in managing and determining the tasks that must be done first ",
            showBackButton = false,
            showNextButton = true
        ),
        OnBoardingPage(
            imageResId = R.drawable.ld1,
            title = "Increase Work Effectiveness",
            description = "Time management and the determination of more important tasks will " +
                    "give your job statistics better and always improve",
            showBackButton = true,
            showNextButton = true
        ),
        OnBoardingPage(
            imageResId = R.drawable.ld3,
            title = "Reminder Notification",
            description = "The advantage of this application is that it also provides reminders for you so you don't forget to keep " +
                    "doing your assignments well and according to the time you have set",
            showBackButton = false,
            showNextButton = true
        )
    )

    var currentPage by remember { mutableStateOf(0) }
    val current = pages[currentPage]

    OnBoardingScreen(
        imageResId = current.imageResId,
        title = current.title,
        description = current.description,
        showBackButton = current.showBackButton,
        showNextButton = current.showNextButton,
        onBackClick = {
            if (currentPage > 0) currentPage--
        },
        onNextClick = {
            if (currentPage < pages.lastIndex) {
                currentPage++
            } else {
                navController.navigate("login")
            }
        }
    )
}

@Composable
fun HomeScreen(navController: NavController) {
    Text("HOME")
}


