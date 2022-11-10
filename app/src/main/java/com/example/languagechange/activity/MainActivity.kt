package com.example.languagechange.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.languagechange.R
import com.example.languagechange.model.LanguageModel
import com.example.languagechange.util.LanguageChangeUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, LanguageChangeActivity::class.java)

        btn_language_change_settings.setOnClickListener {
            setNextPageIntent(intent)
        }
    }

    private fun setNextPageIntent(intent: Intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        finish()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageChangeUtil.updateBaseContextLocale(newBase))
    }
}