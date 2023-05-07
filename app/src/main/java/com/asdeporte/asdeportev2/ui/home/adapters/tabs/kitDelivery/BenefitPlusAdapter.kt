package com.asdeporte.asdeportev2.ui.home.adapters.tabs.kitDelivery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.databinding.ItemBenefitPlusViewBinding
import com.asdeporte.asdeportev2.ui.home.adapters.tabs.plusMembership.PlusMembershipBenefitAdapter
import com.asdeporte.asdeportev2.ui.home.models.tabs.plusMembership.PlusMembershipModel

class BenefitPlusAdapter(
    private val items: List<PlusMembershipModel>
) : RecyclerView.Adapter<BenefitPlusAdapter.BenefitPlusHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BenefitPlusHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBenefitPlusViewBinding.inflate(inflater, parent, false)
        return BenefitPlusHolder(binding)
    }

    override fun onBindViewHolder(holder: BenefitPlusHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class BenefitPlusHolder(private val binding: ItemBenefitPlusViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: PlusMembershipModel
        ) {
            binding.apply {
                tvTitle.text = item.title
                tvDate.text = item.date
                tvWarning.text = item.warning
                tvWarning.visibility = if (item.warning.isNotEmpty()) View.VISIBLE else View.INVISIBLE
                tvDate.visibility = if (item.date.isNotEmpty()) View.VISIBLE else View.INVISIBLE
                mbAction.text = item.textAction

                val adapter = PlusMembershipBenefitAdapter(item.benefits)

                binding.rvBenefitList.apply {
                    this.adapter = adapter
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    isNestedScrollingEnabled = true
                }
            }
        }
    }
}