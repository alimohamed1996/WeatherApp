package com.waether.app.features.randomizer

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class RandomizerViewModelTest{
    @JvmField
    @Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun  initThenUpdateNumberLiverDataValueToZero(){
        // Arrange
        // Act
        val viewModel = RandomizerViewModel()

        // Assert
        val result = viewModel.numberLiveData.value
        Assert.assertTrue(result == 0)
    }

    @Test
    fun `increment When numberLiveData Value I sZero Then Update numberLiveData Value To One`(){
        // Arrange
        val viewModel = RandomizerViewModel()
        viewModel.numberLiveData.value = 0
        // Act
        viewModel.increment()

        // Assert
        val result = viewModel.numberLiveData.value
        Assert.assertTrue(result == 1)
    }

}