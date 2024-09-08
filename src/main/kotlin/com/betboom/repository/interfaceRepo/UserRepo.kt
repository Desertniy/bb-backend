package com.betboom.repository.interfaceRepo

import com.betboom.models.DTO.country.CountryDto
import com.betboom.models.DTO.user.UserAdd
import com.betboom.models.DTO.user.UserDto
import org.jetbrains.exposed.sql.ResultRow
import java.math.BigDecimal

interface UserRepo {
    fun ResultRow.resultRow(): UserDto
    fun ResultRow.resultSalt(): ByteArray
    suspend fun findAllUser(): List<UserDto>
    suspend fun findUserById(idUser: Int): UserDto?
    suspend fun findUserByUsername(username: String): UserDto?
    suspend fun addUser(user: UserAdd): UserDto
    suspend fun deleteUser(idUser: Int): Boolean
    suspend fun addToken(idUser: Int, token: String): UserDto
    suspend fun deleteToken(idUser: Int): Boolean
    suspend fun updateBalance(idUser: Int, balance: BigDecimal): UserDto
    suspend fun getSalt(idUser: Int): ByteArray
    suspend fun findUserByToken(token: String): UserDto?
}