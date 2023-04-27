package com.asdeporte.asdeportev2.ui.profile.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.ItemHistoryBinding
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class HistoryAdapter : RecyclerViewAdapterBase<EventData, HistoryView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): HistoryView =
        HistoryView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<HistoryView>, position: Int) {
        val item = items[position]
        holder.view.apply {
            bind(item)
        }
        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }
}
class HistoryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: ItemHistoryBinding

    init {
        binding = ItemHistoryBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData) {
        //binding.title.text = item.title
    }
}
