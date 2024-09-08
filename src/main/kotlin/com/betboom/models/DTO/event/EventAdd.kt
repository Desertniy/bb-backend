package com.betboom.models.DTO.event

import com.betboom.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class EventAdd(
    val nameEvent: String,
    @Serializable(LocalDateTimeSerializer::class)
    val dateStartEvent: LocalDateTime,
    val idType: Int,
    val status: String
)
