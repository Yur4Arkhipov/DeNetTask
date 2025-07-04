package com.example.denettask.domain.model

import androidx.compose.runtime.mutableStateListOf
import com.example.denettask.domain.utils.EthereumAddressGenerator

class Node(
    val name: String,
    private var parent: Node?
) {
    private val _childs = mutableStateListOf<Node>()
    val childs: List<Node> = _childs

    fun addChild(): Node {
        val newName = EthereumAddressGenerator.generateNodeName()
        val child = Node(name = newName, parent = this)
        _childs.add(child)
        return child
    }

    fun removeChild(child: Node) {
        _childs.remove(child)
    }

    fun setParent(parent: Node?) {
        this.parent = parent
    }

    fun getParent(): Node? = parent

    fun addChildNode(child: Node) {
        child.setParent(this)
        _childs.add(child)
    }

    fun getAllDescendants(): List<Node> {
        val result = mutableListOf<Node>()
        fun dfs(node: Node) {
            for (child in node.childs) {
                result.add(child)
                dfs(child)
            }
        }
        dfs(this)
        return result
    }
}
