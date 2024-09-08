package com.betboom.models.DTO.sportTypeDto

import kotlinx.serialization.Serializable

@Serializable
data class SportTypeDto(
    val idSportType: Int,
    val nameType: String
)
