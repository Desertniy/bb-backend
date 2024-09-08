package com.betboom.models

import org.jetbrains.exposed.dao.id.IntIdTable

object Role: IntIdTable() {
    val nameRole = varchar("nameRole", 20)
}
