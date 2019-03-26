package com.example.usecases

import com.example.entities.City
import com.example.entities.FavoriteCityId
import com.example.usecases.database.WeatherDatabase
import com.example.usecases.database.weatherDatabase

val citiesRepository by lazy {
    CitiesRepositoryImplementer()
}

interface CitiesRepository {
    fun searchCityByName(name : String) : List<City>
    fun retrieveFavoriteCitiesIds() : List<FavoriteCityId>
    fun retrieveCitiesByIds(favoriteCityIds: List<Long>) : List<City>
    fun removeFavoriteCityId(favoriteCityId: FavoriteCityId)
    fun addFavoriteCityById(favoriteCityId: FavoriteCityId)
}

class CitiesRepositoryImplementer(private val database: Lazy<WeatherDatabase> = lazy {  weatherDatabase }) : CitiesRepository {
    override fun searchCityByName(name : String) : List<City> = database.value.citiesDao.queryCitiesByName(name)

    override fun retrieveFavoriteCitiesIds() : List<FavoriteCityId> = database.value.favoritesDao.queryGetAllFavorites()

    override fun retrieveCitiesByIds(favoriteCityIds: List<Long>) : List<City> = database.value.citiesDao.queryCityByIds(favoriteCityIds)

    override fun removeFavoriteCityId(favoriteCityId: FavoriteCityId) = database.value.favoritesDao.removeFavoriteCityId(favoriteCityId)

    override fun addFavoriteCityById(favoriteCityId: FavoriteCityId) = database.value.favoritesDao.addFavoriteCityId(favoriteCityId)
}