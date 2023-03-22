package com.asdeporte.asdeportev2.ui.reusableview.tribu

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.annotation.MenuRes
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FriendDefaultViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class FriendDefaultView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    interface FriendDefaultViewListener {
        fun onOptionSelected(friendId: Int, option: FriendMenuOption)
    }
    private lateinit var listener: FriendDefaultViewListener

    private lateinit var binding: FriendDefaultViewBinding

    init {
        binding = FriendDefaultViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(listener: FriendDefaultViewListener) {
        //binding.numberEvents.text = events
        this.listener = listener

        var requestOptions = RequestOptions()
            .placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder_img))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(5))
        Glide.with(this)
            .load("https://i.pravatar.cc/200")
            .fitCenter()
            .apply(requestOptions)
            .into(binding.personImage)

        binding.menuButton.setOnClickListener {
            showMenu(it, R.menu.default_frient_menu)
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
            if (it.title == "Enviar mensaje") {
                listener.onOptionSelected(0, FriendMenuOption.MESSAGE)
            } else if (it.title == "Eliminar") {
                listener.onOptionSelected(0, FriendMenuOption.SHARE)
            } else if (it.title == "Bloquear") {
                listener.onOptionSelected(0, FriendMenuOption.BLOCK)
            }
            return@setOnMenuItemClickListener true
        }
        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }
        // Show the popup menu.
        popup.show()
    }

}

enum class FriendMenuOption {
    MESSAGE,
    SHARE,
    BLOCK
}