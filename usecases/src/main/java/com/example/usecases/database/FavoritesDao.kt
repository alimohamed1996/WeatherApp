package com.example.usecases.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
interface FavoritesDao {
    @Query("select * from FavoriteCityId")
    fun queryGetAllFavorites() :List<Long>
}