package com.example.room.common

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager {

    companion object{

        private val APP_SETTINGS_FILE = "APP_SETTINGS"

        fun getSharedPreference(): SharedPreferences{
            return MyApp.getContext().getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE)
        }

        fun setSomeLongValue(key: String, value: Long){
            val editor = getSharedPreference().edit()
            editor.putLong(key, value)
            editor.commit()
        }

        fun getSomeLongValue(key: String): Long{
            return getSharedPreference().getLong(key, Const.DEFAULT_VALUE_ID_USER)
        }

    }

}