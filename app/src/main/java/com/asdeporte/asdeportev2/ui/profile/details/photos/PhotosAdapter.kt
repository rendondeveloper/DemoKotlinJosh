package com.asdeporte.asdeportev2.ui.profile.details.photos

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.ItemPhotosBinding
import com.asdeporte.asdeportev2.ui.mitribu.ImageDialogView
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper
import com.bumptech.glide.Glide

class PhotosAdapter : RecyclerViewAdapterBase<EventData, PhotosView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): PhotosView =
        PhotosView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<PhotosView>, position: Int) {
        val item = items[position]
        holder.view.apply {
            bind(item)
        }
        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }
}
class PhotosView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: ItemPhotosBinding

    init {
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        binding = ItemPhotosBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData) {
        Glide.with(context)
            .load(R.drawable.activities_dummy)
            .centerInside()
            .into(binding.photoImage)

        binding.photoImage.setOnClickListener{
            val dialog = ImageDialogView()
            dialog.show((context as AppCompatActivity).supportFragmentManager, "")
        }
    }
}
