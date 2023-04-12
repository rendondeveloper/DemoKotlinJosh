package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.ScoreData
import com.asdeporte.asdeportev2.databinding.ItemScoreBinding

class ScoreAdapter(private val scores: List<ScoreData>, val listener: () -> Unit) :
    RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScoreViewHolder {
        val binding = ItemScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) =
        holder.bind(scores[position], position, listener)

    override fun getItemCount() = scores.size

    class ScoreViewHolder(val binding: ItemScoreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(score: ScoreData, position: Int, listener: () -> Unit) {
            binding.apply {
                tvPosition.text = score.position.toString()
                tvName.text = score.name
                tvTime.text = score.time

                if (position % 2 == 0) binding.root.setBackgroundResource(R.color.gray_item)

                ivOptions.setOnClickListener {
                    listener()
                }
            }
        }
    }
}