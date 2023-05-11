package com.asdeporte.asdeportev2.ui.home.adapters.tabs.inscriptionAndPrice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.databinding.EventDetailViewInscriptionAndPriceEventServicesItemBinding
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.EventServicesItemModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.EventServicesModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.KitModel

class EventServicesAdapter(
        private val items: List<EventServicesItemModel>
) : RecyclerView.Adapter<EventServicesAdapter.EventDetailViewInscriptionAndPriceEventServicesItemHolder>() {


    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): EventDetailViewInscriptionAndPriceEventServicesItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EventDetailViewInscriptionAndPriceEventServicesItemBinding.inflate(inflater, parent, false)
        return EventDetailViewInscriptionAndPriceEventServicesItemHolder(binding)
    }

    override fun onBindViewHolder(holder: EventDetailViewInscriptionAndPriceEventServicesItemHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class EventDetailViewInscriptionAndPriceEventServicesItemHolder(private val binding: EventDetailViewInscriptionAndPriceEventServicesItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(
                item: EventServicesItemModel
        ) {
            binding.apply {
                ivImage.setImageResource(item.image)
                tvTitle.text = item.title
                tvSubTitle.text = item.subTitle
            }
        }
    }
}


