package com.betboom.service

import com.betboom.models.DTO.user.LoginAccount
import com.betboom.models.DTO.user.UserAdd
import com.betboom.models.DTO.user.UserAddService
import com.betboom.models.DTO.user.UserDto
import com.betboom.repository.interfaceRepo.RoleRepo
import com.betboom.repository.interfaceRepo.UserRepo
import com.betboom.utils.PasswordEncoder
import java.math.BigDecimal

class UserService(private val userRepo: UserRepo, private val roleRepo: RoleRepo) {
    suspend fun registerUser(user: UserAddService): UserDto {
        if (userRepo.findUserByUsername(user.username) != null){
            throw Exception()
        }

        val salt = PasswordEncoder.generateSalt()
        val hashedPassword = PasswordEncoder.hashPassword(user.password, salt)
        val role = roleRepo.findRoleByName("User")
        return userRepo.addUser(
            UserAdd(
                username = user.username,
                password = hashedPassword,
                salt = salt,
                idRole = role.idRole,
                idCountry = user.idCountry,
                statusBan = false,
                startBan = null,
                balance = BigDecimal("0")
            )
        )
    }

    suspend fun checkUser(userLogin: LoginAccount): UserDto{
        val user = userRepo.findUserByUsername(userLogin.username)?: throw Exception()
        val salt = userRepo.getSalt(user.idUser)
        val checkPassword = PasswordEncoder.hashPassword(userLogin.password, salt)
        if (user.password != checkPassword){
            throw Exception()
        }
        return user
    }

    suspend fun addToken(idUser: Int, token: String): UserDto{
        if (userRepo.findUserById(idUser) == null){
            throw Exception()
        }
        return userRepo.addToken(idUser, token)
    }

    suspend fun deleteToken(idUser: Int): Boolean{
        if (userRepo.findUserById(idUser) == null){
            throw Exception()
        }
        return userRepo.deleteToken(idUser)
    }

    suspend fun getUserInformationByToken(token: String): UserDto{
        val user = userRepo.findUserByToken(token)?: throw Exception()
        return user
    }
}