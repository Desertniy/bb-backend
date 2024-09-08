package com.betboom.models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object EventLine: IntIdTable() {
    val idEvent = reference("idEvent", Event.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val idStruct = reference("idStruct", CompositionLineEvent.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val coef = decimal("coef", 4, 2)
    val status = bool("status")
}