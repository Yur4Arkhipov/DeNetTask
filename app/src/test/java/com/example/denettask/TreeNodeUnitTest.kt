package com.example.denettask

import org.junit.Assert.assertEquals
import org.junit.Test
import com.example.denettask.data.TreeNode

class TreeNodeUnitTest {
    val root = TreeNode("root")
    val firstChild = TreeNode("firstChild")
    var secondChild = TreeNode("secondChild")

    @Test
    fun `addChild fun correctly set parent and one child`() {
        root.addChild(firstChild)
        assertEquals(1, root.children.size)

        assertEquals(root.children[0].name, "firstChild")
        assertEquals(root.children[0], firstChild)
        assertEquals(root, firstChild.parent)
    }

    @Test
    fun `addChild fun correctly set parent and multiple child`() {
        root.addChild(firstChild)
        assertEquals(1, root.children.size)

        root.addChild(secondChild)
        assertEquals(2, root.children.size)

        assertEquals(root.children[0].name, firstChild.name)
        assertEquals(root.children[1].name, secondChild.name)

        assertEquals(root.children[0], firstChild)
        assertEquals(root.children[1], secondChild)

        assertEquals(root, firstChild.parent)
        assertEquals(root, secondChild.parent)
    }
}