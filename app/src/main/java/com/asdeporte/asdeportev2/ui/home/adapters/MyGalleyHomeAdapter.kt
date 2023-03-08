package com.asdeporte.asdeportev2.ui.home.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.MyGalleyHomeAdapterViewBinding
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class MyGalleyHomeAdapter : RecyclerViewAdapterBase<String, MyGalleyHomeAdapterView>() {

    var onItemClick: ((item: String) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): MyGalleyHomeAdapterView =
        MyGalleyHomeAdapterView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<MyGalleyHomeAdapterView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

}

class MyGalleyHomeAdapterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: MyGalleyHomeAdapterViewBinding

    init {
        binding = MyGalleyHomeAdapterViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: String) {

        Glide.with(this)
            .load(item)
            .centerCrop()
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true))
            .into(binding.galleryItem)

    }
}
