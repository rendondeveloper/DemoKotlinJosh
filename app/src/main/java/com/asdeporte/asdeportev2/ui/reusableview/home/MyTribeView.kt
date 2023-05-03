package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.MyTribeViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.button.MaterialButton

class MyTribeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    private var binding: MyTribeViewBinding? = null
    private lateinit var listener: SearchTribuView.SearchTribuViewListener

    init {
        binding = MyTribeViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(listener: SearchTribuView.SearchTribuViewListener) {
        this.listener = listener
        binding?.searchTribuView?.setData(listener)
        binding?.let {
            Glide.with(context).load(R.drawable.img_tribu_home).apply(RequestOptions()).into(it.imageView8)
        }
    }

    fun showScreen(mod: String) {

        when(mod){
            "Free" -> {
                binding?.tvMyTribeDescription?.visibility = View.VISIBLE
                binding?.tribeResumeView?.visibility = View.GONE
                binding?.tribeItemListView?.visibility = View.GONE
                binding?.tribeResultsView?.visibility = View.GONE
                binding?.searchTribuView?.visibility = View.GONE
            }

            "FreeWithAccount" -> {
                binding?.searchTribuView?.visibility = View.VISIBLE
                binding?.mytribeSeparator1?.visibility = View.GONE
            }

            "PlusNoData" -> {
                binding?.searchTribuView?.visibility = View.VISIBLE
                binding?.mytribeSeparator1?.visibility = View.GONE
                binding?.searchTribuView?.findViewById<TextView>(R.id.title)?.text = "¡CREA O ENCUENTRA TU TRIBU!"
                binding?.searchTribuView?.findViewById<MaterialButton>(R.id.loginButton)?.visibility = View.VISIBLE
            }

            "PlusWithData" -> {
                binding?.searchTribuView?.visibility = View.VISIBLE
                binding?.mytribeSeparator1?.visibility = View.GONE
            }
            "PlusWithDataNextComp" -> {
                binding?.searchTribuView?.visibility = View.GONE
                binding?.mytribeSeparator1?.visibility = View.GONE
                binding?.mytribeWatchDemo?.visibility = View.GONE
                binding?.imageView8?.visibility = View.GONE
                binding?.tribeResumeView?.visibility = View.VISIBLE
                binding?.tribeItemListView?.visibility = View.VISIBLE
                binding?.tribeResultsView?.visibility = View.VISIBLE


            }
            else -> {
                println("NADA")
            }
        }

    }
    fun showScreenFreeWithData() {

    }

}