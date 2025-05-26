package com.example.danettask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.danettask.ui.Navigation
import com.example.danettask.ui.theme.DaNetTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DaNetTaskTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Navigation()
//                }
            }
        }
    }
}