package com.example.todo_jetpack.navigation

import androidx.navigation.NavHostController
import com.example.todo_jetpack.util.Action
import com.example.todo_jetpack.util.Constants

class Screens(navController: NavHostController) {
    val list: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(Constants.LIST_SCREEN) { inclusive = true }
        }
    }

    val task: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
}