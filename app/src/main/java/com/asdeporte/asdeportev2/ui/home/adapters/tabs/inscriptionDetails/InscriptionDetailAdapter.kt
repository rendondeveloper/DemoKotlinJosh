package com.asdeporte.asdeportev2.ui.home.adapters.tabs.inscriptionDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.databinding.ItemInscriptionDetailBinding
import com.asdeporte.asdeportev2.ui.home.models.tabs.plusMembership.PlusMembershipModel

class InscriptionDetailAdapter(
    private val items: List<PlusMembershipModel>
) : RecyclerView.Adapter<InscriptionDetailAdapter.BenefitPlusHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BenefitPlusHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemInscriptionDetailBinding.inflate(inflater, parent, false)
        return BenefitPlusHolder(binding)
    }

    override fun onBindViewHolder(holder: BenefitPlusHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class BenefitPlusHolder(private val binding: ItemInscriptionDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: PlusMembershipModel
        ) {
            binding.apply {

            }
        }
    }
}