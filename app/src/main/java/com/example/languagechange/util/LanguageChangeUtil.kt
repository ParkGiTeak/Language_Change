package com.example.languagechange.util

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import java.util.*

class LanguageChangeUtil {
    companion object {
        const val languageDefault: String = "DEFAULT"
        val sysLanguage: String = Locale.getDefault().language
        var savedLanguage: String? = null

        fun updateBaseContextLocale(context: Context): Context {
            savedLanguage = SharedPreferencesUtil.getLanguage("Save_Lang")
            Log.d("LanguageLog", "저장된 언어: ${savedLanguage}")
            val locale = Locale(savedLanguage)
            Locale.setDefault(locale)
            return updateResourcesLocale(context, locale)
        }

        private fun updateResourcesLocale(context: Context, locale: Locale): Context {
            val config = Configuration(context.resources.configuration)
            config.setLocale(locale)
            return context.createConfigurationContext(config)
        }
    }
}