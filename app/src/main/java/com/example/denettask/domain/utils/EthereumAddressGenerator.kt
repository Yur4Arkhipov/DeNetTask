package com.example.denettask.domain.utils

import org.bouncycastle.jce.ECNamedCurveTable
import java.math.BigInteger
import java.security.SecureRandom
import org.bouncycastle.jcajce.provider.digest.Keccak

object EthereumAddressGenerator {

    fun generateNodeName(): String {
        // Приватный ключ
        val privateKey = ByteArray(32).apply {
            SecureRandom().nextBytes(this)
        }

        // Публичный ключ
        val privateKeyInt = BigInteger(1, privateKey)

        // Параметры эллиптической кривой (y² = x³ + 7)
        val spec = ECNamedCurveTable.getParameterSpec("secp256k1")

        // Q = d * G генерация публичного ключа
        val publicKeyPoint = spec.g.multiply(privateKeyInt)
        val publicKeyUncompressed = publicKeyPoint.getEncoded(false)

        // чтобы было 64 байта
        val publicKeyBytes = publicKeyUncompressed.copyOfRange(1, publicKeyUncompressed.size)

        // хэширование публичного ключа с keccak
        val keccak = Keccak.Digest256()
        keccak.update(publicKeyBytes)
        val publicKeyHash = keccak.digest()

        // последние 20 байт
        val addressBytes = publicKeyHash.copyOfRange(12, 32)

        return "0x" + addressBytes.toHexString()
    }

    private fun ByteArray.toHexString(): String {
        return joinToString("") { "%02x".format(it) }
    }
}