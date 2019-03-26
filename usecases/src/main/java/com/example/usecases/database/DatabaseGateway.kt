package com.example.usecases.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import com.example.entities.City
import com.example.entities.Coordinates
import com.example.entities.FavoriteCityId
import com.example.usecases.applicationLiveData
import com.example.usecases.getNonnull
import com.google.gson.Gson

val weatherDatabase by lazy {
    initializeDatabase(applicationLiveData.getNonnull())
}

@Database(entities = [City::class,FavoriteCityId::class]
    , version = 1
    ,exportSchema = false)
@TypeConverters(CoordinateTypeConverter::class)
abstract class WeatherDatabase : RoomDatabase(){
    abstract val citiesDao : CitiesDao
    abstract val favoritesDao : FavoritesDao
}


class CoordinateTypeConverter{
    @TypeConverter
    fun toJson(coordinates: Coordinates) = Gson().toJson(coordinates)

    @TypeConverter
    fun fromJson(string: String) = Gson().fromJson(string , Coordinates::class.java)
}