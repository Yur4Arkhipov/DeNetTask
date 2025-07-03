package com.example.denettask

import com.example.denettask.domain.model.Node
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertNotEquals
import org.junit.Test

class NodeTest {

    @Test
    fun `addChild adds a new node with unique name and correct parent`() {
        val root = Node(name = "root", parent = null)

        val child = root.addChild()

        assertEquals(1, root. childs.size)
        assertTrue(root.childs.contains(child))
        assertEquals(root, child.parent)
        assertNotEquals("root", child.name)
    }

    @Test
    fun `removeChild removes the child node`() {
        val root = Node(name = "root", parent = null)
        val child = root.addChild()

        root.removeChild(child)

        assertFalse(root.childs.contains(child))
        assertEquals(0, root.childs.size)
    }

    @Test
    fun `adding multiple children creates all unique nodes`() {
        val root = Node(name = "root", parent = null)
        val child1 = root.addChild()
        val child2 = root.addChild()

        assertEquals(2, root.childs.size)
        assertNotEquals(child1.name, child2.name)
        assertEquals(root, child1.parent)
        assertEquals(root, child2.parent)
    }

    @Test
    fun `child can have its own children`() {
        val root = Node(name = "root", parent = null)
        val child = root.addChild()
        val grandChild = child.addChild()

        assertEquals(child, grandChild.parent)
        assertEquals(1, child.childs.size)
    }
}