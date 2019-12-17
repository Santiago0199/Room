package com.example.room.common

import android.app.Application
import android.content.Context

class MyApp: Application() {

    companion object{
        private lateinit var instance: MyApp

        fun getContext(): Context{
            return instance
        }
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

}