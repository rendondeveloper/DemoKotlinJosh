package com.asdeporte.asdeportev2.ui.profile.adapters.badget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.BadgeGoalBinding
import com.asdeporte.asdeportev2.utils.dpToPx
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class BadgeAdapter : RecyclerViewAdapterBase<BadgeModel, GoalBadgedCardView>() {

    var onItemClick: ((item: BadgeModel) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): GoalBadgedCardView =
            GoalBadgedCardView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<GoalBadgedCardView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item, onItemClick)
        }
    }
}

class GoalBadgedCardView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: BadgeGoalBinding

    init {
        binding = BadgeGoalBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: BadgeModel, callback : ((item: BadgeModel) -> Unit)? = null) {
        var requestOptions = RequestOptions()
                .placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder_img))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)

        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(dpToPx(8)))
        binding.apply {
            buttonDetail.setOnClickListener{
                callback?.invoke(item)
            }
            badgeNameShort.text = item.nameShort
            badgeYear.text = item.year
            Glide.with(context)
                    .load(item.image ?: item.imageResource?.let { ContextCompat.getDrawable(context, it) })
                    .apply(requestOptions)
                    .into(binding.badgeImage)

        }
    }
}
