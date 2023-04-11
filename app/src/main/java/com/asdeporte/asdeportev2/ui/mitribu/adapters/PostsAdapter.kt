package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper
import com.google.android.exoplayer2.ExoPlayer


class PostsAdapter : RecyclerViewAdapterBase<EventData, PostsAdapterView>(),
    PostsAdapterView.PostsAdapterListener {

   public var onItemClick: ((item: EventData) -> Unit)? = null
    var onMenuClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): PostsAdapterView =
        PostsAdapterView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<PostsAdapterView>, position: Int) {
        val item = items[position]
        holder.view.apply {
            bind(item, this@PostsAdapter, (context as AppCompatActivity).supportFragmentManager)

        }
    }

    override fun onItem(event: EventData) {
        onItemClick?.invoke(event)
    }

    override fun onMenu(event: EventData) {
        onMenuClick?.invoke(event)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    override fun onViewAttachedToWindow(holder: ViewWrapper<PostsAdapterView>) {
        super.onViewAttachedToWindow(holder)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onViewRecycled(holder: ViewWrapper<PostsAdapterView>) {
        super.onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: ViewWrapper<PostsAdapterView>): Boolean {
        return super.onFailedToRecycleView(holder)
    }

    override fun onViewDetachedFromWindow(holder: ViewWrapper<PostsAdapterView>) {
        super.onViewDetachedFromWindow(holder)
        holder.view.pause()

    }
    override fun onBindViewHolder(
        holder: ViewWrapper<PostsAdapterView>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)


    }
}