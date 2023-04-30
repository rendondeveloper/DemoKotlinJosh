package com.asdeporte.asdeportev2.ui.profile.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
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

    fun bind(item: ProfileMenuItem, isFromHome: Boolean, backItem: ProfileMenuItem? = null) {

        if (isFromHome) {
            binding.viewSeparate.visibility = INVISIBLE
            binding.viewTopSeparate.visibility = GONE
        }

        item.icon?.let {
            binding.icon.setImageDrawable(it)
            binding.icon.visibility = VISIBLE
            binding.imageView14.visibility = VISIBLE
        } ?: run {
            binding.icon.visibility = GONE
            binding.imageView14.visibility = GONE
            binding.viewSeparate.visibility = INVISIBLE
        }

        item.section?.let {
            binding.txtSection.text = it
            binding.txtSection.visibility = VISIBLE
            if (isFromHome) {
                binding.viewTopSeparate.visibility = VISIBLE
            }
        } ?: run {
            binding.txtSection.visibility = GONE
        }
        item.title?.let {
            binding.title.text = it
            binding.title.visibility = VISIBLE
        } ?: run {
            binding.title.visibility = GONE
        }

        item.subtitle?.let {
            binding.subtitle.text = it
            binding.subtitle.visibility = VISIBLE
        } ?: run {
            binding.subtitle.visibility = GONE
        }

    }
}
