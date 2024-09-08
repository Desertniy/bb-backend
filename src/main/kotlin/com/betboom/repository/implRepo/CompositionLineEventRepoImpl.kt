package com.betboom.repository.implRepo

import com.betboom.models.CompositionLineEvent
import com.betboom.models.Country
import com.betboom.models.DTO.compositionLineEventDto.CompositionLineEventDto
import com.betboom.models.DTO.country.CountryDto
import com.betboom.plugins.dbQuery
import com.betboom.repository.interfaceRepo.CompositionLineEventRepo
import com.betboom.repository.interfaceRepo.CountryRepo
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

fun compositionLineEventRepo(): CompositionLineEventRepo = object: CompositionLineEventRepo{
    override fun ResultRow.resultRow(): CompositionLineEventDto {
        return CompositionLineEventDto(
            idCompositionLineEvent = this[CompositionLineEvent.id].value,
            nameLine = this[CompositionLineEvent.nameLine]
        )
    }

    override suspend fun findAllCompositionLineEvent(): List<CompositionLineEventDto> = dbQuery {
        CompositionLineEvent.selectAll().map { row -> CompositionLineEventDto(
            idCompositionLineEvent = row[CompositionLineEvent.id].value,
            nameLine = row[CompositionLineEvent.nameLine]
        )
        }
    }

    override suspend fun findCompositionLineEventById(idCompositionLineEvent: Int): CompositionLineEventDto = dbQuery{
        CompositionLineEvent.select{CompositionLineEvent.id eq idCompositionLineEvent}.single().resultRow()
    }

    override suspend fun findCompositionLineEventByName(nameCompositionLineEvent: String): CompositionLineEventDto? = dbQuery {
        CompositionLineEvent.select {CompositionLineEvent.nameLine eq nameCompositionLineEvent }.singleOrNull()?.resultRow()
    }

    override suspend fun addCompositionLineEvent(compositionLineEventName: String): CompositionLineEventDto = dbQuery {
        val res = CompositionLineEvent.insertAndGetId {
            it[CompositionLineEvent.nameLine] = compositionLineEventName
        }
        CompositionLineEvent.select { CompositionLineEvent.id eq res}.single().resultRow()
    }

    override suspend fun deleteCompositionLineEvent(idCompositionLineEvent: Int): Boolean = dbQuery {
        val res = CompositionLineEvent.deleteWhere { CompositionLineEvent.id eq idCompositionLineEvent }
        res > 0
    }

}