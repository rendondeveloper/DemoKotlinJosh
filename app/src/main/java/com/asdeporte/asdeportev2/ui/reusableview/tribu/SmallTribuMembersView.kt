package com.asdeporte.asdeportev2.ui.reusableview.tribu

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.databinding.SmallTribuMembersViewBinding


class SmallTribuMembersView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private lateinit var binding: SmallTribuMembersViewBinding

    init {
        binding = SmallTribuMembersViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData() {
        //binding.numberEvents.text = events

    }

}