package com.betboom.models.DTO.event

import com.betboom.utils.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class EventAddBet(
    val idUser: Int,
    val idLine: Int,
    @Serializable(with = BigDecimalSerializer::class)
    val amount: BigDecimal
)
