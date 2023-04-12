package com.asdeporte.asdeportev2.ui.profile.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.ViewCreditCardBinding
import com.asdeporte.asdeportev2.databinding.ViewProfileItemBinding
import com.asdeporte.asdeportev2.ui.profile.ProfileMenuItem
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class CreditCardAdapter : RecyclerViewAdapterBase<EventData, CreditCardView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): CreditCardView =
        CreditCardView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<CreditCardView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }
    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}
class CreditCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: ViewCreditCardBinding

    init {
        binding = ViewCreditCardBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData) {

        //binding.title.text = item.title


    }
}
