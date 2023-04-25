package com.asdeporte.asdeportev2.ui.notifications.adapter

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.data.models.HasImage
import com.asdeporte.asdeportev2.data.models.Message
import com.asdeporte.asdeportev2.databinding.ItemChatMeBinding
import com.asdeporte.asdeportev2.databinding.ItemChatOtherBinding
import com.asdeporte.asdeportev2.ui.mitribu.TribuTabs
import com.asdeporte.asdeportev2.ui.notifications.adapter.Const.HASIMAGE
import com.asdeporte.asdeportev2.ui.notifications.adapter.Const.NOIMAGE
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


/*class MessageListAdapter(context: Context, messageList: List<BaseMessage>) :
    RecyclerView.Adapter<Any?>() {
    private val mContext: Context
    private val mMessageList: List<BaseMessage>

    init {
        mContext = context
        mMessageList = messageList
    }
}*/

class ChatAdapter(private var messages: ArrayList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = messages.size

    // ViewHolder for landmarks with image
    inner class ReceivedMessageHolder(private val context: Context, private val itemChatOther: ItemChatOtherBinding) :
        RecyclerView.ViewHolder(itemChatOther.root) {
        fun bind(item: Message) {
            itemChatOther.dateOther.visibility = if(item.createdAt) VISIBLE else GONE
            itemChatOther.dateOther.text = "6 de Noviembre"
            itemChatOther.messageOther.text = item.message
            itemChatOther.timestampOther.text = "Hace 30 min"//item.timestamp
            itemChatOther.dateOtherGroup.visibility = GONE
            Glide.with(context)
                .load(item.sender?.profileUrl)
                .centerCrop()
                .apply(
                    RequestOptions()
                        //.placeholder(R.drawable.placeholder)
                        //.error(R.drawable.error)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(itemChatOther.imageOther)
        }
    }

    // ViewHolder for landmark without image
    inner class SentMessageHolder(private val context: Context, private val itemChatMe: ItemChatMeBinding) :
        RecyclerView.ViewHolder(itemChatMe.root) {
        fun bind(item: Message) {
            itemChatMe.messageMe.text = item.message
            itemChatMe.timestampMe.text = "Hace 2 horas"//item.timestamp
            itemChatMe.dateMe.visibility = if(item.createdAt) VISIBLE else GONE

            Glide.with(context)
                .load(item.sender?.profileUrl)
                .centerCrop()
                .apply(
                    RequestOptions()
                        //.placeholder(R.drawable.placeholder)
                        //.error(R.drawable.error)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(itemChatMe.imageMe)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].hasImage == HasImage.TRUE) HASIMAGE else NOIMAGE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HASIMAGE) {
            val view =
                ItemChatOtherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ReceivedMessageHolder(parent.context, view)
        } else {
            val view =
                ItemChatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            SentMessageHolder(parent.context, view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == HASIMAGE){
            (holder as ReceivedMessageHolder).bind(messages[position])
        } else{
            (holder as SentMessageHolder).bind(messages[position])
        }
    }
}

class ChatGroupAdapter(private var messages: ArrayList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = messages.size

    // ViewHolder for landmarks with image
    inner class ReceivedMessageHolder(private val context: Context, private val itemChatOther: ItemChatOtherBinding) :
        RecyclerView.ViewHolder(itemChatOther.root) {
        fun bind(item: Message) {
            itemChatOther.dateOther.text = "6 de Noviembre"
            itemChatOther.messageOther.text = item.message
            itemChatOther.timestampOther.text = "Hace 30 min"//item.timestamp
            itemChatOther.dateOtherGroup.visibility = if(item.createdAt) VISIBLE else GONE
            itemChatOther.dateOther.visibility = GONE
            Glide.with(context)
                .load(item.sender?.profileUrl)
                .centerCrop()
                .apply(
                    RequestOptions()
                        //.placeholder(R.drawable.placeholder)
                        //.error(R.drawable.error)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(itemChatOther.imageOther)
        }
    }

    // ViewHolder for landmark without image
    inner class SentMessageHolder(private val context: Context, private val itemChatMe: ItemChatMeBinding) :
        RecyclerView.ViewHolder(itemChatMe.root) {
        fun bind(item: Message) {
            itemChatMe.messageMe.text = item.message
            itemChatMe.timestampMe.text = "Hace 2 horas"//item.timestamp
            itemChatMe.dateMe.visibility = GONE
            itemChatMe.dateMe.visibility = if(item.createdAt) VISIBLE else GONE

            Glide.with(context)
                .load(item.sender?.profileUrl)
                .centerCrop()
                .apply(
                    RequestOptions()
                        //.placeholder(R.drawable.placeholder)
                        //.error(R.drawable.error)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(itemChatMe.imageMe)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].hasImage == HasImage.TRUE) HASIMAGE else NOIMAGE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HASIMAGE) {
            val view =
                ItemChatOtherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ReceivedMessageHolder(parent.context, view)
        } else {
            val view =
                ItemChatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            SentMessageHolder(parent.context, view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == HASIMAGE){
            (holder as ReceivedMessageHolder).bind(messages[position])
        } else{
            (holder as SentMessageHolder).bind(messages[position])
        }
    }
}

private object Const{
    const val HASIMAGE = 0
    const val NOIMAGE = 1
}
