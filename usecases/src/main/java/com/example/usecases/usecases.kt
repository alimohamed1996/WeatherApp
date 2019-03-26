package com.example.usecases

import android.arch.lifecycle.MutableLiveData
import com.example.entities.City
import com.example.entities.EmptyListEx

fun randomNumberGenerator() = Math.random() * 1000

fun incrementNumber(incrementValue: Int = 1 , mutableLiveData: MutableLiveData<Int>){
    val oldValue = mutableLiveData.value ?: 0
    mutableLiveData.postValue(oldValue + incrementValue)

}

fun switchState(mutableLiveData: MutableLiveData<Boolean>) {
    val oldValue = mutableLiveData.value ?: false
    mutableLiveData.postValue(!oldValue)
}


// usecase 1 : search city by name
// if is searching, then do not trigger action
// city name must not be null
// if all is OK, trigger search


typealias CitiesResult = MutableLiveData<List<City>>

class SearchCityByNameUseCase(private val searching : MutableLiveData<Boolean>
                       ,private val  result: MutableLiveData<List<City>>
                       ,private val repository: CitiesRepository = citiesRepository){
    fun invoke(cityName : String?){
        cityName
            ?.takeUnless { searching.value ?: false }
            ?.takeUnless { cityName.isBlank() }
            ?. also { searching.postValue(true) }
            ?.let { repository.searchCityByName(it) }
            ?.also { result.postValue(it) }
            ?.also { searching.postValue(false) }


    }
}
//
//fun searchCityByName(name: String?
//                     , searching : MutableLiveData<Boolean>
//                     , result: MutableLiveData<List<City>>
//                     ,repository: CitiesRepository = citiesRepository
//) {
//    name
//        ?.takeUnless { searching.value ?: false }
//        ?.takeUnless { name.isBlank() }
//        ?. also { searching.postValue(true) }
//        ?.let { repository.searchCityByName(it) }
//        ?.also { result.postValue(it) }
//        ?.also { searching.postValue(false) }
//
//
//}

// usecase 2 : retrieve favorite cities ids (longs)
// if is retrieving, then do not trigger action
// if favorites is empty, throw an exception
// if favorites not empty, convert them to ids (longs)

class RetrieveFavoriteCitiesIdsUseCase(
    private val retrieving: MutableLiveData<Boolean>,
    private val result: MutableLiveData<List<Long>>,
    private val repository: CitiesRepository = citiesRepository){
    fun invoke (){
        repository
            .takeUnless { retrieving.value ?: false }
            ?.also { retrieving.postValue(true) }
            ?.let { repository.retrieveFavoriteCitiesIds() }
            ?.ifEmpty { throw EmptyListEx("The_List_Is_Empty") }
            ?.map{it.id }
            ?.also { result.postValue(it) }
            ?.also { retrieving.postValue(false) }

    }

}

// usecase 3 : retrieve cities by Ids
// if is retrieving, then do not trigger action
// if all is Ok, trigger action
class RetrieveCitiesByIdsUseCase(
    private val retrieving: MutableLiveData<Boolean>,
    private val result: MutableLiveData<List<City>>,
    private val repository: CitiesRepository = citiesRepository){

    fun invoke(ids: List<Long>){
        ids.takeUnless { retrieving.value ?: false }
            ?.also { retrieving.postValue(true) }
            ?.let { repository.retrieveCitiesByIds(it) }
            ?.also { result.postValue(it) }
            ?.also { retrieving.postValue(false) }
    }
}
//
//fun retrieveCitiesByIds(
//    ids: List<Long>,
//    retrieving: MutableLiveData<Boolean>,
//    result: MutableLiveData<List<City>>,
//    repository: CitiesRepository = citiesRepository
//) {
//
//    ids.takeUnless { retrieving.value ?: false }
//        ?.also { retrieving.postValue(true) }
//        ?.let { repository.retrieveCitiesByIds(it) }
//        ?.also { result.postValue(it) }
//        ?.also { retrieving.postValue(false) }
//}