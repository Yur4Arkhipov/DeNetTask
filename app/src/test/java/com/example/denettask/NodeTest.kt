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
        assertEquals(root, child.getParent())
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
        assertEquals(root, child1.getParent())
        assertEquals(root, child2.getParent())
    }

    @Test
    fun `child can have its own children`() {
        val root = Node(name = "root", parent = null)
        val child = root.addChild()
        val grandChild = child.addChild()

        assertEquals(child, grandChild.getParent())
        assertEquals(1, child.childs.size)
    }

    @Test
    fun `getAllDescendants returns all nested children`() {
        val root = Node("root", null)
        val child1 = root.addChild()
        val child2 = root.addChild()
        val grandChild = child1.addChild()
        val greatGrandChild = grandChild.addChild()

        val descendants = root.getAllDescendants()

        assertEquals(4, descendants.size)
        assertTrue(descendants.containsAll(listOf(child1, child2, grandChild, greatGrandChild)))
    }

    @Test
    fun `getAllDescendants returns empty list for leaf node`() {
        val root = Node("root", null)
        val leaf = root.addChild()

        val descendants = leaf.getAllDescendants()

        assertTrue(descendants.isEmpty())
    }

    @Test
    fun `deeply nested tree structure is correct`() {
        val root = Node("root", null)
        var current = root
        repeat(10) {
            current = current.addChild()
        }

        val all = root.getAllDescendants()
        assertEquals(10, all.size)
        assertEquals(root, all.first().getParent())
    }
}