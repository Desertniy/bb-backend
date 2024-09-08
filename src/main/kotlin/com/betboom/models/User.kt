package com.betboom.models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.`java-time`.datetime

object User: IntIdTable() {
    val username = varchar("username", 255)
    val password = varchar("password", 32)
    val salt = binary("salt")
    val idRole  = reference("idRole", Role.id, onUpdate = ReferenceOption.CASCADE, onDelete = ReferenceOption.CASCADE)
    val idCountry = reference("idCountry", Country.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val statusBan = bool("statusBan")
    val startBan = datetime("startBan").nullable()
    val balance = decimal("userBalance", 12, 2)
    val token = varchar("token", 255).nullable()
}