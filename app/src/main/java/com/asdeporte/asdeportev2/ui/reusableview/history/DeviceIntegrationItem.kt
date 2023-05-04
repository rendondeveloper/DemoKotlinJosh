package com.asdeporte.asdeportev2.ui.reusableview.history

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources.Theme
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.DeviceIntegrationItemBinding
import com.asdeporte.asdeportev2.databinding.HistoryItemBinding

@SuppressLint("Recycle")
class DeviceIntegrationItem @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: DeviceIntegrationItemBinding
    private var title: String? = null
    private var image: Int? = null
    private var buttonText: String? = null
    private var description: String? = null
    private var isConnect: Boolean = false

    init {
        binding = DeviceIntegrationItemBinding.inflate(LayoutInflater.from(context), this, true)
        attrs?.let {
            val typeArray = getContext().obtainStyledAttributes(attrs, R.styleable.DeviceItem)

            val maxLength = typeArray.indexCount
            for (counter in 0..maxLength) {
                when (typeArray.getIndex(counter)) {
                    R.styleable.DeviceItem_device_item_title -> {
                        title = typeArray.getString(R.styleable.DeviceItem_device_item_title)
                    }

                    R.styleable.DeviceItem_device_item_image -> {
                        image = typeArray.getResourceId(R.styleable.DeviceItem_device_item_image, R.drawable.check_circuclar)
                    }

                    R.styleable.DeviceItem_device_item_button_text -> {
                        buttonText = typeArray.getString(R.styleable.DeviceItem_device_item_button_text)
                    }

                    R.styleable.DeviceItem_device_item_description -> {
                        description = typeArray.getString(R.styleable.DeviceItem_device_item_description)
                    }

                    R.styleable.DeviceItem_device_item_is_connect -> {
                        isConnect = typeArray.getBoolean(R.styleable.DeviceItem_device_item_is_connect, false)
                    }
                }
            }
            setTexts(title, image, buttonText, description, isConnect)
        }
    }

    private fun setTexts(title: String?, image: Int?, buttonText: String?, description: String?, isConnect: Boolean) {
        binding.apply {
            tvNameDevice.text = title
            tvInfoDevice.text = description
            mbAction.apply {
                text = buttonText
                if (isConnect) {
                    background = resources.getDrawable(R.drawable.secondary_button)
                    backgroundTintList = resources.getColorStateList(R.color.orange_as_light)
                    setTextColor(resources.getColor(R.color.white))
                } else {
                    background = resources.getDrawable(R.drawable.secondary_button)
                    backgroundTintList = resources.getColorStateList(R.color.black_dynamic)
                    setTextColor(resources.getColor(R.color.black_dynamic))
                }
            }
            image?.let {
                binding.ivDevice.setImageResource(it)
            }
        }
    }
}