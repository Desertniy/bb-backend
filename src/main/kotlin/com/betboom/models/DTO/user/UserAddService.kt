package com.betboom.models.DTO.user

import kotlinx.serialization.Serializable

@Serializable
data class UserAddService(
    val username: String,
    val password: String,
    val idCountry: Int
)
