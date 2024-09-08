package com.betboom.models.DTO.userEvent

import com.betboom.utils.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class UserEventAdd(
    val idLine: Int,
    val idUser: Int,
    @Serializable(with = BigDecimalSerializer::class)
    val amount: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val coef: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val resultPayout: BigDecimal
)
