package com.asdeporte.asdeportev2.ui.home.adapters.tabs.distanceAndCategory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.EventDetailViewDistanceAndCategoryBinding
import com.asdeporte.asdeportev2.databinding.EventDetailViewDistanceAndCategoryItemBinding
import com.asdeporte.asdeportev2.databinding.EventDetailViewPlusMembershipCardBinding
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategoryMainSectionModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategorySectionModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.plusMembership.PlusMembershipModel

class MainSectionsAdapter(
        private val items: List<DistanceAndCategoryMainSectionModel>
) : RecyclerView.Adapter<MainSectionsAdapter.EventDetailViewDistanceAndCategoryMainHolder>() {


    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): EventDetailViewDistanceAndCategoryMainHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EventDetailViewDistanceAndCategoryBinding.inflate(inflater, parent, false)
        return EventDetailViewDistanceAndCategoryMainHolder(binding)
    }

    override fun onBindViewHolder(holder: EventDetailViewDistanceAndCategoryMainHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class EventDetailViewDistanceAndCategoryMainHolder(private val binding: EventDetailViewDistanceAndCategoryBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(
                item: DistanceAndCategoryMainSectionModel
        ) {
            binding.apply {
                tvTitle.text = item.title
                val adapterSection = SectionsAdapter(item.list)
                rvData.apply {
                    this.adapter = adapterSection
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    isNestedScrollingEnabled = true
                }
            }
        }
    }
}


