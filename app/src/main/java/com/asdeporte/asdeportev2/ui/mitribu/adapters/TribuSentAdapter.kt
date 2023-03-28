package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.ItemSentBinding

class TribuSentAdapter(val sent: MutableList<EventData>, val listener: (EventData) -> Unit) :
    RecyclerView.Adapter<TribuSentAdapter.TribuSentViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TribuSentViewHolder {
        val binding = ItemSentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TribuSentViewHolder(binding, listener)
    }

    override fun onBindViewHolder(
        holder: TribuSentViewHolder,
        position: Int
    ) = holder.bind(sent[position])

    override fun getItemCount() = sent.size

    class TribuSentViewHolder(val binding: ItemSentBinding, val listener: (EventData) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sent: EventData) {
            binding.apply {
binding.tribuLogo
            }
            binding.root.setOnClickListener {
                listener(sent)
            }
        }
    }
}