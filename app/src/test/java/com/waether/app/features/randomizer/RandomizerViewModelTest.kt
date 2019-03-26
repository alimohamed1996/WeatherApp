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
    fun `increment When numberLiveData Value Is Zero Then Update numberLiveData Value To One`(){
        // Arrange
        val viewModel = RandomizerViewModel()
        viewModel.numberLiveData.value = 0
        // Act
        viewModel.increment()

        // Assert
        val result = viewModel.numberLiveData.value
        Assert.assertTrue(result == 1)
    }
    @Test
    fun `stateSwitch when stateLiveData is false Then change it to true`(){
        //Arrange
        val viewModel = RandomizerViewModel()
        viewModel.stateLiveData.value = false

        //Act
        viewModel.stateSwitch()

        //Assert
        val result = viewModel.stateLiveData.value
        Assert.assertTrue(result!!)
    }
    @Test
    fun `stateSwitch when stateLiveData is true Then change it to false`(){
        //Arrange
        val viewModel = RandomizerViewModel()
        viewModel.stateLiveData.value = true

        //Act
        viewModel.stateSwitch()

        //Assert
        val result = viewModel.stateLiveData.value
        Assert.assertFalse(result!!)
    }
    @Test
    fun `init then update stateLiveData to false`(){
        //Arrange
        //Act
        val viewModel = RandomizerViewModel()

        //Assert
        val result = viewModel.stateLiveData.value
        Assert.assertFalse(result!!)
    }
}