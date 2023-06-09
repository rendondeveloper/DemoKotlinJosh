package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.view.ViewGroup
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.ui.reusableview.tribu.TribuSmallView
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class SmallTribuJoinAdapter : RecyclerViewAdapterBase<EventData, TribuSmallView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): TribuSmallView =
        TribuSmallView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<TribuSmallView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item,onItemClick)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }

    }

}