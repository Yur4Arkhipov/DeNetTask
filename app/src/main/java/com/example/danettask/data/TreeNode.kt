package com.example.danettask.data

import androidx.compose.runtime.mutableStateListOf

/**
 * Node с названием, детьми и ссылкой на родителя
 */
class TreeNode<T>(
    val name: T
) {
    private val _children: MutableList<TreeNode<T>> = mutableStateListOf<TreeNode<T>>()
    var parent: TreeNode<T>? = null
        private set

    val children: List<TreeNode<T>>
        get() = _children.toList()

    fun addChild(child: TreeNode<T>) {
        child.parent = this
        _children.add(child)
    }

    fun removeChild(child: TreeNode<T>) {
        _children.remove(child)
        child.parent = null
    }
}