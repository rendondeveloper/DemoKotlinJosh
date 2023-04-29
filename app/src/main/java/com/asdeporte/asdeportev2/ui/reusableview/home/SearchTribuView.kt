package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.databinding.SearchTribuViewBinding
import com.asdeporte.asdeportev2.utils.TextUtils

class SearchTribuView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    interface SearchTribuViewListener {
        fun onSearch()
        fun onFilters()
    }
    private lateinit var listener: SearchTribuViewListener
    private var binding: SearchTribuViewBinding? = null

    init {
        binding = SearchTribuViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(listener: SearchTribuViewListener) {
        this.listener = listener
        binding?.subtitle?.text = TextUtils().agregarNegrita("1500 Atletas han encontrado su tribu y ya están practicando su deporte favorito.","1500 Atletas")
        binding?.apply {
            searchTextField.setOnClickListener {
                listener.onSearch()
            }
            filters.setOnClickListener {
                listener.onFilters()
            }
        }

    }

}