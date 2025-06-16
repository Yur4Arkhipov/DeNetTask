package com.example.danettask.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.danettask.R
import com.example.danettask.data.TreeNode
import com.example.danettask.data.TreeRepository
import com.example.danettask.data.findNodeByPath

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreeScreen(
    nodePath: String,
    onNavigateToChild: (String) -> Unit,
    onBack: () -> Unit
) {
    val root = TreeRepository.root
    val currentNode = root.findNodeByPath(nodePath)
    var showAddDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var newNodeName by remember { mutableStateOf("") }
    var nodeToDelete by remember { mutableStateOf<TreeNode<String>?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = currentNode?.name ?: "Unknown node") },
                navigationIcon = {
                    if (nodePath != "root") {
                        IconButton(onClick = onBack) {
                            Icon(
                                painter = painterResource(R.drawable.sharp_chevron_backward_24),
                                contentDescription = null
                            )
                        }
                    }
                },
                actions = {
                    IconButton(
                        onClick = { showAddDialog = true },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
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
            if (currentNode == null) {
                Text("Node not found")
            } else {
                Text("Current node: ${currentNode.name}")

                Spacer(Modifier.height(20.dp))

                if (currentNode.children.isEmpty()) {
                    Text("No children")
                } else {
//                    currentNode.children.forEach { child ->
//                        Button(
//                            onClick = {
//                                val newPath = "$nodePath/${child.name}"
//                                onNavigateToChild(newPath)
//                            }
//                        ) {
//                            Text(text = child.name)
//                        }
//                    }
                    currentNode.children.forEach { child ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Button(
                                onClick = {
                                    val newPath = "$nodePath/${child.name}"
                                    onNavigateToChild(newPath)
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(child.name)
                            }
                            Spacer(Modifier.width(8.dp))
                            IconButton(
                                onClick = {
                                    nodeToDelete = child
                                    showDeleteDialog = true
                                }
                            ) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete")
                            }
                        }
                    }
                }
            }
            if (showAddDialog && currentNode != null) {
                AlertDialog(
                    onDismissRequest = { showAddDialog = false },
                    title = { Text("Add child") },
                    text = {
                        Column {
                            Text("Parent: ${currentNode.name}")
                            OutlinedTextField(
                                value = newNodeName,
                                onValueChange = { newNodeName = it },
                                label = { Text("Node name") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                if (newNodeName.isNotBlank()) {
                                    currentNode.addChild(TreeNode(newNodeName))
                                    newNodeName = ""
                                    showAddDialog = false
                                }
                            },
                            enabled = newNodeName.isNotBlank()
                        ) {
                            Text("Add")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showAddDialog = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }
            if (showDeleteDialog && nodeToDelete != null) {
                AlertDialog(
                    onDismissRequest = {
                        showDeleteDialog = false
                        nodeToDelete = null
                    },
                    title = { Text("Confirm Deletion") },
                    text = { Text("Delete node '${nodeToDelete?.name}'?") },
                    confirmButton = {
                        Button(
                            onClick = {
                                currentNode?.removeChild(nodeToDelete!!)
                                showDeleteDialog = false
                                nodeToDelete = null
                            }
                        ) {
                            Text("Delete")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDeleteDialog = false
                            nodeToDelete = null
                        }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}