package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.databinding.BlogViewBinding

class BlogView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: BlogViewBinding? = null

    init {
        binding = BlogViewBinding.inflate(LayoutInflater.from(context), this, true)
    }


    fun setTitle(text: String) {

    }
}