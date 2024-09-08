package com.betboom

import com.betboom.plugins.*
import com.betboom.repository.implRepo.*
import com.betboom.repository.interfaceRepo.UserRepo
import com.betboom.service.CountryService
import com.betboom.service.EventService
import com.betboom.service.UserService
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.config.ConfigLoader.Companion.load
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    dbInit(environment.config)
    configureCors()
    val userRepo = userRepo()
    val roleRepo = roleRepo()
    val countryRepo = countryRepo()
    val eventRepo = eventRepo()
    val eventLineRepo = eventLineRepo()
    val sportTypeRepo = sportTypeRepo()
    val userEventRepo = userEventRepo()
    val compositionLineEventRepo = compositionLineEventRepo()
    val userService = UserService(userRepo, roleRepo)
    val countryService = CountryService(countryRepo)
    val eventService = EventService(eventRepo, eventLineRepo, sportTypeRepo, compositionLineEventRepo, userEventRepo, userRepo)
    configureSerialization()
    configureRouting(userService, countryService, eventService)
}
