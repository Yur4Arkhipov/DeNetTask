package com.example.denettask.ui.util

import com.example.denettask.domain.model.Node

sealed class UiState {
    object Loading : UiState()
    data class Success(val current: Node) : UiState()
}