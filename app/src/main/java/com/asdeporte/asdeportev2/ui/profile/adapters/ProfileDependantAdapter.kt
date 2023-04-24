package com.asdeporte.asdeportev2.ui.profile.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.ViewDependantProfileBinding
import com.asdeporte.asdeportev2.databinding.ViewEventHistoryBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.profile.details.BottomSheetDependents
import com.asdeporte.asdeportev2.utils.dpToPx
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class ProfileDependantAdapter : RecyclerViewAdapterBase<EventData, ProfileDependantView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): ProfileDependantView =
        ProfileDependantView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<ProfileDependantView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item)
        }
    }
    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}
class ProfileDependantView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: ViewDependantProfileBinding

    init {
        binding = ViewDependantProfileBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData) {

        Glide.with(this)
            .load("https://i.pravatar.cc/100")
            .centerCrop()
            .into(binding.dependantLogo)

        //binding.title.text = "Astri Acapulco Cabo Marqués"
        //binding.place.text = "30 octubre 2022"
        //binding.date.text = "Estado de México, México"
        binding.btnVerPerfil.setOnClickListener{
            findNavController().safelyNavigate(R.id.action_personal_dependents_to_personalAddDependentsFragment)
        }
        binding.rejectButton.setOnClickListener{
            BottomSheetDependents().show((context as AppCompatActivity).supportFragmentManager, "MY_BOTTOM_SHEET")
        }
    }
}
