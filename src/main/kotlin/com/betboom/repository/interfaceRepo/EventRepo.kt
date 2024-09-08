package com.betboom.repository.interfaceRepo

import com.betboom.models.DTO.country.CountryDto
import com.betboom.models.DTO.event.EventAdd
import com.betboom.models.DTO.event.EventDto
import com.betboom.models.DTO.user.UserAdd
import com.betboom.models.DTO.user.UserDto
import org.jetbrains.exposed.sql.ResultRow

interface EventRepo {
    fun ResultRow.resultRow(): EventDto
    suspend fun findAllEvent(): List<EventDto>
    suspend fun findAllByCategory(idType: Int): List<EventDto>
    suspend fun findEventById(idEvent: Int): EventDto
    suspend fun findEventByName(nameEvent: String): EventDto?
    suspend fun addEvent(event: EventAdd): EventDto
    suspend fun deleteEvent(idEvent: Int): Boolean
}