package com.example.denettask

import com.example.denettask.domain.utils.EthereumAddressGenerator
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class EthereumAddressGeneratorTest {

    @Test
    fun `address should start with 0x`() {
        val address = EthereumAddressGenerator.generateNodeName()
        assertTrue(address.startsWith("0x"))
    }

    @Test
    fun `address should be 42 characters long`() {
        val address = EthereumAddressGenerator.generateNodeName()
        assertEquals(42, address.length)
    }

    @Test
    fun `address should contain only valid hex characters`() {
        val address = EthereumAddressGenerator.generateNodeName().removePrefix("0x")
        assertTrue(address.matches(Regex("^[0-9a-f]{40}$")))
    }

    @Test
    fun `generate multiple addresses - should be unique`() {
        val addresses = (1..1000).map { EthereumAddressGenerator.generateNodeName() }.toSet()
        assertEquals(1000, addresses.size)
    }
}