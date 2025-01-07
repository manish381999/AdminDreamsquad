package com.tie.admindreamsquad.ui.cricket.data.repository

import android.util.Log
import com.tie.admindreamsquad.api.ApiClient
import com.tie.admindreamsquad.ui.cricket.data.api_client.cricapiApiClient
import com.tie.admindreamsquad.ui.cricket.data.models.ApiResponse
import com.tie.admindreamsquad.ui.cricket.data.models.SeriesResponse
import com.google.gson.Gson
import com.tie.admindreamsquad.ui.cricket.data.models.GetSeriesResponse
import retrofit2.Response

class CricketRepository {

    private val apiService = ApiClient.api  // First API client (ApiEndPointInterface)
    private val cricapiService = cricapiApiClient.apiService  // Second API client (cricapiApiEndpointInterface)

    // Fetch series data from the first API (ApiEndPointInterface)
    suspend fun getSeries(offset: Int): SeriesResponse? {
        val response = cricapiService.getSeries(offset)
        return if (response.isSuccessful) {
            response.body()  // Return the body of the response if successful
        } else {
            null  // Handle error case
        }
    }

    // Submit series data to the second API (cricapiApiEndpointInterface)
    suspend fun submitSeriesData(seriesDataJson: String): Response<ApiResponse> {
        // Log the JSON data before sending it
        Log.d("CricketRepository", "Sending Series Data: $seriesDataJson")
        return apiService.submitSeriesData(seriesDataJson)
    }


    // Fetch all series data from the second API (e.g., Get API)
    suspend fun getAllSeries(): GetSeriesResponse? {
        return try {
            val response = apiService.getCricketSeries()
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("CricketRepository", "Error fetching all series: ${response.code()} ${response.message()}")
                null
            }
        } catch (e: Exception) {
            Log.e("CricketRepository", "Exception in getAllSeries: ${e.message}")
            null
        }
    }


}
