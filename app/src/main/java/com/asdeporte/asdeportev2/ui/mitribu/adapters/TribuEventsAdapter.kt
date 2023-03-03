package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.view.ViewGroup
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.ui.reusableview.tribu.TribuEventstView
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class TribuEventsAdapter : RecyclerViewAdapterBase<EventData, TribuEventstView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): TribuEventstView =
        TribuEventstView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<TribuEventstView>, position: Int) {
        val item = items[position]

        println("TribuEventsAdapter onBindViewHolder")
        holder.view.apply {
            bind(item)
        }

        holder.view.setOnClickListener {
            println("onItemClick")
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}