package com.example.languagechange.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.languagechange.Listener.OnLanguageChangeListener
import com.example.languagechange.R
import com.example.languagechange.model.LanguageModel
import kotlinx.android.synthetic.main.language_item.view.*

class LanguageChangeAdaper(val languageList: List<LanguageModel>) :
    RecyclerView.Adapter<LanguageChangeAdaper.LanguageChangeViewHolder>() {

    private lateinit var litemClickListener: OnLanguageChangeListener

    inner class LanguageChangeViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val view: View = v
        init {
            /* RecyclerViewItem 클릭리스너 */
            view.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION && litemClickListener != null) {
                    litemClickListener.onChangeClick(view, position)
                }
            }
        }
        fun bind(model: LanguageModel) {
            view.tv_language_bottom.text = model.language
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageChangeViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.language_item, parent, false)
        return LanguageChangeViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: LanguageChangeViewHolder, position: Int) {
        val language = languageList[position]

        holder.apply {
            bind(language)
        }
    }

    override fun getItemCount(): Int {
        return languageList.size
    }

    fun setItemClickListener(onItemClickListener: OnLanguageChangeListener) {
        litemClickListener = onItemClickListener
    }
}