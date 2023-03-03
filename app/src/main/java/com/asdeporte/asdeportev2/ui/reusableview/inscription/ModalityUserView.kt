package com.asdeporte.asdeportev2.ui.reusableview.inscription

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.ModalityUserViewBinding
import com.asdeporte.asdeportev2.ui.home.adapters.EventsHorizontalAdapter
import com.asdeporte.asdeportev2.ui.home.adapters.EventsHorizontalBigAdapter
import com.asdeporte.asdeportev2.ui.inscription.adapters.ModalityItemAdapter
import com.asdeporte.asdeportev2.ui.reusableview.home.EventBottomSheet
import com.asdeporte.asdeportev2.utils.dpToPx
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class ModalityUserView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    private lateinit var binding: ModalityUserViewBinding

    private lateinit var distanceAdapter: ModalityItemAdapter
    private lateinit var tshirtAdapter: ModalityItemAdapter

    init {
        binding = ModalityUserViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData) {

        var requestOptions = RequestOptions()
            .placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder_img))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(dpToPx(4)))
        Glide.with(this)
            .load("https://i.pravatar.cc/100")
            .centerCrop()
            .apply(requestOptions)
            .into(binding.userPicture)

        binding.tshirtView.alpha = .3f

        setupAdapter()
    }

    private fun setupAdapter() {

        val distances = listOf("Elite", "Sprint", "Olímpico", "Duatlón", "Elite")

        distanceAdapter = ModalityItemAdapter().apply {
            onItemClick = {
                println("onItemClick: $it")
                //EventBottomSheet.create(this@ModalityUserView, it).show(context.supportFragmentManager, "EventBottomSheet")
                //binding.distanceDetailsView.visibility = View.VISIBLE

                binding.distanceDetailsView.updateLayoutParams {
                    height = android.view.ViewGroup.LayoutParams.WRAP_CONTENT
                }
                binding.tshirtView.alpha = 1f
            }
        }

        binding.distanceList.adapter = distanceAdapter
        binding.distanceList.setHasFixedSize(true)
        binding.distanceList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        distanceAdapter.setItems(distances)

        // Tshirts
        val tshirts = listOf("XS", "SM", "MD", "LG", "XL")

        tshirtAdapter = ModalityItemAdapter().apply {
            onItemClick = {
                //EventBottomSheet.create(this@ModalityUserView, it).show(context.supportFragmentManager, "EventBottomSheet")
            }
        }

        binding.tshirtList.adapter = tshirtAdapter
        binding.tshirtList.setHasFixedSize(true)
        binding.tshirtList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        tshirtAdapter.setItems(tshirts)
    }
}
