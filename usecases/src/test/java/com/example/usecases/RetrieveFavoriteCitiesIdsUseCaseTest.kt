package com.example.usecases

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import com.example.entities.EmptyListEx
import com.example.entities.FavoriteCityId
import com.example.usecases.engine.toMutableLiveData
import com.example.usecases.repository.CityRepositoryTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class RetrieveFavoriteCitiesIdsUseCaseTest{

    @JvmField
    @Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    // if favorites not empty, convert them to ids (longs)
    @Test
    fun `invoke with successful response then update result`(){
        //Arrang
        val retrieving = MutableLiveData<Boolean>()
        val result = MutableLiveData<List<Long>>()
        val repository = CityRepositoryTest2()
        val retrieveFavoriteCitiesIdsUseCase = RetrieveFavoriteCitiesIdsUseCase(retrieving,result,repository)

        //Act
        retrieveFavoriteCitiesIdsUseCase.invoke()

        //Assert
        Assert.assertFalse(result.value!!.isNullOrEmpty())

    }
    // if favorites is empty, throw an exception
    @Test(expected =EmptyListEx::class)
    fun `invoke with empty result throw exception`(){
        //Arrang
        val retrieving = MutableLiveData<Boolean>()
        val result = MutableLiveData<List<Long>>()
        val repository = EmptyFavoriteList()
        val retrieveFavoriteCitiesIdsUseCase = RetrieveFavoriteCitiesIdsUseCase(retrieving,result,repository)

        //Act
        retrieveFavoriteCitiesIdsUseCase.invoke()

        //Assert
        Assert.assertTrue(result.value!!.isNullOrEmpty())

    }
    @Test
    fun `invoke with retrieving as true then do not update result`(){
        //Arrang
        val retrieving = true.toMutableLiveData()
        val result = MutableLiveData<List<Long>>()
        val repository = CityRepositoryTest2()
        val retrieveFavoriteCitiesIdsUseCase = RetrieveFavoriteCitiesIdsUseCase(retrieving,result,repository)

        //Act
        retrieveFavoriteCitiesIdsUseCase.invoke()

        //Assert
        Assert.assertTrue(result.value == null)
    }
}



class CityRepositoryTest2 : CityRepositoryTest(){
    val result = listOf<FavoriteCityId>(FavoriteCityId(0L), FavoriteCityId(0L), FavoriteCityId(0L))
    override fun retrieveFavoriteCitiesIds(): List<FavoriteCityId> {
        return result
    }
}

class EmptyFavoriteList : CityRepositoryTest(){
    val result = listOf<FavoriteCityId>()
    override fun retrieveFavoriteCitiesIds(): List<FavoriteCityId> {
        return result
    }
}