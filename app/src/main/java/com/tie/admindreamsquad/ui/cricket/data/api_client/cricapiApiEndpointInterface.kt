package com.tie.admindreamsquad.ui.cricket.data.api_client

import com.tie.admindreamsquad.ui.cricket.data.models.ApiResponse
import com.tie.admindreamsquad.ui.cricket.data.models.SeriesResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface cricapiApiEndpointInterface {

    @GET("series")
    suspend fun getSeries(
        @Query("offset") offset: Int
    ): Response<SeriesResponse>


}
