package com.asdeporte.asdeportev2.ui.notifications.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.*
import com.asdeporte.asdeportev2.ui.alert.AlertDialog
import com.asdeporte.asdeportev2.ui.notifications.NotificationChatGroupBottomSheet
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class UpdateUserAdapter : RecyclerViewAdapterBase<EventData, UpdateUserView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): UpdateUserView =
        UpdateUserView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<UpdateUserView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item)
        }

        holder.view.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}

class UpdateUserView constructor(context: Context): RelativeLayout(context) {

    private lateinit var binding: UpdateUserViewBinding

    init {
        binding = UpdateUserViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData) {

        Glide.with(this)
            .load("https://i.pravatar.cc/200")
            //.centerCrop()
            .apply(
                RequestOptions()
                    //.placeholder(R.drawable.placeholder)
                    //.error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true))
            .into(binding.avatarImage)


    }
}

class UpdateUserRequestView constructor(context: Context): RelativeLayout(context) {

    private lateinit var binding: UpdateUserRequestViewBinding

    init {
        binding = UpdateUserRequestViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData) {

        Glide.with(this)
            .load("https://i.pravatar.cc/200")
            //.centerCrop()
            .apply(
                RequestOptions()
                    //.placeholder(R.drawable.placeholder)
                    //.error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true))
            .into(binding.avatarImage)

        binding.confirmButton.setOnClickListener{
            binding.lnButtons.visibility = View.GONE
            binding.txtAddMember.visibility = View.VISIBLE
        }

    }
}
class MessageCellView constructor(context: Context): RelativeLayout(context) {

    private lateinit var binding: MessageCellViewBinding

    init {
        binding = MessageCellViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData) {

        Glide.with(this)
            .load("https://i.pravatar.cc/200")
            //.centerCrop()
            .apply(
                RequestOptions()
                    //.placeholder(R.drawable.placeholder)
                    //.error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true))
            .into(binding.avatarImage)


    }
}
class MessageGroupCellView constructor(context: Context, val isSuggested: Boolean = true): RelativeLayout(context) {

    private lateinit var binding: MessageGroupCellViewBinding

    init {
        binding = MessageGroupCellViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData) {

        Glide.with(this)
            .load("https://picsum.photos/200")
            //.centerCrop()
            .apply(
                RequestOptions()
                    //.placeholder(R.drawable.placeholder)
                    //.error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true))
            .into(binding.avatarImage)

        binding.btnUnirme2.isVisible =  isSuggested
        binding.imgMore.isVisible =  !isSuggested
        binding.imgMore.setOnClickListener {
            NotificationChatGroupBottomSheet().show((context as AppCompatActivity).supportFragmentManager, "MY_BOTTOM_SHEET")
        }
        binding.btnUnirme2.setOnClickListener{
            val alert = AlertDialog(
                title = "Unirme a grupo",
                message = "Se te notificará cuando el administrador del grupo acepte tu solicitud").apply {
                onCancelClick = {
                    findNavController().popBackStack()
                }
            }
            alert.show((context as AppCompatActivity).supportFragmentManager, "")
        }

    }
}

class NotificationsTopStatusView constructor(context: Context): RelativeLayout(context) {

    private lateinit var binding: NotificationsTopStatusViewBinding

    init {
        binding = NotificationsTopStatusViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData, passDay: Boolean = false) {
        if(passDay){
            binding.txtDay.text = "Esta semana"
            binding.txtNotification.visibility = View.GONE
        }

    }
}