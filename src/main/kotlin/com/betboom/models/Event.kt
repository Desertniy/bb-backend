package com.betboom.models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.`java-time`.datetime

object Event: IntIdTable() {
    val nameEvent = varchar("nameEvent", 255)
    val dateStartEvent = datetime("dateStartEvent")
    val idType = reference("idSportType", SportType.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE )
    val status = varchar("status", 20)
}