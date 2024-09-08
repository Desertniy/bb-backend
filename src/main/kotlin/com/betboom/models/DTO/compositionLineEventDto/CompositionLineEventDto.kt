package com.betboom.models.DTO.compositionLineEventDto

import kotlinx.serialization.Serializable

@Serializable
data class CompositionLineEventDto(
    val idCompositionLineEvent: Int,
    val nameLine: String
)
