package com.betboom.models.DTO.event

import kotlinx.serialization.Serializable

@Serializable
data class EventDelete(
    val idEvent: Int,
    val nameLine: String
)
