package com.betboom.service

import com.betboom.models.DTO.event.EventAdd
import com.betboom.models.DTO.event.EventAddFromReq
import com.betboom.models.DTO.event.EventDelete
import com.betboom.models.DTO.event.EventWithLinesDTO
import com.betboom.models.DTO.eventLine.EventLineAdd
import com.betboom.models.DTO.eventLine.EventLineForAllEvent
import com.betboom.models.DTO.userEvent.UserEventAdd
import com.betboom.repository.interfaceRepo.*
import java.math.BigDecimal

class EventService(private val eventRepo: EventRepo,
                   private val eventLineRepo: EventLineRepo,
                   private val sportTypeRepo: SportTypeRepo,
                   private val compositionLineEventRepo: CompositionLineEventRepo,
                   private val userEventRepo: UserEventRepo,
                   private val userRepo: UserRepo){

    suspend fun getAllEventsWithLines(): List<EventWithLinesDTO> {
        val events = eventRepo.findAllEvent()
        return events.map { event ->
            val lines = eventLineRepo.findAllEventLineByIdEvent(event.idEvent)
            EventWithLinesDTO(
                event = event,
                type = sportTypeRepo.findSportTypeById(event.idType),
                lines = lines.map { line ->
                    EventLineForAllEvent(
                        idEventLine = line.idEventLine,
                        idEvent = line.idEvent,
                        struct = compositionLineEventRepo.findCompositionLineEventById(line.idStruct),
                        coef = line.coef,
                    )
                }
            )
        }
    }

    suspend fun addBet(idUser: Int, idLine: Int, amount: BigDecimal): Boolean {
        val line = eventLineRepo.findEventLineById(idLine)?: throw Exception()
        val coef = line.coef
        val user = userRepo.findUserById(idUser)?: throw Exception()
        if (user.balance < amount) throw Exception()
        userRepo.updateBalance(idUser, user.balance - amount)
        userEventRepo.addUserEvent(UserEventAdd(
            idLine = idLine,
            idUser = idUser,
            amount = amount,
            coef = coef,
            resultPayout = amount * coef
        ))
        return true
    }

    suspend fun addEvent(event: EventAddFromReq): Boolean {
        val isEvent = eventRepo.findEventByName(event.nameEvent)
        val isSportType = sportTypeRepo.findSportTypeByName(event.Type)
        if (isEvent != null || isSportType == null) throw Exception()
        val addedEvent = eventRepo.addEvent(EventAdd(event.nameEvent, event.dateStartEvent, isSportType.idSportType, event.status))
        eventLineRepo.addEventLine(EventLineAdd(addedEvent.idEvent, 1, event.coefP1, false))
        eventLineRepo.addEventLine(EventLineAdd(addedEvent.idEvent, 2, event.coefP2, false))
        eventLineRepo.addEventLine(EventLineAdd(addedEvent.idEvent, 3, event.coefDraw, false))
        return true
    }

    suspend fun deleteEvent(event: EventDelete): Boolean{
        val isEvent = eventRepo.findEventById(event.idEvent)
        val idLine = compositionLineEventRepo.findCompositionLineEventByName(event.nameLine) ?: throw Exception()
        val lineEvent = eventLineRepo.findEventLineByEventStruct(event.idEvent, idLine.idCompositionLineEvent) ?: throw Exception()
        val users = userEventRepo.findAllUserEventsByIdLine(lineEvent.idEventLine)
        users.forEach { user ->
            val userById = userRepo.findUserById(user.idUser) ?: throw Exception()
            userRepo.updateBalance(userById.idUser, userById.balance + user.resultPayout)
        }
        eventRepo.deleteEvent(isEvent.idEvent)
        return true
    }
}