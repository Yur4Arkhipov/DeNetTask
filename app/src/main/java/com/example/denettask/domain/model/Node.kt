package com.example.denettask.domain.model

data class Node(
    val id: String,
    val displayName: String,
    val name: String,
    var parent: Node?,
    val children: MutableList<Node> = mutableListOf()
)