package com.example.denettask.domain.repository

import com.example.denettask.domain.model.Node

interface TreeRepository {

    suspend fun getRoot(): Node
    suspend fun addChild(parent: Node): Node
    suspend fun removeNode(node: Node): Boolean
}