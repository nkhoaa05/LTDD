package com.example.smarttasks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarttasks.data.model.Task
import com.example.smarttasks.data.model.TaskCategory
import com.example.smarttasks.viewmodel.TaskListUiState
import java.text.SimpleDateFormat
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    uiState: TaskListUiState,
    onTaskClick: (Task) -> Unit,
    onAddTaskClick: () -> Unit,
    onTaskToggle: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // UTH Logo placeholder
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color(0xFF2196F3), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "UTH",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "SmartTasks",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2196F3)
                        )
                        Text(
                            text = "A simple and efficient to-do app",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            },
            actions = {
                // App icon placeholder
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFFF9800), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            )
        )

        // Content
        if (uiState.tasks.isEmpty()) {
            EmptyTasksView()
        } else {
            TaskList(
                tasks = uiState.tasks,
                onTaskClick = onTaskClick,
                onTaskToggle = onTaskToggle
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation and FAB
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomNavigation()

            FloatingActionButton(
                onClick = onAddTaskClick,
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = (-28).dp),
                containerColor = Color(0xFF2196F3)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add Task",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
private fun EmptyTasksView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Sleeping clipboard icon
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color(0xFFE0E0E0), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.Assignment,
                        contentDescription = null,
                        modifier = Modifier.size(60.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = "Z\nZ",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.offset(x = 20.dp, y = (-10).dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "No Tasks Yet!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Stay productive—add something to do",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun TaskList(
    tasks: List<Task>,
    onTaskClick: (Task) -> Unit,
    onTaskToggle: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(tasks) { task ->
            TaskItem(
                task = task,
                onClick = { onTaskClick(task) },
                onToggle = { onTaskToggle(task.id) }
            )
        }
    }
}

@Composable
private fun TaskItem(
    task: Task,
    onClick: () -> Unit,
    onToggle: () -> Unit
) {
    val backgroundColor = when (task.category) {
        TaskCategory.WORK -> Color(0xFFE1BEE7)
        TaskCategory.HEALTH -> Color(0xFFC8E6C9)
        TaskCategory.FITNESS -> Color(0xFFB3E5FC)
        TaskCategory.PERSONAL -> Color(0xFFFFCCBC)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = { onToggle() },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Black,
                        uncheckedColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = task.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = task.description,
                        fontSize = 14.sp,
                        color = Color.Black.copy(alpha = 0.7f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Status: ${task.status.displayName}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                task.dueDate?.let { date ->
                    Text(
                        text = SimpleDateFormat("HH:mm yyyy-MM-dd", Locale.getDefault()).format(date),
                        fontSize = 14.sp,
                        color = Color.Black.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavigation() {
    val items = listOf(
        BottomNavItem.IconItem(Icons.Default.Home),
        BottomNavItem.IconItem(Icons.Default.DateRange),
        BottomNavItem.FabSpacer,
        BottomNavItem.IconItem(Icons.Default.Description),
        BottomNavItem.IconItem(Icons.Default.Settings)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White)
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            when (item) {
                is BottomNavItem.IconItem -> Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Gray
                )

                BottomNavItem.FabSpacer -> Spacer(modifier = Modifier.width(56.dp))
            }
        }
    }
}


sealed class BottomNavItem {
    data class IconItem(val icon: ImageVector) : BottomNavItem()
    object FabSpacer : BottomNavItem()
}