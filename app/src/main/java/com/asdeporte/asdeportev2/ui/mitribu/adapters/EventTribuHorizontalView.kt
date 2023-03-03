package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.EventTribuHorizontalViewBinding
import com.asdeporte.asdeportev2.databinding.PostHomeViewBinding
import com.asdeporte.asdeportev2.utils.dpToPx
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class EventTribuHorizontalView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    private lateinit var binding: EventTribuHorizontalViewBinding

    init {
        binding = EventTribuHorizontalViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData) {

        var requestOptions = RequestOptions()
            .placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder_img))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(dpToPx(9)))
        Glide.with(this)
            .load("https://picsum.photos/600/600")
            .fitCenter()
            .apply(
                requestOptions)
            .into(binding.eventImage)
        println("PostsAdapterView")

        //binding.postOptions.setOnClickListener {
            //showMenu(it, R.menu.menu_post_options)
        //}
    }


}
