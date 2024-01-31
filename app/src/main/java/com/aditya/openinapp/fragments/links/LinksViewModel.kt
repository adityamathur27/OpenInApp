package com.aditya.openinapp.fragments.links

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.*
import com.aditya.openinapp.OpenApp
import com.aditya.openinapp.Repo.DashBoard_Repo
import com.aditya.openinapp.Model.Dashboard_response
import com.aditya.openinapp.util.MutableAppropriateLiveData
import com.aditya.openinapp.util.Resource
import com.aditya.openinapp.util.hasInternetConnection
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class LinksViewModel(private val dashboardRepository: DashBoard_Repo, app: Application):AndroidViewModel(app) {

    private val _dashboardResponse = MutableAppropriateLiveData<Resource<Dashboard_response>>()
    val dashboardResponseResult: LiveData<Resource<Dashboard_response>> = _dashboardResponse

    fun getDashboardApiData() {
        viewModelScope.launch {
            safeGetDashboardResponse()
        }
    }
    private suspend fun safeGetDashboardResponse() {
        _dashboardResponse.postValue(Resource.Loading())
        try {
            val connectivityManager = getApplication<OpenApp>().getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            if (hasInternetConnection(connectivityManager)) {
                val response = dashboardRepository.getDashboardApiResponse()
                _dashboardResponse.postValue(handleDashboardApiResponse(response))
            } else {
                _dashboardResponse.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _dashboardResponse.postValue(Resource.Error("Network Failure"))
                else -> _dashboardResponse.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleDashboardApiResponse(response: Response<Dashboard_response>): Resource<Dashboard_response> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}