package com.example.danettask.data

import com.example.danettask.domain.IntTreeConverter
import com.example.danettask.domain.StringTreeConverter
import com.example.danettask.domain.TreeTypeConverter

object TreeRepositoryString {
    var root: TreeNode<String> = createTree()
    val converter: TreeTypeConverter<String> = StringTreeConverter

    private fun createTree(): TreeNode<String> {
        val root = TreeNode("root")
        val root1 = TreeNode("root_1_child")
        val root2 = TreeNode("root_2_child")
        val root1FirstChild = TreeNode("1_1")
        val root1SecondChild = TreeNode("1_2")
        val root2FirstChild = TreeNode("2_1")
        val root2SecondChild = TreeNode("2_2")

        root.addChild(root1)
        root.addChild(root2)

        root1.addChild(root1FirstChild)
        root1.addChild(root1SecondChild)

        root2.addChild(root2FirstChild)
        root2.addChild(root2SecondChild)

        return root
    }
}

object TreeRepositoryInt {
    var root: TreeNode<Int> = createTree()
    val converter: TreeTypeConverter<Int> = IntTreeConverter

    fun createTree(): TreeNode<Int> {
        val root = TreeNode(0)
        val root1 = TreeNode(1)
        val root2 = TreeNode(2)
        val root1FirstChild = TreeNode(11)
        val root1SecondChild = TreeNode(12)
        val root2FirstChild = TreeNode(21)
        val root2SecondChild = TreeNode(22)

        root.addChild(root1)
        root.addChild(root2)

        root1.addChild(root1FirstChild)
        root1.addChild(root1SecondChild)

        root2.addChild(root2FirstChild)
        root2.addChild(root2SecondChild)

        return root
    }
}

fun <T> TreeNode<T>.findNodeByPath(path: String, convert: (String) -> T?): TreeNode<T>? {
    val parts = path.split("/")
    var current: TreeNode<T>? = this
    for (part in parts.drop(1)) {
        val value = convert(part)
        current = current?.children?.find { it.name == value }
    }
    return current
}