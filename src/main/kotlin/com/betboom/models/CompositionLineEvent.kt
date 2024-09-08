package com.betboom.models

import org.jetbrains.exposed.dao.id.IntIdTable

object CompositionLineEvent: IntIdTable() {
    val nameLine = varchar("nameLine", 255)
}