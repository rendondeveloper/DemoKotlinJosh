package com.asdeporte.asdeportev2.ui.home.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.updateLayoutParams
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.ViewEventHorizontalBinding
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper
import com.asdeporte.hermes.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class EventsHorizontalBigAdapter : RecyclerViewAdapterBase<EventData, EventsHorizontalView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): EventsHorizontalView =
        EventsHorizontalView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<EventsHorizontalView>, position: Int) {
        val item = items[position]

        println("EventsHorizontalBigAdapter onBindViewHolder")
        holder.view.apply {
            bind(item, isBig = true)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}
class EventsHorizontalAdapter : RecyclerViewAdapterBase<EventData, EventsHorizontalView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): EventsHorizontalView =
        EventsHorizontalView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<EventsHorizontalView>, position: Int) {
        val item = items[position]

        println("EventsHorizontalAdapter onBindViewHolder")
        holder.view.apply {
            bind(item)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}

class EventsHorizontalView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: ViewEventHorizontalBinding

    init {
        binding = ViewEventHorizontalBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData, isBig: Boolean = false) {
        if (isBig) {
            binding.containerView.updateLayoutParams {
                width = resources.getDimensionPixelSize(R.dimen.event_card_big_width)
            }
            binding.itemCard.updateLayoutParams {
                //width = resources.getDimensionPixelSize(R.dimen.event_card_big_width)
                height = resources.getDimensionPixelSize(R.dimen.event_card_big_height)
            }
        } else {
            binding.containerView.updateLayoutParams {
                width = resources.getDimensionPixelSize(R.dimen.event_card_normal_width)
                //height = resources.getDimensionPixelSize(R.dimen.event_card_normal_height)
            }
            binding.itemCard.updateLayoutParams {
                //width = resources.getDimensionPixelSize(R.dimen.event_card_normal_width)
                height = resources.getDimensionPixelSize(R.dimen.event_card_normal_height)
            }
        }

        Glide.with(this)
            .load("https://picsum.photos/600/900")
            .centerCrop()
            .apply(
                RequestOptions()
                    //.placeholder(R.drawable.placeholder)
                    //.error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true))
            .into(binding.eventBackground)

        Glide.with(this)
            .load("https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2022%2F5%2Fimg_1655144427045_TUP-Marquesa-22-logo-nvo-jun-13.JPG")
            .centerCrop()
            .apply(
                RequestOptions()
                    //.placeholder(R.drawable.placeholder)
                    //.error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true))
            .into(binding.eventLogo)
        println("EventsHorizontalView")

    }
}
