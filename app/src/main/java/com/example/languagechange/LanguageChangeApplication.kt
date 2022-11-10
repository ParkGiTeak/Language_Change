package com.example.languagechange

import android.app.Application
import com.example.languagechange.util.LanguageChangeUtil
import com.example.languagechange.util.SharedPreferencesUtil

class LanguageChangeApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferencesUtil.initialize(this)
        LanguageChangeUtil.updateBaseContextLocale(this)
    }
}