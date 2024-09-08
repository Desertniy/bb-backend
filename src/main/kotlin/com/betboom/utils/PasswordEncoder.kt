package com.betboom.utils

import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object PasswordEncoder {
    private const val ITERATIONS = 10000
    private const val KEY_LENGTH = 32
    private val RANDOM = SecureRandom()

    @Throws(NoSuchAlgorithmException::class, InvalidKeySpecException::class)
    fun hashPassword(password: String, salt: ByteArray): String {
        val spec = PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val hash = factory.generateSecret(spec).encoded
        return Base64.getEncoder().encodeToString(hash)
    }

    @Throws(NoSuchAlgorithmException::class, InvalidKeySpecException::class)
    fun generateSalt(): ByteArray {
        val salt = ByteArray(16)
        RANDOM.nextBytes(salt)
        return salt
    }
}