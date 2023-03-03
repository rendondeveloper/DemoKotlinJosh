package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.MyResumeHomeViewBinding
import com.asdeporte.asdeportev2.ui.home.adapters.EventsHorizontalBigAdapter
import com.asdeporte.asdeportev2.ui.home.adapters.MyGalleyHomeAdapter

class MyResumeHomeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private lateinit var binding: MyResumeHomeViewBinding

    init {
        binding = MyResumeHomeViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(events: String) {
        //binding.numberEvents.text = events
    }



}