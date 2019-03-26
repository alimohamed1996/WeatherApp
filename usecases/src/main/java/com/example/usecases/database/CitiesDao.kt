package com.example.usecases.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.entities.City
import com.example.entities.FavoriteCityId

@Dao
interface CitiesDao {
    @Query("select * from City where name like '%' || :name || '%' ")
    fun queryCitiesByName(name: String) :List<City>

    @Query("select * from city where id in (:ids)")
    fun queryCityByIds(ids : List<Long>) :List<City>


}