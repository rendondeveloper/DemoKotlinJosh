package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.asdeporte.asdeportev2.databinding.PlanPlusViewBinding

class PlanPlusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: PlanPlusViewBinding? = null

    init {
        binding = PlanPlusViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData() {
        binding?.apply {
            tvSeeEverything.text = Html.fromHtml("<u>Ver todos</u>")
            tvSeeEverything.setOnClickListener{
                if(lnMore.isVisible){
                    lnMore.visibility = GONE
                }else{
                    lnMore.visibility = VISIBLE
                    tvSeeEverything.visibility = GONE
                }
            }
        }
    }

}