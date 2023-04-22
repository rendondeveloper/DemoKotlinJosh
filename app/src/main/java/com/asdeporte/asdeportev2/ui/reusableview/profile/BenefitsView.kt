package com.asdeporte.asdeportev2.ui.reusableview.profile

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.BenefitsViewBinding

class BenefitsView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private companion object {
        const val BACKGROUND_WHITE: Int = 0
        const val BACKGROUND_DARK: Int = 1
        const val IMAGE_SMALL: Int = 0
        const val IMAGE_MEDIUM: Int = 1
    }

    private var binding: BenefitsViewBinding
    private var backgroundType: Int? = null
    private var styleImage: Int = IMAGE_SMALL
    private var title: String? = null
    private var description: String? = null
    private var promotion: String? = null
    private var resource: Int? = null

    init {
        binding = BenefitsViewBinding.inflate(LayoutInflater.from(context), this, true)
        attrs?.let {
            val typeArray = getContext().obtainStyledAttributes(attrs, R.styleable.BenefitsView)

            val maxLength = typeArray.indexCount
            for (counter in 0..maxLength) {
                when (typeArray.getIndex(counter)) {
                    R.styleable.BenefitsView_benefit_background -> {
                        backgroundType = typeArray.getInt(R.styleable.BenefitsView_benefit_background, BACKGROUND_WHITE)
                    }
                    R.styleable.BenefitsView_benefit_title -> {
                        title = typeArray.getString(R.styleable.BenefitsView_benefit_title)
                    }
                    R.styleable.BenefitsView_benefit_description -> {
                        description = typeArray.getString(R.styleable.BenefitsView_benefit_description)
                    }
                    R.styleable.BenefitsView_benefit_promotion -> {
                        promotion = typeArray.getString(R.styleable.BenefitsView_benefit_promotion)
                    }
                    R.styleable.BenefitsView_benefit_icon -> {
                        resource = typeArray.getResourceId(R.styleable.BenefitsView_benefit_icon, R.drawable.check_circuclar)
                    }
                    R.styleable.BenefitsView_benefit_style_image -> {
                        styleImage = typeArray.getInt(R.styleable.BenefitsView_benefit_style_image, IMAGE_SMALL)
                    }
                }
            }
            setBackgroundAndColorText(backgroundType)
            setTexts(title, description, promotion)
            setImage(resource, styleImage)
        }
    }

    private fun setImage(resource: Int?, styleImage : Int) {
        when(styleImage){
            IMAGE_MEDIUM -> {
                binding.benefitImage.layoutParams.height = 80
                binding.benefitImage.layoutParams.width = 80
            }
            IMAGE_SMALL -> {
                binding.benefitImage.layoutParams.height = 50
                binding.benefitImage.layoutParams.width = 50
            }
        }

        resource?.let {
            binding.benefitImage.setImageResource(it)
        }
    }

    private fun setTexts(title: String?, description: String?, promotion: String?) {
        binding.apply {
            benefitTitle.text = title
            benefitDescription.text = description
            promotion?.let {
                benefitPromotion.text = it
            } ?: kotlin.run {
                benefitPromotion.visibility = View.GONE
            }
        }
    }

    private fun setBackgroundAndColorText(style: Int? = null) {
        when (style) {
            BACKGROUND_WHITE -> {
                binding.clBenefits.setBackgroundColor(resources.getColor(R.color.white))
                binding.benefitTitle.setTextColor(resources.getColor(R.color.black))
                binding.benefitDescription.setTextColor(resources.getColor(R.color.black))
            }
            BACKGROUND_DARK -> {
                binding.clBenefits.setBackgroundColor(resources.getColor(R.color.black))
                binding.benefitTitle.setTextColor(resources.getColor(R.color.white))
                binding.benefitDescription.setTextColor(resources.getColor(R.color.white))
            }
            else -> {
                binding.clBenefits.setBackgroundColor(resources.getColor(R.color.white))
                binding.benefitTitle.setTextColor(resources.getColor(R.color.black))
                binding.benefitTitle.setTextColor(resources.getColor(R.color.black))
            }
        }
    }
}