package com.asdeporte.asdeportev2.ui.reusableview.home


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.MyTribeViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MyTribeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    private var binding: MyTribeViewBinding? = null
    private lateinit var listener: SearchTribuView.SearchTribuViewListener
    init {
        binding = MyTribeViewBinding.inflate(LayoutInflater.from(context), this, true)
    }


    fun setData(listener: SearchTribuView.SearchTribuViewListener) {
        this.listener = listener
        binding?.searchTribu?.setData(listener)
        binding?.let {
            Glide.with(context)
                .load(R.drawable.img_tribu_home)
                .apply(RequestOptions())
                .into(it.imageView8)
        }
    }
}