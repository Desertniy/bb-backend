package com.betboom.repository.interfaceRepo

import com.betboom.models.Country
import com.betboom.models.DTO.country.CountryDto
import org.jetbrains.exposed.sql.ResultRow

interface CountryRepo {
    fun ResultRow.resultRow(): CountryDto
    suspend fun findAllCountries(): List<CountryDto>
    suspend fun addCountry(nameCountry: String): CountryDto
    suspend fun deleteCountry(idCountry: Int): Boolean
}