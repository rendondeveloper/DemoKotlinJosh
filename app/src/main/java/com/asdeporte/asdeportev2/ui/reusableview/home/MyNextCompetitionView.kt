package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.navigation.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.MyNextCompetitionViewBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate

class MyNextCompetitionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: MyNextCompetitionViewBinding? = null

    init {
        binding = MyNextCompetitionViewBinding.inflate(LayoutInflater.from(context), this, true)
        initListener()
    }

    private fun initListener() {
        binding?.ivGoToEventDetail?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("viewFrom", "MyNextCompetition")
            findNavController().safelyNavigate(R.id.toEventDetailAction, bundle)
        }
    }

}