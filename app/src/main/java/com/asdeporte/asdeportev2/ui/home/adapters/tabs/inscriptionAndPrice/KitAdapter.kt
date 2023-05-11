package com.asdeporte.asdeportev2.ui.home.adapters.tabs.inscriptionAndPrice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.databinding.EventDetailViewInscriptionAndPriceKitItemBinding
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.KitModel

class KitAdapter(
        private val items: List<KitModel>
) : RecyclerView.Adapter<KitAdapter.EventDetailViewInscriptionAndPriceKitItemHolder>() {


    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): EventDetailViewInscriptionAndPriceKitItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EventDetailViewInscriptionAndPriceKitItemBinding.inflate(inflater, parent, false)
        return EventDetailViewInscriptionAndPriceKitItemHolder(binding)
    }

    override fun onBindViewHolder(holder: EventDetailViewInscriptionAndPriceKitItemHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class EventDetailViewInscriptionAndPriceKitItemHolder(private val binding: EventDetailViewInscriptionAndPriceKitItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(
                item: KitModel
        ) {
            binding.apply {
                ivImage.setImageResource(item.image)
                tvName.text = item.title
            }
        }
    }
}


