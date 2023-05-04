package com.asdeporte.asdeportev2.ui.profile.details.bottomSheet.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.DeviceIntegrationItemBinding
import com.asdeporte.asdeportev2.ui.profile.details.bottomSheet.model.DeviceIntegrationModel
import com.asdeporte.asdeportev2.utils.dpToPx
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class DeviceIntegrationBottomSheetAdapter : RecyclerViewAdapterBase<DeviceIntegrationModel, DeviceIntegrationBottomSheetAdapterView>() {

    var onItemClick: ((item: DeviceIntegrationModel) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): DeviceIntegrationBottomSheetAdapterView =
            DeviceIntegrationBottomSheetAdapterView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<DeviceIntegrationBottomSheetAdapterView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item, onItemClick)
        }
    }
}

class DeviceIntegrationBottomSheetAdapterView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: DeviceIntegrationItemBinding

    init {
        binding = DeviceIntegrationItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: DeviceIntegrationModel, callback: ((item: DeviceIntegrationModel) -> Unit)? = null) {
        var requestOptions = RequestOptions()
                .placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder_img))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)

        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(dpToPx(8)))

        binding.apply {
            tvNameDevice.text = item.title
            tvInfoDevice.text = item.description
            mbAction.apply {
                if (item.isConnect) {
                    text = "Desconectar"
                    background = resources.getDrawable(R.drawable.secondary_button)
                    backgroundTintList = resources.getColorStateList(R.color.black_dynamic)
                    setTextColor(resources.getColor(R.color.black_dynamic))
                } else {
                    text = "Conectar"
                    background = resources.getDrawable(R.drawable.secondary_button_fill)
                    backgroundTintList = resources.getColorStateList(R.color.orange_as_light)
                    setTextColor(resources.getColor(R.color.white))
                }
            }
            item.imageDevice?.let {
                binding.ivDevice.setImageResource(it)
            }
        }
    }
}
