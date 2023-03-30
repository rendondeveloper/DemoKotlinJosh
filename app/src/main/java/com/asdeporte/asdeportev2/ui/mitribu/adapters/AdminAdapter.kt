package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.content.Context
import android.view.ViewGroup
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.ui.reusableview.tribu.AdminView
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class AdminAdapter (val context: Context) : RecyclerViewAdapterBase<EventData, AdminView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): AdminView =
        AdminView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<AdminView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item, context)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}