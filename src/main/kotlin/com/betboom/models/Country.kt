package com.betboom.models

import org.jetbrains.exposed.dao.id.IntIdTable

object Country: IntIdTable() {
    val nameCountry = varchar("nameCountry", 255)
}