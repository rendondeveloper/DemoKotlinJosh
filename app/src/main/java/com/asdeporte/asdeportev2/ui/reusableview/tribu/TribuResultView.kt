package com.asdeporte.asdeportev2.ui.reusableview.tribu

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.TribuResultViewBinding
import com.asdeporte.asdeportev2.ui.mitribu.subtabs.FriendMenuBottomSheet
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlin.random.Random

class TribuResultView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    private lateinit var binding: TribuResultViewBinding

    private var isAdmin: Boolean = false

    init {
        binding = TribuResultViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData, fragmentManager: FragmentManager) {

        isAdmin = Random.nextBoolean()

        Glide.with(this)
            .load("https://i.pravatar.cc/50")
            .centerCrop()
            .apply(
                RequestOptions()
                    //.placeholder(R.drawable.placeholder)
                    //.error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true))
            .into(binding.profileImage)

        var requestOptions = RequestOptions()
            .placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder_img))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(4))
        Glide.with(this)
            .load("https://picsum.photos/50/50")
            .centerCrop()
            .apply(requestOptions)
            .into(binding.eventImage)

        if (isAdmin)
            binding.adminBadge.visibility = View.VISIBLE
         else
            binding.adminBadge.visibility = View.GONE

        binding.optionsButton.setOnClickListener {
            item.isAmigo?.let { isAmigo ->
                if(isAmigo){
                    FriendMenuBottomSheet().show(fragmentManager, "MY_BOTTOM_SHEET")
                }else{
                    ModalBottomSheetMembers("add").show(fragmentManager, "MY_BOTTOM_SHEET")
                }
            }

        }
    }
}
