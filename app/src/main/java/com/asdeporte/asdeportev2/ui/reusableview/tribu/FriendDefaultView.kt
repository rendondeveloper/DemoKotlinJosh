package com.asdeporte.asdeportev2.ui.reusableview.tribu

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FriendDefaultViewBinding
import com.asdeporte.asdeportev2.extensions.getVisibleFragment
import com.asdeporte.asdeportev2.ui.mitribu.subtabs.FriendMenuBottomSheet
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

    private var binding: FriendDefaultViewBinding

    init {
        binding = FriendDefaultViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(supportFragmentManager: FragmentManager) {
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
            val friendsFragment =
                supportFragmentManager.getVisibleFragment("TabsFriendsProfileFragment")
            val miTribuFragment = supportFragmentManager.getVisibleFragment("MiTribuFragment")
            if (friendsFragment || miTribuFragment) {
                ModalBottomSheetMembers().show(supportFragmentManager, "MY_BOTTOM_SHEET")
            } else {
                FriendMenuBottomSheet().show(supportFragmentManager, "MY_BOTTOM_SHEET")
            }
        }
    }

}
