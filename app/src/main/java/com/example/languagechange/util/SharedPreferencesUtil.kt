package com.example.languagechange.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SharedPreferencesUtil {
    companion object{
        var context: (() -> Context)? = null
        var preference: SharedPreferences? = null
        fun initialize(context: Context) {
            this.context = { context }
            this.preference = context.getSharedPreferences("SaveLanguage", Activity.MODE_PRIVATE)
        }
        fun getLanguage(key: String):String {
            return  if (preference != null) {
                preference!!.getString(key, LanguageChangeUtil.langDefault).toString()
            } else {
                LanguageChangeUtil.langDefault
            }
        }
        fun addLanguage(key: String, value: String) {
            if (preference != null) {
                preference!!.edit().putString(key, value).apply()
            }
        }
    }
}