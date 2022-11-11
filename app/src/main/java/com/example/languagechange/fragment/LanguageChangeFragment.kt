package com.example.languagechange.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.languagechange.Listener.OnLanguageChangeListener
import com.example.languagechange.R
import com.example.languagechange.activity.LanguageChangeActivity.Companion.lanAdapter
import com.example.languagechange.activity.LanguageChangeActivity.Companion.languages
import com.example.languagechange.adapter.LanguageChangeAdaper
import com.example.languagechange.model.LanguageModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_language_change.*
import kotlinx.android.synthetic.main.fragment_language_change.*

class LanguageChangeFragment: BottomSheetDialogFragment() {


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
    }

    override fun getTheme(): Int = R.style.LanguageBottomSheetDialogThem

    private fun setRecyclerView() {
        Log.d("LanguageLog", "setRecyclerView")

        rv_language_change.adapter = lanAdapter
        rv_language_change.layoutManager = LinearLayoutManager(context)
    }
}