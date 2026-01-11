package com.example.myapplication.utils

import android.content.Context
import android.content.SharedPreferences

object SharePreUtils {
    private const val PREFS = "PREFS"

    fun pref(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
    }

    fun getBoolean(context: Context,key: String ="", value: Boolean = false): Boolean{
        return pref(context).getBoolean(key,value)
    }

    fun setBoolean(context: Context,key: String="",value: Boolean= false): Boolean{
        return pref(context).edit().putBoolean(key,value).commit()
    }
}