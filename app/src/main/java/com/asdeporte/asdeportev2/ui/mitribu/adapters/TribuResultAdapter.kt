package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.view.ViewGroup
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.ui.reusableview.tribu.TribuResultView
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class TribuResultAdapter : RecyclerViewAdapterBase<EventData, TribuResultView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): TribuResultView =
        TribuResultView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<TribuResultView>, position: Int) {
        val item = items[position]

        println("TribuResultAdapter onBindViewHolder")
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