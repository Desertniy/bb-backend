package com.betboom.models.DTO.user

import com.betboom.utils.BigDecimalSerializer
import com.betboom.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
data class UserAdd(
    val username: String,
    val password: String,
    val salt: ByteArray,
    val idRole: Int,
    val idCountry: Int,
    val statusBan: Boolean,
    @Serializable(LocalDateTimeSerializer::class)
    val startBan: LocalDateTime?,
    @Serializable(BigDecimalSerializer::class)
    val balance: BigDecimal,
)
