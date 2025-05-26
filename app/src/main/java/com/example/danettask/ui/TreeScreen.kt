package com.example.danettask.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    onBack: () -> Unit,
) {
    val currentNode = root.findNodeByPath(nodePath) { converter.fromString(it) }
    var newNode by remember { mutableStateOf("") }

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
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            currentNode?.let { node ->
                item {
                    Text("Current node: ${node.name}")
                    Spacer(Modifier.height(20.dp))
                }

                if (node.children.isEmpty()) {
                    item {
                        Text("No children")
                        Spacer(Modifier.height(10.dp))
                    }
                } else {
                    items(node.children) { child ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    val newPath = "$nodePath/${child.name}"
                                    onNavigateToChild(newPath)
                                }
                            ) {
                                Text(converter.toString(child.name))
                            }

                            IconButton(
                                onClick = {
                                    node.removeChild(child)
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_delete_24),
                                    contentDescription = null
                                )
                            }
                        }
                        Spacer(Modifier.height(10.dp))
                    }
                }

                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = newNode,
                            onValueChange = { newNode = it },
                            label = {
                                Text(
                                    text = "Add new child",
                                    fontSize = 10.sp,
                                )
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { /* закрыть клавиатуру, если хочешь */ }
                            ),
                        )

                        IconButton(
                            onClick = {
                                val value = converter.fromString(newNode)
                                if (value != null) {
                                    node.addChild(TreeNode(value))
                                    newNode = ""
                                }
                            },
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )
                        }
                    }
                }
            } ?: item{
                Text("Node not found")
            }
        }
    }
}