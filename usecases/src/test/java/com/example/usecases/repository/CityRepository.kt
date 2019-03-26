package com.example.usecases.repository

import com.example.entities.City
import com.example.entities.FavoriteCityId
import com.example.usecases.CitiesRepository

open class CityRepositoryTest : CitiesRepository{

    override fun searchCityByName(name: String): List<City> {
        return emptyList()
    }

    override fun retrieveFavoriteCitiesIds(): List<FavoriteCityId> {
        return emptyList()
    }

    override fun retrieveCitiesByIds(favoriteCityIds: List<Long>): List<City> {
    return emptyList()
    }

    override fun removeFavoriteCityId(favoriteCityId: FavoriteCityId) {

    }

    override fun addFavoriteCityById(favoriteCityId: FavoriteCityId) {

    }
}