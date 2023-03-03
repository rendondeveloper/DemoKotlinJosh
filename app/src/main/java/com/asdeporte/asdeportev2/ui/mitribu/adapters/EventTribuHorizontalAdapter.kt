package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.view.ViewGroup
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class EventTribuHorizontalAdapter : RecyclerViewAdapterBase<EventData, EventTribuHorizontalView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): EventTribuHorizontalView =
        EventTribuHorizontalView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<EventTribuHorizontalView>, position: Int) {
        val item = items[position]

        println("PostsAdapter onBindViewHolder")
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