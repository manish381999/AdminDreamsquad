package com.tie.admindreamsquad.ui.cricket.data.api_client

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object cricapiApiClient {

    private const val BASE_URL = "https://api.cricapi.com/v1/"
    private const val API_KEY = "c1dea35d-738d-41bf-b010-18ca58312fee"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest: Request = chain.request()
                val originalUrl: HttpUrl = originalRequest.url

                // Add the API key to the URL using the URL builder
                val newUrl: HttpUrl = originalUrl.newBuilder()
                    .addQueryParameter("apikey", API_KEY)
                    .build()

                val newRequest: Request = originalRequest.newBuilder()
                    .url(newUrl)  // Replace the original URL with the new one
                    .build()

                chain.proceed(newRequest)  // Proceed with the modified request
            }
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)  // Add the OkHttpClient to Retrofit
            .addConverterFactory(GsonConverterFactory.create())  // Add the Gson converter
            .build()
    }

    val apiService: cricapiApiEndpointInterface by lazy {
        retrofit.create(cricapiApiEndpointInterface::class.java)  // Create the API service
    }
}
