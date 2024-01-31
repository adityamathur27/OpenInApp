package com.aditya.openinapp.Repo

import com.aditya.openinapp.api.RetrofitInstance


class DashBoard_Repo {
    suspend fun getDashboardApiResponse() =
        RetrofitInstance.api.dashboardApi()
}