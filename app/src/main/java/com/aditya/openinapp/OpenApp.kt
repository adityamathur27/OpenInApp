package com.aditya.openinapp

import android.app.Application
import android.content.Context

class OpenApp: Application(){

    companion object{
        var appContext: Context? = null
    }
}