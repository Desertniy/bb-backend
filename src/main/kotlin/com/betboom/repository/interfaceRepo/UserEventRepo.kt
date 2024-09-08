package com.betboom.repository.interfaceRepo

import com.betboom.models.DTO.country.CountryDto
import com.betboom.models.DTO.userEvent.UserEventAdd
import com.betboom.models.DTO.userEvent.UserEventDto
import org.jetbrains.exposed.sql.ResultRow

interface UserEventRepo {
    fun ResultRow.resultRow(): UserEventDto
    suspend fun findAllUserEventsByIdEvent(idEvent: Int): List<UserEventDto>
    suspend fun findAllUserEventsByIdLine(idLine: Int): List<UserEventDto>
    suspend fun addUserEvent(userEvent: UserEventAdd): UserEventDto
    suspend fun deleteUserEvent(idUserEvent: Int): Boolean
}