package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.databinding.MyGalleryHomeViewBinding
import com.asdeporte.asdeportev2.ui.home.adapters.MyGalleyHomeAdapter

class MyGalleryHomeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: MyGalleryHomeViewBinding

    private lateinit var myGalleyTopHomeAdapter: MyGalleyHomeAdapter
    private lateinit var myGalleyBottomHomeAdapter: MyGalleyHomeAdapter

    init {
        binding = MyGalleryHomeViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(events: String) {
        //binding.numberEvents.text = events
        setupGallery()
    }

    private fun setupGallery() {
        val items = listOf("https://images.unsplash.com/photo-1663264891853-a32793671359?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80", "https://images.unsplash.com/photo-1663945618146-11bd7004f8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80", "https://images.unsplash.com/photo-1675087042892-5989eabf5261?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80","https://plus.unsplash.com/premium_photo-1672046218164-57e51f2185f9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80","https://images.unsplash.com/photo-1672699303810-0b55ddad76b5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80","https://images.unsplash.com/photo-1633894233513-0d4bb1731fe5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1794&q=80","https://images.unsplash.com/photo-1661264254942-6c4beebe6e14?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80")

        // Top gallery
        myGalleyTopHomeAdapter = MyGalleyHomeAdapter().apply {
            onItemClick = {
                //EventBottomSheet.create(this@HomeFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }
        }

        binding.topGallery.adapter = myGalleyTopHomeAdapter
        binding.topGallery.setHasFixedSize(true)
        binding.topGallery.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        myGalleyTopHomeAdapter.setItems(items)

        // Bottom gallery
        myGalleyBottomHomeAdapter = MyGalleyHomeAdapter().apply {
            onItemClick = {
                //EventBottomSheet.create(this@HomeFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }
        }

        binding.bottomGallery.adapter = myGalleyBottomHomeAdapter
        binding.bottomGallery.setHasFixedSize(true)
        binding.bottomGallery.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        myGalleyBottomHomeAdapter.setItems(items.reversed())
    }

}