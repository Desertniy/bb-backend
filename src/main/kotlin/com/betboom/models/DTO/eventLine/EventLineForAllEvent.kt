package com.betboom.models.DTO.eventLine

import com.betboom.models.DTO.compositionLineEventDto.CompositionLineEventDto
import com.betboom.utils.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class EventLineForAllEvent(
    val idEventLine: Int,
    val idEvent: Int,
    val struct: CompositionLineEventDto,
    @Serializable(with = BigDecimalSerializer::class)
    val coef: BigDecimal,
)
