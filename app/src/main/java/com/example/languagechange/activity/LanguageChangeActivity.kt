package com.example.languagechange.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.languagechange.R
import com.example.languagechange.fragment.LanguageChangeFragment
import kotlinx.android.synthetic.main.activity_language_change.*

class LanguageChangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_change)

        language_change_button.setOnClickListener {
            val languageChangeFragment = LanguageChangeFragment()
            languageChangeFragment.show(supportFragmentManager, languageChangeFragment.tag)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity :: class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
}