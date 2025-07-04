package com.example.denettask.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.denettask.domain.model.Node
import com.example.denettask.domain.usecase.AddChildNodeUseCase
import com.example.denettask.domain.usecase.GetRootNodeUseCase
import com.example.denettask.domain.usecase.RemoveNodeUseCase
import com.example.denettask.ui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreeViewModel @Inject constructor(
    private val getRootNode: GetRootNodeUseCase,
    private val addChild: AddChildNodeUseCase,
    private val removeNode: RemoveNodeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private var current: Node? = null

    init {
        viewModelScope.launch {
            val root = getRootNode()
            current = root
            _uiState.value = UiState.Success(root)
        }
    }

    fun onAddChild() {
        viewModelScope.launch {
            current?.let {
                addChild(it)
                _uiState.value = UiState.Success(it)
            }
        }
    }

    fun onRemoveCurrent() {
        viewModelScope.launch {
            current?.let { node ->
                if (node.getParent() != null) {
                    removeNode(node)
                    current = node.getParent()
                    _uiState.value = UiState.Success(current!!)
                }
            }
        }
    }

    fun goToChild(child: Node) {
        current = child
        _uiState.value = UiState.Success(child)
    }

    fun goToParent() {
        current?.getParent()?.let { parent ->
            current = parent
            _uiState.value = UiState.Success(parent)
        }
    }
}