package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.PostHomeViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class PostsAdapterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    private lateinit var binding: PostHomeViewBinding

    interface PostsAdapterListener {
        fun onItem(event: EventData)
        fun onMenu(event: EventData)
    }
    private lateinit var listener: PostsAdapterListener

    private var isLiked = false
    init {
        binding = PostHomeViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData, listener: PostsAdapterListener) {
        println("PostsAdapterView")

        var requestOptions = RequestOptions()
            //.placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder_img))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(14))

        Glide.with(this)
            .load("https://i.pravatar.cc/100")
            .centerCrop()
            .apply(requestOptions)
            .into(binding.profileImage)

        Glide.with(this)
            .load("https://i.pravatar.cc/100")
            .centerCrop()
            .apply(requestOptions)
            .into(binding.profileSecondaryImage)

        Glide.with(this)
            .load("https://picsum.photos/900/600")
            .centerCrop()
            .apply(requestOptions)
            .into(binding.postImage)

        binding.postOptions.setOnClickListener {
            //showMenu(it, R.menu.menu_post_options)
            listener.onMenu(item)
        }

        binding.likeButton.setOnClickListener {
            println("post liked: $isLiked")
            if (isLiked) {
                isLiked = false
                binding.likeIcon.setColorFilter(ContextCompat.getColor(context, R.color.label_secondary), PorterDuff.Mode.SRC_IN)
                binding.likeText.setTextColor(ContextCompat.getColor(context, R.color.label_secondary))
            } else {
                isLiked = true
                binding.likeIcon.setColorFilter(ContextCompat.getColor(context, R.color.orange_as_light), PorterDuff.Mode.SRC_IN)
                binding.likeText.setTextColor(ContextCompat.getColor(context, R.color.orange_as_light))
            }
        }

        binding.shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val shareBody = "Checa esto"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            context.startActivity(Intent.createChooser(shareIntent, "Compartir usando..."))
        }

    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(context, v)
        popup.menuInflater.inflate(menuRes, popup.menu)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popup.setForceShowIcon(true)
        }
        popup.setOnMenuItemClickListener {
            // Respond to menu item click.
            return@setOnMenuItemClickListener true
        }
        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }
        // Show the popup menu.
        popup.show()
    }

}
