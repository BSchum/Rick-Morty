package com.ynov.kotlin.rickmorty.presentation

import android.app.Application
import com.ynov.kotlin.rickmorty.data.manager.ApiManager
import com.ynov.kotlin.rickmorty.data.manager.CacheManager
import com.ynov.kotlin.rickmorty.data.repository.DataRepository

class RMApplication: Application() {
    companion object{
        lateinit var app: RMApplication
    }

    var repo : DataRepository =
        DataRepository(
            ApiManager(),
            CacheManager()
        )

    override fun onCreate() {
        super.onCreate()
        app = this
    }

}