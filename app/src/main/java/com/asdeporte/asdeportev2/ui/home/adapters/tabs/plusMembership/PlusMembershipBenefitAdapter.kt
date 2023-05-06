package com.asdeporte.asdeportev2.ui.home.adapters.tabs.plusMembership

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.databinding.EventDetailPlusMembershipBenefitBinding
import com.asdeporte.asdeportev2.ui.home.models.tabs.plusMembership.PlusMembershipBenefitModel

class PlusMembershipBenefitAdapter(
        private val items: List<PlusMembershipBenefitModel>
) :
        RecyclerView.Adapter<PlusMembershipBenefitAdapter.EventDetailViewPlusMembershipBenefitHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventDetailViewPlusMembershipBenefitHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = EventDetailPlusMembershipBenefitBinding.inflate(inflater, parent, false)
        return EventDetailViewPlusMembershipBenefitHolder(binding)
    }

    override fun onBindViewHolder(holder: EventDetailViewPlusMembershipBenefitHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class EventDetailViewPlusMembershipBenefitHolder(private val binding: EventDetailPlusMembershipBenefitBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(
                item: PlusMembershipBenefitModel
        ) {
            binding.tvTitle.text = item.title
        }
    }
}

