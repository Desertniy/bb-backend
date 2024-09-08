package com.betboom.repository.implRepo

import com.betboom.models.Country
import com.betboom.models.DTO.country.CountryDto
import com.betboom.plugins.dbQuery
import com.betboom.repository.interfaceRepo.CountryRepo
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

fun countryRepo(): CountryRepo = object: CountryRepo{
    override fun ResultRow.resultRow(): CountryDto {
        return CountryDto(
            idCountry = this[Country.id].value,
            nameCountry = this[Country.nameCountry]
        )
    }

    override suspend fun findAllCountries(): List<CountryDto> = dbQuery {
        Country.selectAll().map { row -> CountryDto(
            idCountry = row[Country.id].value,
            nameCountry = row[Country.nameCountry]
        )
        }
    }

    override suspend fun addCountry(nameCountry: String): CountryDto = dbQuery {
        val country = Country.insertAndGetId {
            it[Country.nameCountry] = nameCountry
        }
        Country.select { Country.id eq country }.single().resultRow()
    }

    override suspend fun deleteCountry(idCountry: Int): Boolean = dbQuery {
        val delCountry = Country.deleteWhere { Country.id eq idCountry }
        delCountry > 0
    }

}