package com.betboom.models.DTO.event

import com.betboom.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class EventDto(
    val idEvent: Int,
    val nameEvent: String,
    @Serializable(LocalDateTimeSerializer::class)
    val dateStartEvent: LocalDateTime,
    val idType: Int,
    val status: String
)
