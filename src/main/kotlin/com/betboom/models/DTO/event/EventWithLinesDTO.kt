package com.betboom.models.DTO.event

import com.betboom.models.DTO.eventLine.EventLineDto
import com.betboom.models.DTO.eventLine.EventLineForAllEvent
import com.betboom.models.DTO.sportTypeDto.SportTypeDto
import kotlinx.serialization.Serializable

@Serializable
data class EventWithLinesDTO(
    val event: EventDto,
    val type: SportTypeDto,
    val lines: List<EventLineForAllEvent>
)
