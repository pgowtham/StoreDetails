package com.tps.challenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tps.challenge.Recipe
import com.tps.challenge.School
import com.tps.challenge.network.TPSCoroutineService
import kotlinx.coroutines.launch
import javax.inject.Inject

class StoreFeedViewModel @Inject constructor(
    private val tpsCoroutineService: TPSCoroutineService
) : ViewModel() {


    private val _storeData = MutableLiveData<List<School>>()
    val storeData: LiveData<List<School>> = _storeData

    // LiveData to hold an error state
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    private val _recipeData = MutableLiveData<List<Recipe>>()
    val recipeData: LiveData<List<Recipe>> = _recipeData

    // LiveData to hold an error state
    private val _errorRecipe = MutableLiveData<String>()
    val errorRecipe: LiveData<String> = _error


    fun getStoreDetails() {
        viewModelScope.launch {
            try {
                val response = tpsCoroutineService.getSchoolData()
                _storeData.value = response
                Log.d("Gowtham", response.toString())
            } catch (exception: Exception) {
                _error.value = exception.message
            }
        }
    }


    fun getRecipeDetails() {
        viewModelScope.launch {
            try {
                val response = tpsCoroutineService.getRecipeData()
                _recipeData.value = response
                Log.d("Gowtham", response.toString())
            } catch (exception: Exception) {
                _errorRecipe.value = exception.message
            }
        }
    }

}