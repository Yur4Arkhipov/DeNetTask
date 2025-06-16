package com.example.danettask.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Routes.Tree("root"),
    ) {
        composable<Routes.Tree> { backStackEntry ->
            val nodePath = backStackEntry.arguments?.getString("nodePath") ?: "root"

            TreeScreen(
                nodePath = nodePath,
                onNavigateToChild = { newPath ->
                    navHostController.navigate(Routes.Tree(newPath))
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}