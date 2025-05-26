package com.example.danettask.data

object TreeRepository {
    val root: TreeNode<String> = createTree()

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

fun TreeNode<String>.findNodeByPath(path: String): TreeNode<String>? {
    val parts = path.split("/")
    var current: TreeNode<String>? = this
    for (part in parts.drop(1)) {
        current = current?.children?.find { it.name == part }
    }
    return current
}
