package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.ItemReceivedBinding

class TribuReceivedAdapter(private val requests: MutableList<EventData>, val listener: (EventData) -> Unit) :
    RecyclerView.Adapter<TribuReceivedAdapter.TribuReceivedViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TribuReceivedViewHolder {
        val binding = ItemReceivedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TribuReceivedViewHolder(binding, listener)
    }

    override fun onBindViewHolder(
        holder: TribuReceivedViewHolder,
        position: Int
    ) = holder.bind(requests[position])

    override fun getItemCount() = requests.size

    class TribuReceivedViewHolder(val binding: ItemReceivedBinding, val listener: (EventData) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(request: EventData) {

            binding.apply {
                tvName.text = request.official_name
                /*
                Glide.with(binding.root.context)
                    .load(request.logo)
                    .centerCrop()
                    .into(binding.ivImage)
                 */
            }

            binding.root.setOnClickListener {
                listener(request)
            }
        }
    }
}