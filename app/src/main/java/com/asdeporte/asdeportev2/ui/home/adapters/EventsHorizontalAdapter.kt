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
import com.bumptech.glide.Glide

class EventsHorizontalAdapter : RecyclerViewAdapterBase<EventData, EventsHorizontalView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): EventsHorizontalView =
        EventsHorizontalView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<EventsHorizontalView>, position: Int) {
        val item = items[position]
        holder.view.apply {
            bind(item)
        }
        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
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
                height = resources.getDimensionPixelSize(R.dimen.event_card_big_height)
            }
        } else {
            binding.containerView.updateLayoutParams {
                width = resources.getDimensionPixelSize(R.dimen.event_card_normal_width)
            }
            binding.itemCard.updateLayoutParams {
                height = resources.getDimensionPixelSize(R.dimen.event_card_normal_height)
            }
        }

        Glide.with(context)
            .load(R.drawable.events_dummy)
            .into(binding.eventBackground)

    }
}
