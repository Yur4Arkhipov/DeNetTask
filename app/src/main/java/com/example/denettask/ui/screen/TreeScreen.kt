package com.example.denettask.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.denettask.ui.util.UiState

@Composable
fun TreeScreen(
    viewModel: TreeViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    Scaffold { innerPadding ->
        when (val currentState = state.value) {
            is UiState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Success -> {
                val node = currentState.current

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(top = 16.dp, start = 8.dp, end = 8.dp)
                ) {
                    Text("Current Node: ${node.name}")

                    Spacer(Modifier.height(16.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(onClick = { viewModel.onAddChild() }) {
                            Text("Add Child")
                        }

                        Spacer(Modifier.width(10.dp))

                        Button(
                            onClick = { viewModel.onRemoveCurrent() },
                            enabled = node.getParent() != null
                        ) {
                            Text("Remove")
                        }

                        Spacer(Modifier.width(10.dp))

                        Button(
                            onClick = { viewModel.goToParent() },
                            enabled = node.getParent() != null
                        ) {
                            Text("Go to Parent")
                        }
                    }

                    Spacer(Modifier.height(20.dp))

                    Text("Children:")

                    node.childs.forEach { child ->
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            onClick = { viewModel.goToChild(child) }
                        ) {
                            Text(child.name)
                        }
                    }
                }
            }
        }
    }
}

