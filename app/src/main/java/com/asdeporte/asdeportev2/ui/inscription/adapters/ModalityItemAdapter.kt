package com.asdeporte.asdeportev2.ui.inscription.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.ViewModalityItemBinding
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class ModalityItemAdapter : RecyclerViewAdapterBase<String, ModalityItemView>(),
    ModalityItemView.ModalityItemListener {

    var onItemClick: ((item: String) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): ModalityItemView =
        ModalityItemView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<ModalityItemView>, position: Int) {
        val item = items[position]

        println("EventsHorizontalBigAdapter onBindViewHolder")
        holder.view.apply {
            bind(item, this@ModalityItemAdapter)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun onItemSelected(item: String) {
        println("onItemSelected: $item")
        onItemClick?.invoke(item)
    }
}

class ModalityItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private lateinit var binding: ViewModalityItemBinding
    private var isActive = false

    interface ModalityItemListener {
        fun onItemSelected(item: String)
    }
    private lateinit var listener: ModalityItemListener

    init {
        binding = ViewModalityItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: String, listener: ModalityItemListener) {
        this.listener = listener

        binding.itemButton.text = item

        binding.itemButton.setOnClickListener {
            println("itemButton click: $isActive")
            this.listener.onItemSelected(item)
            if (isActive) {
                isActive = false
                binding.itemButton.background = ContextCompat.getDrawable(context, R.drawable.secondary_button)
                binding.itemButton.setTextColor(ContextCompat.getColor(context, R.color.label_primary))
            } else {
                isActive = true
                binding.itemButton.background = ContextCompat.getDrawable(context, R.drawable.pill_background)
                binding.itemButton.setTextColor(ContextCompat.getColor(context, R.color.white_dynamic))
            }
        }
        //println("ModalityItemView")

    }
}
