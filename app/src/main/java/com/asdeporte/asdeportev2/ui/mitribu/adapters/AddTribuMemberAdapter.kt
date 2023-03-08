package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.view.ViewGroup
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.ui.reusableview.tribu.AddTribuMemberView
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class AddTribuMemberAdapter : RecyclerViewAdapterBase<EventData, AddTribuMemberView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): AddTribuMemberView =
        AddTribuMemberView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<AddTribuMemberView>, position: Int) {
        val item = items[position]

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