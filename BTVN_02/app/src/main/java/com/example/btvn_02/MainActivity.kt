package com.example.btvn_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.btvn_02.ui.addnew.AddNewScreen
import com.example.btvn_02.ui.tasklist.TaskListScreen
import com.example.btvn_02.ui.tasklist.TaskListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel // Import này là quan trọng
import com.example.btvn_02.ui.theme.BTVN_02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BTVN_02Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TaskAppNavigation()
                }
            }
        }
    }
}

// Định nghĩa các tuyến đường (routes) cho điều hướng
object Routes {
    const val TASK_LIST = "task_list"
    const val ADD_NEW_TASK = "add_new_task"
}

@Composable
fun TaskAppNavigation() {
    val navController = rememberNavController()
    // Sử dụng chung một TaskListViewModel để AddNewScreen có thể thêm task vào TaskListScreen
    val taskListViewModel: TaskListViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.TASK_LIST) {
        composable(Routes.TASK_LIST) {
            TaskListScreen(
                navController = navController,
                viewModel = taskListViewModel, // Truyền ViewModel vào TaskListScreen
                onAddTaskClick = { navController.navigate(Routes.ADD_NEW_TASK) }
            )
        }
        composable(Routes.ADD_NEW_TASK) {
            AddNewScreen(
                navController = navController,
                onAddTask = { newTask ->
                    taskListViewModel.addTask(newTask) // Gọi hàm thêm task từ TaskListViewModel
                }
            )
        }
    }
}