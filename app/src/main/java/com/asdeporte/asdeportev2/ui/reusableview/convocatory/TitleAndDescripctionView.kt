package com.asdeporte.asdeportev2.ui.reusableview.convocatory

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.databinding.TitleAndDescripctionViewBinding

class TitleAndDescripctionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private lateinit var binding: TitleAndDescripctionViewBinding

    init {
        binding = TitleAndDescripctionViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(title: String, description: String) {
        binding.titleView.text = title
        binding.descriptionView.text = description

    }

}