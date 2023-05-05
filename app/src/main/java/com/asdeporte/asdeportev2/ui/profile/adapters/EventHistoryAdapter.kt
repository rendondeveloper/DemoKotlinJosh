package com.asdeporte.asdeportev2.ui.profile.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.ViewEventHistoryBinding
import com.asdeporte.asdeportev2.ui.profile.details.bottomSheet.model.EventHistoryLapModel
import com.asdeporte.asdeportev2.ui.profile.details.bottomSheet.model.EventHistoryModel
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class EventHistoryAdapter : RecyclerViewAdapterBase<EventHistoryModel, EventHistoryView>() {

    var onItemClick: ((item: EventHistoryModel) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): EventHistoryView =
            EventHistoryView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<EventHistoryView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }
}

class EventHistoryView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: ViewEventHistoryBinding
    private var showDetail = false


    init {
        binding = ViewEventHistoryBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventHistoryModel) {

        binding.tvArrow.apply {
            setImageResource( R.drawable.ic_arrow_down_line_two)
            layoutParams.height = 60
            layoutParams.width = 60
        }

        binding.tvTitle.text = item.title
        binding.tvDate.text = item.date
        binding.tvCategoryDate.text = item.category
        binding.tvLeagueData.text = item.league
        item.image?.let {
            binding.ivImage.setImageResource(it)
        }

        binding.llArrow.setOnClickListener {
            showDetail = !showDetail
            binding.tvArrow.setImageResource(if (showDetail)
                R.drawable.ic_arrow_up_line_two
            else
                R.drawable.ic_arrow_down_line_two)


            binding.viewEventHistoryDetail.clDetail.visibility = if (showDetail)
                View.VISIBLE
            else
                View.GONE
        }

        val adapterCustom = EventHistoryLapsAdapter().apply {
            onItemClick = {
            }
        }

        adapterCustom.setItems(item.listLaps)

        val adapterCustom2 = EventHistoryImageAdapter().apply {
            onItemClick = {
            }
        }
        adapterCustom2.setItems(item.listImage)

        binding.viewEventHistoryDetail.apply {
            tvGoalLabel.text = item.detailGoal
            tvPositionLabel.text = item.detailPosition
            tvTimeLabel.text = item.detailTime
            tvTitleCategoryData.text = "CATEGORÍA - ${item.league}"
            tvTotalDate.text = item.detailTimeTotal
            rvLaps.apply {
                adapter = adapterCustom
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
            }
            rvPhotos.apply {
                adapter = adapterCustom2
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }
}
