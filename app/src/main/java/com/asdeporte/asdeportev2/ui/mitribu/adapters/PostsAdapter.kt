package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.view.ViewGroup
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class PostsAdapter : RecyclerViewAdapterBase<EventData, PostsAdapterView>(),
    PostsAdapterView.PostsAdapterListener {

    var onItemClick: ((item: EventData) -> Unit)? = null
    var onMenuClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): PostsAdapterView =
        PostsAdapterView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<PostsAdapterView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item, this@PostsAdapter)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }

    }

    override fun onItem(event: EventData) {
    }

    override fun onMenu(event: EventData) {
        onMenuClick?.invoke(event)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}