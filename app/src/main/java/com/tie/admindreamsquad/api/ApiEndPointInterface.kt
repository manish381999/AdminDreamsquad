package com.tie.admindreamsquad.api


import com.tie.admindreamsquad.ui.credentials.model.LoginResponse
import com.tie.admindreamsquad.ui.cricket.data.models.ApiResponse
import com.tie.admindreamsquad.ui.cricket.data.models.GetSeriesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
interface ApiEndPointInterface {


    @FormUrlEncoded
    @POST("adminLogin.php")
    fun admin_login(
        @Field("emp_id") mobileNumber: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("insert_cricket_series.php")
    suspend fun submitSeriesData(
        @Field("series_data") seriesData: String // Send series data as JSON string inside form-data
    ): Response<ApiResponse>

    @GET("get_cricket_series.php") // API endpoint path
    suspend fun getCricketSeries(): Response<GetSeriesResponse>

}