package com.example.todo_jetpack.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todo_jetpack.navigation.destinations.listComposable
import com.example.todo_jetpack.navigation.destinations.taskComposable
import com.example.todo_jetpack.ui.viewmodels.SharedViewModel
import com.example.todo_jetpack.util.Constants

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = Constants.LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.list
        )
    }
}