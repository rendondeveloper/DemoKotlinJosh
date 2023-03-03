package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.NewPostMedalViewBinding
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class NewPostMedalAdapter : RecyclerViewAdapterBase<EventData, NewPostMedalView>(),
    NewPostActivityView.PostsAdapterListener {

    var onPreviewClick: ((item: EventData) -> Unit)? = null
    var onPostClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): NewPostMedalView =
        NewPostMedalView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<NewPostMedalView>, position: Int) {
        val item = items[position]

        println("PostsAdapter onBindViewHolder")
        holder.view.apply {
            bind(item, this@NewPostMedalAdapter)
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
class NewPostMedalView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    private lateinit var binding: NewPostMedalViewBinding

    private lateinit var listener: NewPostActivityView.PostsAdapterListener

    init {
        binding = NewPostMedalViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData, listener: NewPostActivityView.PostsAdapterListener) {
        var requestOptions = RequestOptions()
            .placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder_img))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(4))
        Glide.with(this)
            .load("https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2022%2F1%2Fimg_1643913601949_Invierno-22-logo-OK.jpg")
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
