package com.betboom.models.DTO.user

import kotlinx.serialization.Serializable

@Serializable
data class LoginAccount(
    val username: String,
    val password: String
)
