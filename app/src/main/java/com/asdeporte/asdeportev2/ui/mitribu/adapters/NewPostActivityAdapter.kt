package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.NewPostActivityViewBinding
import com.asdeporte.asdeportev2.databinding.PostHomeViewBinding
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class NewPostActivityAdapter : RecyclerViewAdapterBase<EventData, NewPostActivityView>(),
    NewPostActivityView.PostsAdapterListener {

    var onPreviewClick: ((item: EventData) -> Unit)? = null
    var onPostClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): NewPostActivityView =
        NewPostActivityView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<NewPostActivityView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item, this@NewPostActivityAdapter)
        }

    }

    override fun onPreview(event: EventData) {
        onPreviewClick?.invoke(event)
    }

    override fun onPost(event: EventData) {
        onPostClick?.invoke(event)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}

class NewPostActivityView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    private lateinit var binding: NewPostActivityViewBinding

    interface PostsAdapterListener {
        fun onPreview(event: EventData)
        fun onPost(event: EventData)
    }
    private lateinit var listener: PostsAdapterListener

    init {
        binding = NewPostActivityViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData, listener: PostsAdapterListener) {

       var requestOptions = RequestOptions()
            .placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder_img))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(4))
        Glide.with(this)
            .load("https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2022%2F5%2Fimg_1655144427045_TUP-Marquesa-22-logo-nvo-jun-13.JPG")
            .centerCrop()
            .apply(requestOptions)
            .into(binding.eventImage)

        binding.previewButton.setOnClickListener {
            //showMenu(it, R.menu.menu_post_options)
            listener.onPreview(item)
        }
        binding.postButton.setOnClickListener {
            //showMenu(it, R.menu.menu_post_options)
            listener.onPost(item)
        }
    }


}
