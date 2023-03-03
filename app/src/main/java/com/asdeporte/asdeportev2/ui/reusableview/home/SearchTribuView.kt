package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.SearchTribuViewBinding

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

    private lateinit var binding: SearchTribuViewBinding

    init {
        binding = SearchTribuViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(listener: SearchTribuViewListener) {
        //binding.numberEvents.text = events
        this.listener = listener

        binding.searchView.setOnClickListener {
            listener.onSearch()
        }
        binding.filters.setOnClickListener {
            listener.onFilters()
        }
    }

}