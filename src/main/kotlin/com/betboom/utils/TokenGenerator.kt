package com.betboom.utils

import java.util.*

object TokenGenerator {
    fun encodeToBase64(input: String): String {
        val encodedBytes = Base64.getEncoder().encode(input.toByteArray())
        return String(encodedBytes)
    }

    fun decodeFromBase64(input: String): String{
        val decodeBytes = Base64.getDecoder().decode(input.toByteArray())
        return String(decodeBytes)
    }

}