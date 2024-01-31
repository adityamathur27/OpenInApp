package com.aditya.openinapp.api

import com.aditya.openinapp.Model.Dashboard_response
import retrofit2.Response
import retrofit2.http.GET


interface OpeninApi {

    @GET("api/v1/dashboardNew")
    suspend fun dashboardApi(): Response<Dashboard_response>
}