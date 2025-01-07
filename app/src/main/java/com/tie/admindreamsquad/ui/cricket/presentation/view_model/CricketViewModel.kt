package com.tie.admindreamsquad.ui.cricket.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.tie.admindreamsquad.ui.cricket.data.models.ApiResponse
import com.tie.admindreamsquad.ui.cricket.data.models.GetSeriesResponse
import com.tie.admindreamsquad.ui.cricket.data.models.SeriesResponse
import com.tie.admindreamsquad.ui.cricket.data.repository.CricketRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CricketViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CricketRepository = CricketRepository()  // Use the repository

    val seriesResponse: MutableLiveData<List<SeriesResponse.Series>> = MutableLiveData()
    val allSeriesResponse: MutableLiveData<List<GetSeriesResponse.AllSeries>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val responseMessage: MutableLiveData<String> = MutableLiveData()  // To show the API response message

    // Fetch the series from the first API (ApiEndPointInterface)
    fun fetchSeries(offset: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getSeries(offset)
                if (response != null) {
                    seriesResponse.postValue(response.data)
                } else {
                    errorMessage.postValue("Failed to fetch series data.")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Error: ${e.message}")
            }
        }
    }

    // Submit the selected series data to the second API (cricapiApiEndpointInterface)
    fun sendSeriesDataToApi(selectedSeries: SeriesResponse.Series) {
        viewModelScope.launch {
            try {
                // Convert the series data to JSON string
                val seriesDataJson = Gson().toJson(selectedSeries)
                // Log the JSON string before sending it
                Log.d("CricketViewModel", "Sending JSON: $seriesDataJson")

                val response: Response<ApiResponse> = repository.submitSeriesData(seriesDataJson)

                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.status == "success") {
                        responseMessage.postValue(apiResponse.message)
                    } else {
                        responseMessage.postValue("Failed to insert series data.")
                    }
                } else {
                    responseMessage.postValue("Failed to send series data.")
                }
            } catch (e: Exception) {
                responseMessage.postValue("Error: ${e.message}")
            }
        }
    }

    fun fetchAllSeries() {
        viewModelScope.launch {
            try {
                val response = repository.getAllSeries()
                if (response != null && !response.data.isNullOrEmpty()) {
                    allSeriesResponse.postValue(response.data)
                } else {
                    errorMessage.postValue("No series data found or response is null.")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Error fetching all series: ${e.message}")
                Log.e("CricketViewModel", "Exception in fetchAllSeries", e)
            }
        }
    }



}
