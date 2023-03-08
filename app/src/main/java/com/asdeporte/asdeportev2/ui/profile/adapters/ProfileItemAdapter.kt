package com.asdeporte.asdeportev2.ui.profile.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.ViewEventHorizontalBinding
import com.asdeporte.asdeportev2.databinding.ViewProfileItemBinding
import com.asdeporte.asdeportev2.ui.profile.ProfileMenuItem
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper

class ProfileItemHomeAdapter : RecyclerViewAdapterBase<ProfileMenuItem, ProfileItemView>() {

    var onItemClick: ((item: ProfileMenuItem) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): ProfileItemView =
        ProfileItemView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<ProfileItemView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item, true)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }
    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}
class ProfileItemDetailAdapter : RecyclerViewAdapterBase<ProfileMenuItem, ProfileItemView>() {

    var onItemClick: ((item: ProfileMenuItem) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): ProfileItemView =
        ProfileItemView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<ProfileItemView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item, false)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }
    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}

class ProfileItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: ViewProfileItemBinding

    init {
        binding = ViewProfileItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: ProfileMenuItem, isFromHome: Boolean) {
        item.icon?.let {
            binding.icon.setImageDrawable(it)
        } ?: run {
            binding.icon.visibility = View.GONE
        }

        binding.title.text = item.title

        if (!isFromHome) {
            val typefaceBold = ResourcesCompat.getFont(context, R.font.kanit_semibold)
            binding.title.typeface = typefaceBold
        }

        item.subtitle?.let {
            binding.subtitle.text = it
        } ?: run {
            binding.subtitle.visibility = View.GONE
        }


    }
}
