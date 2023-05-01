package com.asdeporte.asdeportev2.ui.profile.details.photos

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.SimpleAdapter
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.ItemHistoryPhotosBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class HistoryPhotosAdapter : RecyclerViewAdapterBase<EventData, HistoryView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): HistoryView =
        HistoryView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<HistoryView>, position: Int) {
        val item = items[position]
        holder.view.apply {
            bind(item)
        }
        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }
}
class HistoryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: ItemHistoryPhotosBinding

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        binding = ItemHistoryPhotosBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData) {
        val layoutHorizontalManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.rvPhotos.layoutManager = layoutHorizontalManager
        val testEvent = EventData("", "", "", "")
        val items = listOf(testEvent, testEvent, testEvent, testEvent)
        val adapter = PhotosAdapter()
        adapter.setItems(items)
        binding.rvPhotos.setHasFixedSize(true)
        binding.rvPhotos.adapter = adapter

        binding.txtShowGallery.setOnClickListener{
            findNavController().safelyNavigate(R.id.galleryPhotosFragment)
        }
    }
}
