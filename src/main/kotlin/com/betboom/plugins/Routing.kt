package com.betboom.plugins

import com.betboom.route.authenticationRoutes
import com.betboom.route.countriesRoute
import com.betboom.route.eventRoute
import com.betboom.route.userRoute
import com.betboom.service.CountryService
import com.betboom.service.EventService
import com.betboom.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(userService: UserService, countryService: CountryService, eventService: EventService) {
    routing {
        authenticationRoutes(userService)
        userRoute(userService)
        countriesRoute(countryService)
        eventRoute(eventService, userService)
    }
}
