package com.ynov.kotlin.rickmorty.presentation

import android.app.Application
import android.provider.ContactsContract
import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.data.CacheManager
import com.ynov.kotlin.rickmorty.data.DataRepository

class RMApplication: Application() {
    companion object{
        lateinit var app: RMApplication
    }

    var repo : DataRepository = DataRepository(ApiManager(), CacheManager())

    override fun onCreate() {
        super.onCreate()
        app = this
    }

}