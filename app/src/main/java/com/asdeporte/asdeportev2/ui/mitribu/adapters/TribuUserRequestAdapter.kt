package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.view.ViewGroup
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.ui.reusableview.tribu.TribuUserRequestView
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class TribuUserRequestAdapter : RecyclerViewAdapterBase<EventData, TribuUserRequestView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): TribuUserRequestView =
        TribuUserRequestView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<TribuUserRequestView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item, true)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }

        holder.view.rejectButton().setOnClickListener {
            holder.view.hideButtons()
            this.notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}