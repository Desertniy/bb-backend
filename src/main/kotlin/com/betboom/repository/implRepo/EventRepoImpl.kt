package com.betboom.repository.implRepo

import com.betboom.models.DTO.event.EventAdd
import com.betboom.models.DTO.event.EventDto
import com.betboom.models.DTO.role.RoleDto
import com.betboom.models.DTO.user.UserAdd
import com.betboom.models.DTO.user.UserDto
import com.betboom.models.Event
import com.betboom.models.Role
import com.betboom.models.User
import com.betboom.plugins.dbQuery
import com.betboom.repository.interfaceRepo.EventRepo
import com.betboom.repository.interfaceRepo.RoleRepo
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.`java-time`.date

fun eventRepo(): EventRepo = object : EventRepo{
    override fun ResultRow.resultRow(): EventDto {
        return EventDto(
            idEvent = this[Event.id].value,
            nameEvent = this[Event.nameEvent],
            dateStartEvent = this[Event.dateStartEvent],
            idType = this[Event.idType].value,
            status = this[Event.status],
        )
    }

    override suspend fun findAllEvent(): List<EventDto> = dbQuery {
        Event.selectAll().map { row ->
            EventDto(
                idEvent = row[Event.id].value,
                nameEvent = row[Event.nameEvent],
                dateStartEvent = row[Event.dateStartEvent],
                idType = row[Event.idType].value,
                status = row[Event.status],
            )
        }
    }

    override suspend fun findAllByCategory(idType: Int): List<EventDto>  = dbQuery{
        Event.select{Event.idType eq idType}.map { row ->
            EventDto(
                idEvent = row[Event.id].value,
                nameEvent = row[Event.nameEvent],
                dateStartEvent = row[Event.dateStartEvent],
                idType = row[Event.idType].value,
                status = row[Event.status],
            )
        }
    }

    override suspend fun findEventById(idEvent: Int): EventDto = dbQuery {
        Event.select{Event.id eq idEvent}.single().resultRow()
    }

    override suspend fun findEventByName(nameEvent: String): EventDto? = dbQuery {
        Event.select{Event.nameEvent eq nameEvent}.singleOrNull()?.resultRow()
    }

    override suspend fun addEvent(event: EventAdd): EventDto = dbQuery {
        val res = Event.insertAndGetId {
            it[nameEvent] = event.nameEvent
            it[dateStartEvent] = event.dateStartEvent
            it[idType] = event.idType
            it[status] = event.status
        }
        Event.select { Event.id eq res }.single().resultRow()
    }

    override suspend fun deleteEvent(idEvent: Int): Boolean = dbQuery {
        val res = Event.deleteWhere { Event.id eq idEvent }
        res > 0
    }

}