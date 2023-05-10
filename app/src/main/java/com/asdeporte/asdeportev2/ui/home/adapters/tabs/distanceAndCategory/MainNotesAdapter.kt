package com.asdeporte.asdeportev2.ui.home.adapters.tabs.distanceAndCategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.databinding.EventDetailViewDistanceAndCategoryNoteBinding
import com.asdeporte.asdeportev2.databinding.EventDetailViewDistanceAndCategoryNoteItemBinding
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategoryMainNotesModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategoryNotesModel

class MainNotesAdapter(
        private val items: List<DistanceAndCategoryMainNotesModel>
) :
        RecyclerView.Adapter<MainNotesAdapter.EventDetailViewDistanceAndCategoryNoteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventDetailViewDistanceAndCategoryNoteHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = EventDetailViewDistanceAndCategoryNoteBinding.inflate(inflater, parent, false)
        return EventDetailViewDistanceAndCategoryNoteHolder(binding)
    }

    override fun onBindViewHolder(holder: EventDetailViewDistanceAndCategoryNoteHolder, position: Int) {
        holder.bind(
                items[position])
    }

    override fun getItemCount(): Int = items.size

    class EventDetailViewDistanceAndCategoryNoteHolder(private val binding: EventDetailViewDistanceAndCategoryNoteBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(
                item: DistanceAndCategoryMainNotesModel

        ) {
            binding.apply {
                tvTitle.text = item.title;
                val adapter = NotesAdapter(item.list)
                rvData.apply {
                    this.adapter = adapter
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    isNestedScrollingEnabled = true
                }
            }
        }
    }
}

