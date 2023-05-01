package com.asdeporte.asdeportev2.ui.profile.details.photos

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.ItemGalleryBinding
import com.asdeporte.asdeportev2.ui.mitribu.ImageDialogView
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper
import com.bumptech.glide.Glide

class GalleryAdapter : RecyclerViewAdapterBase<EventData, GalleryView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null
    private var isEditable = false

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): GalleryView =
        GalleryView(parent.context)

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewWrapper<GalleryView>, position: Int) {
        val item = items[position]
        /*
        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
         */

        val selectionThreshold = 100L
        var selectionStartTime: Long = 0
        holder.view.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Registrar el tiempo de inicio del toque
                    selectionStartTime = System.currentTimeMillis()
                }
                MotionEvent.ACTION_UP -> {
                    val selectionDuration = System.currentTimeMillis() - selectionStartTime
                    // Comprobar si la duración está por debajo del umbral de selección
                    if (selectionDuration > selectionThreshold) {
                        // Acción de selección
                        item.isBuy = true
                        isEditable = true
                        //holder.view.visibility = FrameLayout.VISIBLE
                        holder.view.alpha = 0.5f
                    }else{
                        if(isEditable){
                            if(holder.view.alpha == 0.5f){
                                item.isBuy = false
                                holder.view.alpha = 1f
                            }else{
                                item.isBuy = true
                                holder.view.alpha = 0.5f
                            }
                            /*
                            item.isBuy?.let {
                                if(it){
                                    item.isBuy = false
                                    //holder.view.visibility = FrameLayout.GONE
                                    holder.view.alpha = 0.5f
                                }else{
                                    item.isBuy = false
                                    //holder.view.visibility = FrameLayout.VISIBLE
                                    holder.view.alpha = 1f
                                }
                            }
                             */
                        }else{
                            val dialog = ImageDialogView()
                            dialog.show((holder.view.context as AppCompatActivity).supportFragmentManager, "")
                        }
                    }
                }
            }
            true
        }

        holder.view.apply {
            bind(item)
        }
    }
}
class GalleryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: ItemGalleryBinding

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        binding = ItemGalleryBinding.inflate(LayoutInflater.from(context), this, true)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun bind(item: EventData) {
        Glide.with(context)
            .load(R.drawable.activities_dummy)
            .centerInside()
            .into(binding.photoImage)

    }
}
