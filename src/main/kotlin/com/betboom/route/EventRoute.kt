package com.betboom.route

import com.betboom.models.DTO.event.EventAddBet
import com.betboom.models.DTO.event.EventAddFromReq
import com.betboom.models.DTO.event.EventDelete
import com.betboom.service.EventService
import com.betboom.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.statements.DeleteStatement.Companion.where

fun Route.eventRoute(eventService: EventService, userService: UserService){
    route("/event") {
        get("/all") {
            val events = eventService.getAllEventsWithLines()
            call.respond(HttpStatusCode.OK, events)
        }
        post("/addBet"){
            val token = call.request.headers["Authorization"]?: return@post call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Bed Token"))
            val infoForBet = call.receive<EventAddBet>()
            try {
                val userByToken = userService.getUserInformationByToken(token)
                if (userByToken.idUser != infoForBet.idUser){
                    call.respond(HttpStatusCode.BadRequest, mapOf("status" to "No idUser"))
                }
                eventService.addBet(infoForBet.idUser, infoForBet.idLine, infoForBet.amount)
                call.respond(HttpStatusCode.OK, mapOf("status" to "Bet Register"))
            }
            catch (e: Exception){
                call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Bed Service"))
            }
        }

        post("/addEvent"){
            val token = call.request.headers["Authorization"]?: return@post call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Bed Token"))
            val infoForEvent = call.receive<EventAddFromReq>()
            try{
                val userByToken = userService.getUserInformationByToken(token)
                if (userByToken.idRole != 2) call.respond(HttpStatusCode.BadRequest, mapOf("status" to "No idUser"))
                eventService.addEvent(infoForEvent)
                call.respond(HttpStatusCode.OK, mapOf("status" to "Event added"))
            }
            catch (e:Exception){
                call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Event bad"))
            }
        }

        post("/deleteEvent"){
            val token = call.request.headers["Authorization"]?: return@post call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Bed Token"))
            val infoForEvent = call.receive<EventDelete>()
            try{
                val userByToken = userService.getUserInformationByToken(token)
                if (userByToken.idRole != 2) call.respond(HttpStatusCode.BadRequest, mapOf("status" to "No idUser"))
                eventService.deleteEvent(infoForEvent)
                call.respond(HttpStatusCode.OK, mapOf("status" to "Event deleted"))
            }
            catch (e: Exception){
                call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Event bad"))
            }
        }
    }
}