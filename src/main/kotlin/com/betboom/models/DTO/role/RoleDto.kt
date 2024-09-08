package com.betboom.models.DTO.role

import kotlinx.serialization.Serializable

@Serializable
data class RoleDto(
    val idRole: Int,
    val nameRole: String
)
