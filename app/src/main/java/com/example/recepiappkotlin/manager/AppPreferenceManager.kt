package com.example.recepiappkotlin.manager

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class AppPreferenceManager(c : Context) {

    lateinit var cc : Context
    lateinit var preference : SharedPreferences


    init {
        this.cc = c
       // preference = c.getSharedPreferences("APP_PREFERNCE", Context.MODE_PRIVATE)
        preference = PreferenceManager.getDefaultSharedPreferences(cc)

    }

    fun setDarkModeState(enable : Boolean){
        var editor : SharedPreferences.Editor = preference.edit()
        editor.putBoolean("darkMode", enable)
        editor.apply()
    }

    fun getDarkModeState() : Boolean{
        return preference.getBoolean("darkMode", false)
    }





}