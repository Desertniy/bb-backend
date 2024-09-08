package com.betboom.repository.implRepo

import com.betboom.models.DTO.sportTypeDto.SportTypeDto
import com.betboom.models.DTO.userEvent.UserEventAdd
import com.betboom.models.DTO.userEvent.UserEventDto
import com.betboom.models.SportType
import com.betboom.models.User
import com.betboom.models.UserEvent
import com.betboom.plugins.dbQuery
import com.betboom.repository.interfaceRepo.EventRepo
import com.betboom.repository.interfaceRepo.UserEventRepo
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

fun userEventRepo(): UserEventRepo = object : UserEventRepo{
    override fun ResultRow.resultRow(): UserEventDto {
        return UserEventDto(
            idUserEventDto = this[UserEvent.id].value,
            idLine = this[UserEvent.idLine].value,
            idUser = this[UserEvent.idUser].value,
            amount = this[UserEvent.amount],
            coef = this[UserEvent.coef],
            resultPayout = this[UserEvent.resultPayout]
        )
    }

    override suspend fun findAllUserEventsByIdEvent(idEvent: Int): List<UserEventDto> = dbQuery{
        UserEvent.selectAll().map { row -> UserEventDto(
            idUserEventDto = row[UserEvent.id].value,
            idLine = row[UserEvent.idLine].value,
            idUser = row[UserEvent.idUser].value,
            amount = row[UserEvent.amount],
            coef = row[UserEvent.coef],
            resultPayout = row[UserEvent.resultPayout]
        ) }
    }

    override suspend fun findAllUserEventsByIdLine(idLine: Int): List<UserEventDto> = dbQuery {
        UserEvent.selectAll()
            .filter { it[UserEvent.idLine].value == idLine }
            .map { row -> UserEventDto(
            idUserEventDto = row[UserEvent.id].value,
            idLine = row[UserEvent.idLine].value,
            idUser = row[UserEvent.idUser].value,
            amount = row[UserEvent.amount],
            coef = row[UserEvent.coef],
            resultPayout = row[UserEvent.resultPayout]
        ) }
    }

    override suspend fun addUserEvent(userEvent: UserEventAdd): UserEventDto = dbQuery{
        val res = UserEvent.insertAndGetId {
            it[idLine] = userEvent.idLine
            it[idUser] = userEvent.idUser
            it[amount] = userEvent.amount
            it[coef] = userEvent.coef
            it[resultPayout] = userEvent.resultPayout
        }
        UserEvent.select { UserEvent.id eq res }.single().resultRow()
    }

    override suspend fun deleteUserEvent(idUserEvent: Int): Boolean = dbQuery {
        val res = UserEvent.deleteWhere { UserEvent.id eq idUserEvent }
        res > 0
    }

}