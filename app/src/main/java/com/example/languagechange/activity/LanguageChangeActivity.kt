package com.example.languagechange.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.example.languagechange.util.LanguageChangeUtil
import com.example.languagechange.util.SharedPreferencesUtil
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
        languageIsChecked()

        language_change_button.setOnClickListener {
            val languageChangeFragment = LanguageChangeFragment()
            languageChangeFragment.show(supportFragmentManager, languageChangeFragment.tag)
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageChangeUtil.updateBaseContextLocale(newBase))
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
                        setLocate("ko")
                        setLanguageRestart()
                    }
                    "en" -> {
                        Log.d("LanguageLog", "Position 1 Click!!")
                        setLocate("en")
                        setLanguageRestart()
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

    private fun languageIsChecked() {
        when(LanguageChangeUtil.savedLanguage) {
            LanguageChangeUtil.langDefault -> {
                when (LanguageChangeUtil.sysLanguage) {
                    LanguageChangeUtil.langKo -> {
                        languages[0].isLanguageChecked = true
                        languages[1].isLanguageChecked = false
                    }
                    LanguageChangeUtil.langEn -> {
                        languages[0].isLanguageChecked = false
                        languages[1].isLanguageChecked = true
                    }
                    else -> {
                        Log.e("LanguageLog", "languageIsChecked() Error!")
                    }
                }
            }
            LanguageChangeUtil.langKo -> {
                languages[0].isLanguageChecked = true
                languages[1].isLanguageChecked = false
            }
            LanguageChangeUtil.langEn -> {
                languages[0].isLanguageChecked = false
                languages[1].isLanguageChecked = true
            }
        }
    }

    private fun clearItem() {
        languages.clear()
    }

    fun setLocate(Lang: String) {
        /* 안드로이드 시스템 언어와 같은 언어를 선택하거나,
         현재 저장되어 적용된 언어와 같은 언어로 선택시 변경 못하게 막는 분기처리 */
        if (LanguageChangeUtil.savedLanguage == "DEFAULT" && LanguageChangeUtil.sysLanguage != Lang) {
            SharedPreferencesUtil.addLanguage("Save_Lang", Lang)
        }
        else if (LanguageChangeUtil.savedLanguage != "DEFAULT" && LanguageChangeUtil.savedLanguage != Lang) {
            SharedPreferencesUtil.addLanguage("Save_Lang", Lang)
        }
        else {
            Log.e("LanguageLog", "setLocate() Error!")
        }

    }

    fun setLanguageRestart() {
        val intent: Intent =
            baseContext.packageManager.getLaunchIntentForPackage(baseContext.packageName) as Intent
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        overridePendingTransition(0, 0)
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
    }

}