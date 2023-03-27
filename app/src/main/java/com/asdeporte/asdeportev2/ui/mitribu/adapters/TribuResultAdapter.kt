package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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

        holder.view.apply {
            bind(item, (context as AppCompatActivity).supportFragmentManager)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}