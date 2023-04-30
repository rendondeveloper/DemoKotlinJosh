package com.asdeporte.asdeportev2.ui.mitribu.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.SheetNewPostPreviewBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewPostPreviewSheet : BottomSheetDialogFragment() {

    private var _binding: SheetNewPostPreviewBinding? = null
    private val binding get() = _binding!!

    val testEvent = EventData("123",
        "7, 14 y 21K by WomanUp",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")

    interface NewPostPreviewSheetListener {
        fun onMediaPost(item: String?)
    }

    lateinit var listener: NewPostPreviewSheetListener
    lateinit var type: NewPostType

    companion object {
        fun create(listener: NewPostPreviewSheetListener, type: NewPostType) = NewPostPreviewSheet().apply {
            this.listener = listener
            this.type = type
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = SheetNewPostPreviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as MainActivity).hideActionBar()

        /*binding.backImage.setOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }*/

        when (type) {
            NewPostType.TEXT -> {

            }
            NewPostType.ACTIVITY -> {
                setupActivity()
            }
            NewPostType.MEDAL -> {
                setupMedal()
            }
            NewPostType.MEDIA -> {
                setupMedia()
            }
        }

        binding.cancelButton.setOnClickListener {
            this.dismiss()
        }
        binding.postButton.setOnClickListener {
            this.dismiss()
            listener.onMediaPost("")
            //(activity as MainActivity).showTribuTab()
        }

        return root
    }

    private fun setupActivity() {
        binding.topView.visibility = View.VISIBLE
        binding.dataView.visibility = View.VISIBLE
        binding.mediaView.visibility = View.GONE

        val requestOptions = RequestOptions()
            .placeholder(ContextCompat.getDrawable(requireContext(), R.drawable.placeholder_img))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        Glide.with(this)
            .load("https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2022%2F5%2Fimg_1655144427045_TUP-Marquesa-22-logo-nvo-jun-13.JPG")
            .centerCrop()
            .apply(requestOptions)
            .into(binding.eventImage)
    }
    private fun setupMedal() {
        binding.topView.visibility = View.VISIBLE
        binding.dataView.visibility = View.VISIBLE
        binding.mediaView.visibility = View.GONE

        val requestOptions = RequestOptions()
            .placeholder(ContextCompat.getDrawable(requireContext(), R.drawable.placeholder_img))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        Glide.with(this)
            .load("https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2022%2F1%2Fimg_1643913601949_Invierno-22-logo-OK.jpg")
            .centerCrop()
            .apply(requestOptions)
            .into(binding.eventImage)
    }
    private fun setupMedia() {
        binding.topView.visibility = View.GONE
        binding.dataView.visibility = View.GONE
        binding.mediaView.visibility = View.VISIBLE

        binding.mediaPickerView.setOnClickListener {
            binding.mediaPickerView.visibility = View.GONE
            binding.mediaContainerView.visibility = View.VISIBLE
        }
        //
        var requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(8))
        Glide.with(this)
            .load("https://images.unsplash.com/photo-1518365658347-c4906efc5636?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1444&q=80")
            .centerCrop()
            .apply(requestOptions)
            .into(binding.mediaContainerView)
    }

    /*
     Listeners
     */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}