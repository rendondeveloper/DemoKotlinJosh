package com.asdeporte.asdeportev2.ui.home.adapters.tabs.inscriptionAndPrice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.EventDetailViewInscriptionAndPriceInscriptionWayItemBinding
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.InscriptionWayItemModel

class InscriptionWayAdapter(
        private val items: List<InscriptionWayItemModel>
) : RecyclerView.Adapter<InscriptionWayAdapter.EventDetailViewInscriptionAndPriceInscriptionWayItemHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): EventDetailViewInscriptionAndPriceInscriptionWayItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EventDetailViewInscriptionAndPriceInscriptionWayItemBinding.inflate(inflater, parent, false)
        return EventDetailViewInscriptionAndPriceInscriptionWayItemHolder(binding)
    }

    override fun onBindViewHolder(holder: EventDetailViewInscriptionAndPriceInscriptionWayItemHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class EventDetailViewInscriptionAndPriceInscriptionWayItemHolder(private val binding: EventDetailViewInscriptionAndPriceInscriptionWayItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(
                item: InscriptionWayItemModel
        ) {
            binding.apply {
                tvTitle.text = item.title
                tvTitle.visibility = if (item.title.isEmpty()) View.GONE else View.VISIBLE
                tvSubTitle.text = item.subTitle.ifEmpty { binding.root.resources.getText(item.suTitleRes) }
                ivImage.setImageResource(item.image)
            }
        }
    }
}


