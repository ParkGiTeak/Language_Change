package com.example.languagechange.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.languagechange.Listener.OnLanguageChangeListener
import com.example.languagechange.R
import com.example.languagechange.adapter.LanguageChangeAdaper
import com.example.languagechange.model.LanguageModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_language_change.*
import kotlinx.android.synthetic.main.fragment_language_change.*

class LanguageChangeFragment: BottomSheetDialogFragment() {
    val languages = mutableListOf<LanguageModel>()
    var lanAdapter : LanguageChangeAdaper = LanguageChangeAdaper(languages)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_language_change, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        addLanguage()
    }

    override fun onDestroy() {
        super.onDestroy()
        clearItem()
    }

    //Todo 바텀시트 getThem()

    private fun setRecyclerView() {
        Log.d("LanguageLog", "setRecyclerView")
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

        rv_language_change.adapter = lanAdapter
        rv_language_change.layoutManager = LinearLayoutManager(context)
    }
    /* BottomSheet 표시할 아이템(언어)추가 */
    fun addLanguage() {
        languages.add(LanguageModel("한국어", "ko", false))
        languages.add(LanguageModel("English", "en", false))
        lanAdapter.notifyDataSetChanged()
    }

    /* languages 아이템 초기화 */
    private fun clearItem() {
        Log.d("LanguageLog", "clearItem")
        languages.clear()
    }

}