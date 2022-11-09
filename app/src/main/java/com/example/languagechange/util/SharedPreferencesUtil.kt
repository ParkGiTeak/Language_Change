package com.example.languagechange.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SharedPreferencesUtil {
    companion object{
        var context: (() -> Context)? = null
        var preference: SharedPreferences? = null
        fun initialize(context: Context) {
            this.context = { context }
            this.preference = PreferenceManager.getDefaultSharedPreferences(context)
        }
        fun getLanguage(key: String):String {
            return  if (preference != null) {
                preference!!.getString(key, "").toString()
            } else {
                ""
            }
        }
        fun addLanguage(key: String, value: String) {
            if (preference != null) {
                preference!!.edit().putString(key, value).apply()
            }
        }
    }
}