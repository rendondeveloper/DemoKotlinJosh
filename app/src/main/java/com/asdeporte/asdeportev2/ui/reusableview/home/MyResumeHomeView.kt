package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.navigation.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.MyResumeHomeViewBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate

class MyResumeHomeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: MyResumeHomeViewBinding? = null

    init {
        binding = MyResumeHomeViewBinding.inflate(LayoutInflater.from(context), this, true)
        binding?.tvShowDetails?.setOnClickListener {
            findNavController().safelyNavigate(R.id.action_navigation_home_to_detailResultsFragment3)
        }
    }

    fun setData(events: String) {
        binding?.apply {
            //numberEvents.text = events
            clInfo.setOnClickListener {
                if (llInfo.visibility == View.GONE) {
                    llInfo.visibility = View.VISIBLE
                    ivRow.setImageResource(R.drawable.arrow_up)
                } else {
                    llInfo.visibility = View.GONE
                    ivRow.setImageResource(R.drawable.arrow_down_line)
                }
            }
        }
    }
}