package com.example.danettask.ui

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data class Tree(val nodePath: String) : Routes()
}
