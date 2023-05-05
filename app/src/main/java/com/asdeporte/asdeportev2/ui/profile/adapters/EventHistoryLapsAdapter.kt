package com.asdeporte.asdeportev2.ui.profile.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.ViewEventHistoryBinding
import com.asdeporte.asdeportev2.databinding.ViewEventHistoryDetailItemLapBinding
import com.asdeporte.asdeportev2.ui.profile.details.bottomSheet.model.EventHistoryLapModel
import com.asdeporte.asdeportev2.ui.profile.details.bottomSheet.model.EventHistoryModel
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class EventHistoryLapsAdapter : RecyclerViewAdapterBase<EventHistoryLapModel, EventHistoryLapView>() {

    var onItemClick: ((item: EventHistoryLapModel) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): EventHistoryLapView =
            EventHistoryLapView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<EventHistoryLapView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }
}

class EventHistoryLapView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: ViewEventHistoryDetailItemLapBinding

    init {
        binding = ViewEventHistoryDetailItemLapBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventHistoryLapModel) {
        binding.tvDistance.text = item.distance
        binding.tvTime.text = item.time

    }
}
