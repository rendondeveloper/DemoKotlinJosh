package com.asdeporte.asdeportev2.ui.home.adapters.tabs.distanceAndCategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.databinding.EventDetailViewDistanceAndCategoryNoteItemBinding
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategoryNotesModel

class NotesAdapter(
        private val items: List<DistanceAndCategoryNotesModel>
) :
        RecyclerView.Adapter<NotesAdapter.EventDetailViewDistanceAndCategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventDetailViewDistanceAndCategoryHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = EventDetailViewDistanceAndCategoryNoteItemBinding.inflate(inflater, parent, false)
        return EventDetailViewDistanceAndCategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: EventDetailViewDistanceAndCategoryHolder, position: Int) {
        holder.bind(
                position + 1,
                items[position])
    }

    override fun getItemCount(): Int = items.size

    class EventDetailViewDistanceAndCategoryHolder(private val binding: EventDetailViewDistanceAndCategoryNoteItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(
                counter :  Int,
                item: DistanceAndCategoryNotesModel

        ) {
            binding.apply {
                tvNoteNumber.text = counter.toString();
                tvNotaData.text = item.description
            }
        }
    }
}

