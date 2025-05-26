package com.example.danettask.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Tree(),
    ) {
        composable<Routes.Tree> { backStackEntry ->
            val nodePath = backStackEntry.arguments?.getString("nodePath") ?: "root"

            TreeScreen(
                nodePath = nodePath,
                onNavigateToChild = { newPath ->
                    navController.navigate(Routes.Tree(newPath))
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

    }
}