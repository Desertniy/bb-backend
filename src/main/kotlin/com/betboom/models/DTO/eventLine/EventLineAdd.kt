package com.betboom.models.DTO.eventLine

import com.betboom.utils.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

data class EventLineAdd(
    val idEvent: Int,
    val idStruct: Int,
    @Serializable(with = BigDecimalSerializer::class)
    val coef: BigDecimal,
    val status: Boolean
)
