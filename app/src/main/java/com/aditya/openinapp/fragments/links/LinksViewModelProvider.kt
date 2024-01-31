package com.aditya.openinapp.fragments.links

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aditya.openinapp.Repo.DashBoard_Repo

class LinksViewModelProvider(private val application: Application):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LinksViewModel::class.java)) {
            return LinksViewModel(
                dashboardRepository = DashBoard_Repo(
                ), application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}