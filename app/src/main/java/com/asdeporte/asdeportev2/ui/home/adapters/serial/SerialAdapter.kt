package com.asdeporte.asdeportev2.ui.home.adapters.serial

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.ItemSerialsBinding
import com.asdeporte.asdeportev2.utils.dpToPx
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class SerialAdapter : RecyclerViewAdapterBase<SerialModel, SerialCardView>() {
    var onItemClick: ((item: SerialModel) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): SerialCardView =
        SerialCardView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<SerialCardView>, position: Int) {
        val item = items[position]
        holder.view.apply {
            bind(item, onItemClick)
        }
    }
}

class SerialCardView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: ItemSerialsBinding

    init {
        binding = ItemSerialsBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: SerialModel, callback : ((item: SerialModel) -> Unit)? = null) {
        var requestOptions = RequestOptions()
                .placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder_img))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)

        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(dpToPx(4)))
        binding.apply {
            imgLogo.setOnClickListener{
                callback?.invoke(item)
            }
            Glide.with(context)
                    //.load(item.image ?: item.imageResource?.let { ContextCompat.getDrawable(context, it) })
                    .load( item.imageResource?.let { ContextCompat.getDrawable(context, it) })
                    .apply(requestOptions)
                    .into(imgLogo)
        }
    }
}
