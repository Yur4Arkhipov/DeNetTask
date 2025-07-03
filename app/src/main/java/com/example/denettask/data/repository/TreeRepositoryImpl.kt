package com.example.denettask.data.repository

import com.example.denettask.data.local.NodeDao
import com.example.denettask.data.local.NodeEntity
import com.example.denettask.data.local.toEntity
import com.example.denettask.domain.model.Node
import com.example.denettask.domain.repository.TreeRepository
import com.example.denettask.domain.utils.EthereumAddressGenerator

class TreeRepositoryImpl(
    private val nodeDao: NodeDao
) : TreeRepository {

    private var root: Node? = null

    override suspend fun getRoot(): Node {
        if (root == null) {
            loadTree()
            checkNotNull(root) { "Exception getRoot" }
        }

        return root!!
    }

    override suspend fun addChild(parent: Node): Node {
        val newName = EthereumAddressGenerator.generateNodeName()
        val child = Node(name = newName, parent = parent)
        nodeDao.insertNode(child.toEntity())
        parent.addChildNode(child)
        return child
    }

    override suspend fun removeNode(node: Node): Boolean {
        val all = node.getAllDescendants() + node
        all.forEach { nodeDao.deleteNodeByName(it.name) }
        node.getParent()?.removeChild(node)
        return true
    }

    private suspend fun loadTree() {
        val entities = nodeDao.getAllNodes()
        if (entities.isEmpty()) {
            root = Node(name = EthereumAddressGenerator.generateNodeName(), parent = null)
            nodeDao.insertNode(root!!.toEntity())
        } else {
            root = buildTreeFromEntities(entities)
        }
    }

    private fun buildTreeFromEntities(entities: List<NodeEntity>): Node {
        // Map<String, NodeEntity> -> Map<String, <Node>>
        val map = entities.associateBy { it.name }.mapValues { Node(it.key, null) }.toMutableMap()

        entities.forEach { entity ->
            val node = map[entity.name]!!
            entity.parentName?.let { parentName ->
                val parent = map[parentName]
                node.setParent(parent)
                parent?.addChildNode(node)
            }
        }
        return map.values.first { it.getParent() == null }
    }
}