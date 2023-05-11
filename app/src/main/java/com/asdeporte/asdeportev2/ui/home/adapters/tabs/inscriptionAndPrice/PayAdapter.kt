package com.asdeporte.asdeportev2.ui.home.adapters.tabs.inscriptionAndPrice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.databinding.EventDetailViewInscriptionAndPricePayWayItemBinding
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.InscriptionWayItemModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.PayItemModel

class PayAdapter(
        private val items: List<PayItemModel>
) : RecyclerView.Adapter<PayAdapter.EventDetailViewInscriptionAndPricePayWayItemHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): EventDetailViewInscriptionAndPricePayWayItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EventDetailViewInscriptionAndPricePayWayItemBinding.inflate(inflater, parent, false)
        return EventDetailViewInscriptionAndPricePayWayItemHolder(binding)
    }

    override fun onBindViewHolder(holder: EventDetailViewInscriptionAndPricePayWayItemHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class EventDetailViewInscriptionAndPricePayWayItemHolder(private val binding: EventDetailViewInscriptionAndPricePayWayItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(
                item: PayItemModel
        ) {
            binding.apply {
                tvTitle.visibility = if (item.title.isEmpty()) View.GONE else View.VISIBLE
                tvTitle.text = item.title
                ivImage.setImageResource(item.image)
            }
        }
    }
}


