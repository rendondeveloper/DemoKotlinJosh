package com.asdeporte.asdeportev2.ui.home.adapters.tabs.inscriptionAndPrice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.EventDetailViewInscriptionAndPriceInscriptionPriceItemBinding
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.PriceInscriptionModel

class PriceInscriptionAdapter(
        private val items: List<PriceInscriptionModel>
) : RecyclerView.Adapter<PriceInscriptionAdapter.EventDetailViewInscriptionAndPriceInscriptionPriceItemHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): EventDetailViewInscriptionAndPriceInscriptionPriceItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EventDetailViewInscriptionAndPriceInscriptionPriceItemBinding.inflate(inflater, parent, false)
        return EventDetailViewInscriptionAndPriceInscriptionPriceItemHolder(binding)
    }

    override fun onBindViewHolder(holder: EventDetailViewInscriptionAndPriceInscriptionPriceItemHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class EventDetailViewInscriptionAndPriceInscriptionPriceItemHolder(private val binding: EventDetailViewInscriptionAndPriceInscriptionPriceItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        private var clickAction = false

        fun bind(
                item: PriceInscriptionModel
        ) {
            binding.apply {
                tvTitle.text = binding.root.resources.getText(item.title)

                ivAction.layoutParams.height = 40
                ivAction.layoutParams.width = 40

                ivAction.setOnClickListener {
                    clickAction = !clickAction
                    tlData.visibility = if (clickAction) View.VISIBLE else View.GONE

                    ivAction.layoutParams.height = 40
                    ivAction.layoutParams.width = 40

                    if (clickAction) {
                        ivAction.setImageDrawable(binding.root.context.getDrawable(R.drawable.ic_minus))
                    } else {
                        ivAction.setImageDrawable(binding.root.context.getDrawable(R.drawable.ic_add))
                    }
                }
            }
        }
    }
}


