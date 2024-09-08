package com.betboom.repository.interfaceRepo

import com.betboom.models.DTO.compositionLineEventDto.CompositionLineEventDto
import com.betboom.models.DTO.country.CountryDto
import org.jetbrains.exposed.sql.ResultRow

interface CompositionLineEventRepo {
    fun ResultRow.resultRow(): CompositionLineEventDto
    suspend fun findAllCompositionLineEvent(): List<CompositionLineEventDto>
    suspend fun findCompositionLineEventById(idCompositionLineEvent: Int): CompositionLineEventDto
    suspend fun findCompositionLineEventByName(nameCompositionLineEvent: String): CompositionLineEventDto?
    suspend fun addCompositionLineEvent(compositionLineEventName: String): CompositionLineEventDto
    suspend fun deleteCompositionLineEvent(idCompositionLineEvent: Int): Boolean
}