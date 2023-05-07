package com.asdeporte.asdeportev2.ui.home.adapters.tabs.plusMembership

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.databinding.EventDetailViewPlusMembershipCardBinding
import com.asdeporte.asdeportev2.ui.home.models.tabs.plusMembership.PlusMembershipModel

class PlusMembershipCardAdapter(
    private val items: List<PlusMembershipModel>
) : RecyclerView.Adapter<PlusMembershipCardAdapter.EventDetailViewPlusMembershipCardHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventDetailViewPlusMembershipCardHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EventDetailViewPlusMembershipCardBinding.inflate(inflater, parent, false)
        return EventDetailViewPlusMembershipCardHolder(binding)
    }

    override fun onBindViewHolder(holder: EventDetailViewPlusMembershipCardHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class EventDetailViewPlusMembershipCardHolder(private val binding: EventDetailViewPlusMembershipCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: PlusMembershipModel
        ) {
            binding.apply {
                ivImage.setImageResource(item.image)
                tvTitle.text = item.title
                tvDescription.text = item.date
                tvWarning.text = item.warning
                tvWarning.visibility = if (item.warning.isNotEmpty()) View.VISIBLE else View.GONE
                tvDescription.visibility = if (item.date.isNotEmpty()) View.VISIBLE else View.GONE
                mbAction.text = item.textAction

                val adapter = PlusMembershipBenefitAdapter(item.benefits)

                binding.rvData.apply {
                    this.adapter = adapter
                    setHasFixedSize(true)
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    isNestedScrollingEnabled = true
                }
            }
        }
    }
}


