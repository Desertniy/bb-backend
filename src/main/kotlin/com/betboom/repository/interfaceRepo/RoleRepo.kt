package com.betboom.repository.interfaceRepo

import com.betboom.models.DTO.country.CountryDto
import com.betboom.models.DTO.role.RoleDto
import org.jetbrains.exposed.sql.ResultRow

interface RoleRepo {
    fun ResultRow.resultRow(): RoleDto
    suspend fun findAllRoles(): List<RoleDto>
    suspend fun addRole(nameRole: String): RoleDto
    suspend fun deleteRole(idRole: Int): Boolean
    suspend fun findRoleByName(nameRole: String): RoleDto
}