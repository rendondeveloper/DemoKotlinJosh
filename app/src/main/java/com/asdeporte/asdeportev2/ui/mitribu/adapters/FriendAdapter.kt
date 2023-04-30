package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.content.Context
import android.view.ViewGroup
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.ui.reusableview.tribu.FriendView
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class FriendAdapter(val context: Context) : RecyclerViewAdapterBase<EventData, FriendView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): FriendView =
        FriendView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<FriendView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item, context)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

}