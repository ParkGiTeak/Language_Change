package com.example.languagechange.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.languagechange.Listener.OnLanguageChangeListener
import com.example.languagechange.R
import com.example.languagechange.adapter.LanguageChangeAdaper
import com.example.languagechange.fragment.LanguageChangeFragment
import com.example.languagechange.model.LanguageModel
import kotlinx.android.synthetic.main.activity_language_change.*

class LanguageChangeActivity : AppCompatActivity() {
    companion object {
        val languages = mutableListOf<LanguageModel>()
        var lanAdapter : LanguageChangeAdaper = LanguageChangeAdaper(languages)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_change)

        setActivityRecyclerView()
        addLanguage()

        language_change_button.setOnClickListener {
            val languageChangeFragment = LanguageChangeFragment()
            languageChangeFragment.show(supportFragmentManager, languageChangeFragment.tag)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clearItem()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity :: class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
        finish()
    }
    private fun setActivityRecyclerView() {
        lanAdapter.setItemClickListener(object : OnLanguageChangeListener {
            override fun onChangeClick(v: View, position: Int) {
                when(languages[position].languageType) {
                    "ko" -> {
                        Log.d("LanguageLog", "Position 0 Click!!")
                    }
                    "en" -> {
                        Log.d("LanguageLog", "Position 1 Click!!")
                    }
                    else -> {
                        Log.e("LanguageLog", "setLocate Error!!")
                    }
                }
            }
        })

        rv_language_change_activity.adapter = lanAdapter
        rv_language_change_activity.layoutManager = LinearLayoutManager(this)
    }

    /* BottomSheet 표시할 아이템(언어)추가 */
    private fun addLanguage() {
        languages.add(LanguageModel("한국어", "ko", false))
        languages.add(LanguageModel("English", "en", false))
        lanAdapter.notifyDataSetChanged()
    }

    fun clearItem() {
        languages.clear()
    }

}