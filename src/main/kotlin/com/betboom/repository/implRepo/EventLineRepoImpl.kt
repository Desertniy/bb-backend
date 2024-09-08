package com.betboom.repository.implRepo

import com.betboom.models.Country
import com.betboom.models.DTO.country.CountryDto
import com.betboom.models.DTO.eventLine.EventLineAdd
import com.betboom.models.DTO.eventLine.EventLineDto
import com.betboom.models.EventLine
import com.betboom.plugins.dbQuery
import com.betboom.repository.interfaceRepo.CountryRepo
import com.betboom.repository.interfaceRepo.EventLineRepo
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.math.BigDecimal

fun eventLineRepo(): EventLineRepo = object: EventLineRepo{
    override fun ResultRow.resultRow(): EventLineDto {
        return EventLineDto(
            idEventLine = this[EventLine.id].value,
            idEvent = this[EventLine.idEvent].value,
            idStruct = this[EventLine.idStruct].value,
            coef = this[EventLine.coef],
            status = this[EventLine.status]
        )
    }

    override suspend fun findAllEventLine(): List<EventLineDto> = dbQuery {
        EventLine.selectAll().map { row -> EventLineDto(
            idEventLine = row[EventLine.id].value,
            idEvent = row[EventLine.idEvent].value,
            idStruct = row[EventLine.idStruct].value,
            coef = row[EventLine.coef],
            status = row[EventLine.status]
        )
        }
    }

    override suspend fun findEventLineById(idLineEvent: Int): EventLineDto? = dbQuery {
        EventLine.select { EventLine.id eq idLineEvent }.singleOrNull()?.resultRow()
    }

    override suspend fun findEventLineByEventStruct(idEvent: Int, idStruct: Int): EventLineDto? = dbQuery{
        EventLine.select { (EventLine.idEvent eq idEvent) and (EventLine.idStruct eq idStruct)}.singleOrNull()?.resultRow()
    }

    override suspend fun findAllEventLineByIdEvent(idEvent: Int): List<EventLineDto> = dbQuery {
        EventLine.select{EventLine.idEvent eq idEvent}.map { row -> EventLineDto(
            idEventLine = row[EventLine.id].value,
            idEvent = row[EventLine.idEvent].value,
            idStruct = row[EventLine.idStruct].value,
            coef = row[EventLine.coef],
            status = row[EventLine.status]
        )
        }
    }

    override suspend fun addEventLine(lineEvent: EventLineAdd): EventLineDto = dbQuery {
        val res = EventLine.insertAndGetId {
            it[idEvent] = lineEvent.idEvent
            it[idStruct] = lineEvent.idStruct
            it[coef] = lineEvent.coef
            it[status] = lineEvent.status
        }
        EventLine.select { EventLine.id eq res }.single().resultRow()
    }

    override suspend fun deleteEventLine(idLineEvent: Int): Boolean = dbQuery {
        val res = EventLine.deleteWhere { EventLine.id eq idLineEvent }
        res > 0
    }

    override suspend fun updateCoefEventLine(idLineEvent: Int, coef: BigDecimal): EventLineDto = dbQuery {
        val res = EventLine.update({EventLine.id eq idLineEvent}){
            it[EventLine.coef] = coef
        }
        EventLine.select { EventLine.id eq res }.single().resultRow()
    }

    override suspend fun updateStatusfEventLine(idLineEvent: Int, status: Boolean): EventLineDto = dbQuery {
        val res = EventLine.update({EventLine.id eq idLineEvent}){
            it[EventLine.status] = status
        }
        EventLine.select { EventLine.id eq res }.single().resultRow()
    }

}