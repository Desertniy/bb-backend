package com.betboom.models.DTO.eventLine

import com.betboom.utils.BigDecimalSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import java.math.BigDecimal

@Serializable
data class EventLineDto(
    val idEventLine: Int,
    val idEvent: Int,
    val idStruct: Int,
    @Serializable(with = BigDecimalSerializer::class)
    val coef: BigDecimal,
    val status: Boolean
)
