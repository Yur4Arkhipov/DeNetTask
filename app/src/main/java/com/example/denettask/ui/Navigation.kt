package com.example.denettask.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.denettask.data.TreeRepositoryString

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
                root = TreeRepositoryString.root,
                converter = TreeRepositoryString.converter,
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