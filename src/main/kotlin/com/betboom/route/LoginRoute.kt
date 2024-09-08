package com.betboom.route

import com.betboom.models.DTO.user.LoginAccount
import com.betboom.service.UserService
import com.betboom.utils.PasswordEncoder
import com.betboom.utils.TokenGenerator
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authenticationRoutes(userService: UserService){
    route("/api/auth"){
        post("/login") {
            val user = call.receive<LoginAccount>()
            println(user)
            try {
                val userDTO = userService.checkUser(user)
                val res = userService.addToken(userDTO.idUser, TokenGenerator.encodeToBase64(user.username + "" + user.password))
                call.respond(HttpStatusCode.Accepted, res)
            }
            catch (e:Exception){
                call.respond(HttpStatusCode.NotFound, mapOf("status" to "Not Found User or invalid password"))
            }
        }
    }
}