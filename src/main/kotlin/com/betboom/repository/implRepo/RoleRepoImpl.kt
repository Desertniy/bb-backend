package com.betboom.repository.implRepo

import com.betboom.models.Country
import com.betboom.models.DTO.country.CountryDto
import com.betboom.models.DTO.role.RoleDto
import com.betboom.models.Role
import com.betboom.plugins.dbQuery
import com.betboom.repository.interfaceRepo.RoleRepo
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

fun roleRepo(): RoleRepo = object : RoleRepo{
    override fun ResultRow.resultRow(): RoleDto {
        return RoleDto(
            idRole = this[Role.id].value,
            nameRole = this[Role.nameRole]
        )
    }

    override suspend fun findAllRoles(): List<RoleDto>  = dbQuery{
        Role.selectAll().map { row ->
            RoleDto(
                idRole = row[Role.id].value,
                nameRole = row[Role.nameRole]
            )
        }
    }

    override suspend fun addRole(nameRole: String): RoleDto  = dbQuery{
        val res = Role.insertAndGetId {
            it[Role.nameRole] = nameRole
        }
        Role.select { Role.id eq res }.single().resultRow()
    }

    override suspend fun deleteRole(idRole: Int): Boolean  = dbQuery{
        val res = Role.deleteWhere { Role.id eq idRole }
        res > 0
    }

    override suspend fun findRoleByName(nameRole: String): RoleDto = dbQuery {
        Role.select { Role.nameRole eq nameRole }.single().resultRow()
    }

}

