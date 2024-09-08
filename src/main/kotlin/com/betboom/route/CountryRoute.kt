package com.betboom.route

import com.betboom.service.CountryService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.countriesRoute(countryService: CountryService){
    route("/country"){
        get("/all"){
            val countries = countryService.findAllCountries()
            call.respond(HttpStatusCode.OK, countries)
        }
    }

}