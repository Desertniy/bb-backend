package com.betboom.route

import com.betboom.models.DTO.user.UserAddService
import com.betboom.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoute(userService: UserService){
    route("/user"){
        post("/register") {
            val user = call.receive<UserAddService>()
            try {
                val reg = userService.registerUser(user)
                call.respond(HttpStatusCode.Created, reg)
            }
            catch (e: Exception){
                call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Bad Request"))
            }
        }

        get("/information"){
            val token = call.request.headers["Authorization"]?: return@get call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Bad Request"))
            try {
                val user = userService.getUserInformationByToken(token)
                call.respond(HttpStatusCode.OK, user)
            }
            catch (e: Exception){
                call.respond(HttpStatusCode.NotFound, mapOf("status" to "Bad Request"))
            }
        }
    }
}