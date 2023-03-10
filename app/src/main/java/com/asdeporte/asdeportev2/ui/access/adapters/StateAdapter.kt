package com.asdeporte.asdeportev2.ui.access.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.databinding.ItemStateBinding

class StateAdapter(private val states: List<String>, val listener: (String) -> Unit) :
    RecyclerView.Adapter<StateAdapter.StateViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StateViewHolder {
        val binding = ItemStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StateViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) =
        holder.bind(states[position])

    override fun getItemCount() = states.size

    class StateViewHolder(val binding: ItemStateBinding, val listener: (String) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(state: String) {
            binding.apply {
                tvState.text = state
                root.setOnClickListener {
                    listener(state)
                }
            }
        }
    }
}