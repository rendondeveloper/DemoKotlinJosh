package com.asdeporte.asdeportev2.ui.reusableview.tribu

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.ItemFriendBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class FriendView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    private lateinit var binding: ItemFriendBinding

    init {
        binding = ItemFriendBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData, context: Context) {

        Glide.with(this)
            .load("https://picsum.photos/600/900")
            .centerCrop()
            .apply(
                RequestOptions()
                    //.placeholder(R.drawable.placeholder)
                    //.error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
            )
            .into(binding.personImage)
    }
}