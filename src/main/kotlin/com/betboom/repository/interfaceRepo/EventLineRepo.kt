package com.betboom.repository.interfaceRepo

import com.betboom.models.DTO.country.CountryDto
import com.betboom.models.DTO.eventLine.EventLineAdd
import com.betboom.models.DTO.eventLine.EventLineDto
import org.jetbrains.exposed.sql.ResultRow
import java.math.BigDecimal

interface EventLineRepo {
    fun ResultRow.resultRow(): EventLineDto
    suspend fun findAllEventLine(): List<EventLineDto>
    suspend fun findAllEventLineByIdEvent(idEvent: Int): List<EventLineDto>
    suspend fun addEventLine(lineEvent: EventLineAdd): EventLineDto
    suspend fun deleteEventLine(idLineEvent: Int): Boolean
    suspend fun findEventLineById(idLineEvent: Int): EventLineDto?
    suspend fun findEventLineByEventStruct(idEvent: Int, idStruct: Int): EventLineDto?
    suspend fun updateCoefEventLine(idLineEvent: Int, coef: BigDecimal): EventLineDto
    suspend fun updateStatusfEventLine(idLineEvent: Int, status: Boolean): EventLineDto
}