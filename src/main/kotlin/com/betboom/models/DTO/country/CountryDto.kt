package com.betboom.models.DTO.country

import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    val idCountry: Int,
    val nameCountry: String
)
