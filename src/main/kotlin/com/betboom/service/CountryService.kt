package com.betboom.service

import com.betboom.models.DTO.country.CountryDto
import com.betboom.repository.interfaceRepo.CountryRepo

class CountryService(private val countryRepo: CountryRepo) {
    suspend fun findAllCountries(): List<CountryDto>{
        return countryRepo.findAllCountries()
    }
}