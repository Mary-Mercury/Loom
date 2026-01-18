package com.example.loom.View

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "tasks"
    ) {
        composable(route="tasks") {
            TasksScreen(viewModel=viewModel, navController=navController)
        }

        composable(route="edit/{taskId}/{task}",
            arguments = listOf(
                navArgument("taskId") { type = NavType.IntType },
                navArgument(name = "task") { type = NavType.StringType }),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(500)
                )
            }
        ) {
            val taskId = it.arguments?.getInt("taskId")!!
            val task = it.arguments?.getString("task")!!

            EditTask(viewModel=viewModel, navController=navController, taskId = taskId, task=task)
        }

        composable(
            route="newTask",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right,
                tween(500)
                )
            }
        ) {
            AddNewTask(viewModel=viewModel, navController=navController)
        }
    }
}
