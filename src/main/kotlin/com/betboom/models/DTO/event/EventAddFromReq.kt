package com.betboom.models.DTO.event

import com.betboom.utils.BigDecimalSerializer
import com.betboom.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
data class EventAddFromReq(
    val nameEvent: String,
    @Serializable(LocalDateTimeSerializer::class)
    val dateStartEvent: LocalDateTime,
    val Type: String,
    @Serializable(with = BigDecimalSerializer::class)
    val coefP1: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val coefP2: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val coefDraw: BigDecimal,
    val status: String
)