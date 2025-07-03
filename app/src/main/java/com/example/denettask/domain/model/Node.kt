package com.example.denettask.domain.model

import com.example.denettask.domain.utils.EthereumAddressGenerator

class Node(
    val name: String,
    var parent: Node?
) {
    private val _childs: MutableList<Node> = mutableListOf()
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
}
