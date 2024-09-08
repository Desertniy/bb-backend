package com.betboom.repository.implRepo

import com.betboom.models.DTO.user.UserAdd
import com.betboom.models.DTO.user.UserDto
import com.betboom.models.User
import com.betboom.plugins.dbQuery
import com.betboom.repository.interfaceRepo.UserRepo
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.math.BigDecimal

fun userRepo(): UserRepo = object : UserRepo{
    override fun ResultRow.resultRow(): UserDto {
        return UserDto(
            idUser = this[User.id].value,
            username = this[User.username],
            password = this[User.password],
            idRole = this[User.idRole].value,
            idCountry = this[User.idCountry].value,
            statusBan = this[User.statusBan],
            startBan = this[User.startBan],
            balance = this[User.balance],
            token = this[User.token]
        )
    }

    override fun ResultRow.resultSalt(): ByteArray {
        return this[User.salt]
    }

    override suspend fun findAllUser(): List<UserDto>  = dbQuery{
        User.selectAll().map { row ->
            UserDto(
                idUser = row[User.id].value,
                username = row[User.username],
                password = row[User.password],
                idRole = row[User.idRole].value,
                idCountry = row[User.idCountry].value,
                statusBan = row[User.statusBan],
                startBan = row[User.startBan],
                balance = row[User.balance],
                token = row[User.token]
            )
        }
    }

    override suspend fun findUserById(idUser: Int): UserDto?  = dbQuery{
        val user = User.select { User.id eq idUser }.singleOrNull()
        user?.resultRow()
    }

    override suspend fun findUserByUsername(username: String): UserDto? = dbQuery {
        val user = User.select { User.username eq username }.singleOrNull()
        user?.resultRow()
    }

    override suspend fun addUser(user: UserAdd): UserDto = dbQuery{
        val result = User.insertAndGetId {
            it[User.username] = user.username
            it[User.password] = user.password
            it[User.salt] = user.salt
            it[User.idRole] = user.idRole
            it[User.idCountry] = user.idCountry
            it[User.statusBan] = user.statusBan
            it[User.startBan] = user.startBan
            it[User.balance] = user.balance
        }
        User.select { User.id eq result }.single().resultRow()
    }

    override suspend fun deleteUser(idUser: Int): Boolean = dbQuery {
        val res = User.deleteWhere { User.id eq idUser }
        res > 0
    }

    override suspend fun addToken(idUser: Int, token: String): UserDto = dbQuery{
        val res = User.update({User.id eq idUser}){
            it[User.token] = token
        }
        User.select { User.id eq idUser }.single().resultRow()
    }

    override suspend fun deleteToken(idUser: Int): Boolean = dbQuery{
        val res = User.update({User.id eq idUser}){
            it[User.token] = null
        }
        res > 0
    }

    override suspend fun updateBalance(idUser: Int, balance: BigDecimal): UserDto = dbQuery{
        val res = User.update({User.id eq idUser}){
            it[User.balance] = balance
        }
        User.select { User.id eq idUser }.single().resultRow()
    }

    override suspend fun getSalt(idUser: Int): ByteArray = dbQuery {
        User.select { User.id eq idUser}.single().resultSalt()
    }

    override suspend fun findUserByToken(token: String): UserDto? = dbQuery{
        User.select { User.token eq token }.singleOrNull()?.resultRow()
    }

}