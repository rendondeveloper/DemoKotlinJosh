package com.asdeporte.asdeportev2.ui.mitribu.adapters

import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.PostHomeViewBinding
import com.asdeporte.asdeportev2.ui.mitribu.ImageDialogView
import com.asdeporte.asdeportev2.ui.reusableview.tribu.ModalBottomSheetShare
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem


class PostsAdapterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : FrameLayout(context, attrs, defStyle) {
    private var binding: PostHomeViewBinding
    private lateinit var bottomSheetShare: ModalBottomSheetShare

    interface PostsAdapterListener {
        fun onItem(event: EventData)
        fun onMenu(event: EventData)
    }

    private var isLiked = false

    init {
        binding = PostHomeViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(
        item: EventData,
        listener: PostsAdapterListener,
        fragmentManager: FragmentManager,
    ) {

        bottomSheetShare = ModalBottomSheetShare()

        var requestOptions = RequestOptions()
            //.placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder_img))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(14))
        /*
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
         */

        binding.postImage.setOnClickListener {
            val dialog = ImageDialogView()
            dialog.show((context as AppCompatActivity).supportFragmentManager, "")
        }

        binding.postOptions.setOnClickListener {
            //showMenu(it, R.menu.menu_post_options)
            listener.onMenu(item)
        }

        binding.likeButton.setOnClickListener {
            if (isLiked) {
                isLiked = false
                binding.likeIcon.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.label_secondary
                    ), PorterDuff.Mode.SRC_IN
                )
                binding.likeText.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.label_secondary
                    )
                )
                binding.likeText.text = context.getString(R.string.like)
            } else {
                isLiked = true
                binding.likeIcon.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.orange_as_light
                    ), PorterDuff.Mode.SRC_IN
                )
                binding.likeText.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.orange_as_light
                    )
                )

                binding.likeText.text = context.getString(R.string.you_like)
            }
        }

        binding.shareButton.setOnClickListener {
            bottomSheetShare.show(fragmentManager, "MY_BOTTOM_SHEET")
        }
        item.isVideo?.let {
            if (it) {
                binding.postImage.visibility = GONE
                binding.videoPlayerView.visibility = VISIBLE
                binding.videoPlayerView.player = ExoPlayer.Builder(context).build()
                val mediaItem =
                    MediaItem.fromUri("https://storage.googleapis.com/wvmedia/clear/h264/tears/tears.mpd")
                (binding.videoPlayerView.player as ExoPlayer).addMediaItem(mediaItem)
                (binding.videoPlayerView.player as ExoPlayer).prepare()
                (binding.videoPlayerView.player as ExoPlayer).play()
            } else {
                binding.postImage.visibility = VISIBLE
                binding.videoPlayerView.visibility = GONE
            }
        }

    }

    fun release() {
        binding.videoPlayerView.player?.release()
    }

    fun pause() {
        binding.videoPlayerView.player?.let {
            if(it.isPlaying){
                it.pause()
            }
        }
    }

    fun stop() {
        binding.videoPlayerView.player?.let {
            if(it.isPlaying){
                it.stop()
            }
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
