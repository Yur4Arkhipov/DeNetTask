package com.example.denettask

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.denettask.data.TreeNode
import com.example.denettask.data.TreeRepositoryString
import com.example.denettask.ui.TreeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TreeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun childrenButtons_displayed_andClickable() {
        val clickedPaths = mutableListOf<String>()

        val tree = TreeNode("root").apply {
            addChild(TreeNode("A"))
            addChild(TreeNode("B"))
        }

        TreeRepositoryString.root = tree

        composeTestRule.setContent {
            TreeScreen(
                nodePath = "root",
                root = TreeRepositoryString.root,
                converter = TreeRepositoryString.converter,
                onNavigateToChild = { clickedPaths.add(it) },
                onBack = { }
            )
        }

        composeTestRule.onNodeWithText("A").assertIsDisplayed()
        composeTestRule.onNodeWithText("B").assertIsDisplayed()

        composeTestRule.onNodeWithText("A").performClick()

        assert(clickedPaths.contains("root/A"))
    }
}