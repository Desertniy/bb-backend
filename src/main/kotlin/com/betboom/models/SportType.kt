package com.betboom.models

import org.jetbrains.exposed.dao.id.IntIdTable

object SportType: IntIdTable() {
    val nameType = varchar("nameType", 50)
}