package com.asdeporte.asdeportev2.ui.profile.details.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.utils.VectorDrawableUtils
import com.github.vipulasri.timelineview.TimelineView

class TimeLineAdapter(private val mFeedList: List<TimeLineModel>) : RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder>() {

    private lateinit var mLayoutInflater: LayoutInflater

    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineViewHolder {

        if(!::mLayoutInflater.isInitialized) {
            mLayoutInflater = LayoutInflater.from(parent.context)
        }

        return TimeLineViewHolder(mLayoutInflater.inflate(R.layout.item_timeline, parent, false), viewType)
    }

    override fun onBindViewHolder(holder: TimeLineViewHolder, position: Int) {

        val timeLineModel = mFeedList[position]

        when {
            timeLineModel.active -> {
                setMarker(holder, R.drawable.ic_marker_inactive, R.color.gray_secondary_text)
            }
            !timeLineModel.active -> {
                setMarker(holder, R.drawable.ic_marker_active, R.color.orange_as_light)
            }
            else -> {
                setMarker(holder, R.drawable.ic_marker, R.color.orange_as_light)
            }
        }

        holder.date.text = timeLineModel.date
    }

    private fun setMarker(holder: TimeLineViewHolder, drawableResId: Int, colorFilter: Int) {
        holder.timeline.marker = VectorDrawableUtils.getDrawable(holder.itemView.context, drawableResId, ContextCompat.getColor(holder.itemView.context, colorFilter))
    }

    override fun getItemCount() = mFeedList.size

    inner class TimeLineViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {

        val date: TextView = itemView.findViewById(R.id.date)
        val timeline: TimelineView = itemView.findViewById(R.id.timeline)

        init {
            timeline.initLine(viewType)
        }
    }

}