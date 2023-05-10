package com.asdeporte.asdeportev2.ui.home.adapters.tabs.distanceAndCategory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.EventDetailViewDistanceAndCategoryItemBinding
import com.asdeporte.asdeportev2.databinding.EventDetailViewPlusMembershipCardBinding
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategorySectionModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.plusMembership.PlusMembershipModel

class SectionsAdapter(
        private val items: List<DistanceAndCategorySectionModel>
) : RecyclerView.Adapter<SectionsAdapter.EventDetailViewDistanceAndCategoryItemHolder>() {


    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): EventDetailViewDistanceAndCategoryItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EventDetailViewDistanceAndCategoryItemBinding.inflate(inflater, parent, false)
        return EventDetailViewDistanceAndCategoryItemHolder(binding)
    }

    override fun onBindViewHolder(holder: EventDetailViewDistanceAndCategoryItemHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class EventDetailViewDistanceAndCategoryItemHolder(private val binding: EventDetailViewDistanceAndCategoryItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        private var clickAction = false

        fun bind(
                item: DistanceAndCategorySectionModel
        ) {
            binding.apply {

                eventAction.setOnClickListener {
                    clickAction = !clickAction
                    llContent.visibility = if (clickAction) View.VISIBLE else View.GONE

                    eventAction.layoutParams.height = 40
                    eventAction.layoutParams.width = 40

                    if (clickAction) {
                        eventAction.setImageDrawable(binding.root.context.getDrawable(R.drawable.ic_add))
                    } else {
                        eventAction.setImageDrawable(binding.root.context.getDrawable(R.drawable.ic_minus))
                    }

                }

                eventAction.layoutParams.height = 40
                eventAction.layoutParams.width = 40
                eventTitle.text = item.title
                eventDescription.text = item.description
                tbTable.visibility = if (item.showTable)
                    View.VISIBLE
                else
                        View.GONE
            }
        }
    }
}


