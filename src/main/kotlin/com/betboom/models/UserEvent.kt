package com.betboom.models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object UserEvent: IntIdTable() {
    val idLine = reference("idLine", EventLine.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val idUser = reference("idUser", User.id, onUpdate = ReferenceOption.CASCADE, onDelete = ReferenceOption.CASCADE)
    val amount = decimal("amount", 12, 2)
    val coef = decimal("coef", 4, 2)
    val resultPayout = decimal("resultPayout", 12, 2)
}