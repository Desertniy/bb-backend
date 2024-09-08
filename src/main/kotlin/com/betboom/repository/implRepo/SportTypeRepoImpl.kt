package com.betboom.repository.implRepo

import com.betboom.models.DTO.role.RoleDto
import com.betboom.models.DTO.sportTypeDto.SportTypeDto
import com.betboom.models.Role
import com.betboom.models.SportType
import com.betboom.plugins.dbQuery
import com.betboom.repository.interfaceRepo.SportTypeRepo
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

fun sportTypeRepo(): SportTypeRepo = object : SportTypeRepo{
    override fun ResultRow.resultRow(): SportTypeDto {
        return SportTypeDto(
            idSportType = this[SportType.id].value,
            nameType = this[SportType.nameType]
        )
    }

    override suspend fun findAllSportType(): List<SportTypeDto> = dbQuery {
        SportType.selectAll().map { row -> SportTypeDto(
            idSportType = row[SportType.id].value,
            nameType = row[SportType.nameType]
        ) }
    }

    override suspend fun findSportTypeById(idSportType: Int): SportTypeDto = dbQuery{
        SportType.select{SportType.id eq idSportType}.single().resultRow()
    }

    override suspend fun findSportTypeByName(nameSportType: String): SportTypeDto? = dbQuery {
        SportType.select{SportType.nameType eq nameSportType}.singleOrNull()?.resultRow()
    }

    override suspend fun addSportType(nameSportType: String): SportTypeDto = dbQuery {
        val res = SportType.insertAndGetId {
            it[SportType.nameType] = nameSportType
        }
        SportType.select { SportType.id eq res }.single().resultRow()
    }

    override suspend fun deleteSportType(idSportType: Int): Boolean  = dbQuery{
        val res = SportType.deleteWhere { SportType.id eq idSportType }
        res > 0
    }

}