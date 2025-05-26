package com.example.danettask.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.danettask.R
import com.example.danettask.data.TreeNode
import com.example.danettask.data.findNodeByPath
import com.example.danettask.domain.TreeTypeConverter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> TreeScreen(
    nodePath: String,
    root: TreeNode<T>,
    converter: TreeTypeConverter<T>,
    onNavigateToChild: (String) -> Unit,
    onBack: () -> Unit
) {
    val currentNode = root.findNodeByPath(nodePath) { converter.fromString(it) }

    Scaffold(
      topBar = {
          TopAppBar(
              title = { Text(text = currentNode?.name.toString()) },
              navigationIcon = {
                  if (nodePath != "root") {
                      IconButton(
                          onClick = { onBack() }
                      ) {
                          Icon(
                              painter = painterResource(R.drawable.sharp_chevron_backward_24),
                              contentDescription = null
                          )
                      }
                  }
              }
          )
      }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text("Current node: ${currentNode?.name}")

            Spacer(Modifier.height(20.dp))

            currentNode?.children?.forEach { child ->
                Button(
                    onClick = {
                        val newPath = "$nodePath/${child.name}"
                        onNavigateToChild(newPath)
                    }
                ) {
                    Text(child.name.toString())
                }
            }

            if (currentNode?.children.isNullOrEmpty()) {
                Text("No children")
            }
        }
    }
}