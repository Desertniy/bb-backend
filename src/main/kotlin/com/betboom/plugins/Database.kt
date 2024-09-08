package com.betboom.plugins

import com.betboom.models.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

fun dbInit(config: ApplicationConfig){
    val driverClassName = config.property("storage.driverClassName").getString()
    val jdbcURL = config.property("storage.jdbcURL").getString()
    val database = Database.connect(jdbcURL, driverClassName, user = "betboom", password = "1234")
    transaction(database) {
        SchemaUtils.create(CompositionLineEvent, Country, Event, EventLine, Role, SportType, User, UserEvent)
    }
}

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }