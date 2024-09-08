package com.betboom.repository.interfaceRepo

import com.betboom.models.DTO.role.RoleDto
import com.betboom.models.DTO.sportTypeDto.SportTypeDto
import com.betboom.models.SportType
import org.jetbrains.exposed.sql.ResultRow

interface SportTypeRepo {
    fun ResultRow.resultRow(): SportTypeDto
    suspend fun findAllSportType(): List<SportTypeDto>
    suspend fun findSportTypeById(idSportType: Int): SportTypeDto
    suspend fun findSportTypeByName(nameSportType: String): SportTypeDto?
    suspend fun addSportType(nameSportType: String): SportTypeDto
    suspend fun deleteSportType(idSportType: Int): Boolean
}