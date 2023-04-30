package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.databinding.TribeResumeViewBinding

class TribeResumeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: TribeResumeViewBinding? = null

    init {
        binding = TribeResumeViewBinding.inflate(LayoutInflater.from(context), this, true)
    }
}