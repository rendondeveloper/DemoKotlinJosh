package com.asdeporte.asdeportev2.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.models.Message
import com.asdeporte.asdeportev2.data.models.MessageModel
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentChatBinding
import com.asdeporte.asdeportev2.databinding.FragmentNotificationsBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.adapters.PostsAdapter
import com.asdeporte.asdeportev2.ui.notifications.adapter.*
import com.asdeporte.asdeportev2.ui.reusableview.home.EventBottomSheet
import com.asdeporte.asdeportev2.ui.reusableview.home.SearchTribuView
import com.google.android.material.tabs.TabLayout

class ChatFragment : Fragment(), EventBottomSheet.EventBottomSheetListener, SearchTribuView.SearchTribuViewListener {

    private var _binding: FragmentChatBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var chatAdapter: ChatAdapter

    val messages = MessageModel.getMessages()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as MainActivity).hideActionBar()

        //binding.toolbar.title = "Notificaciones"
        //binding.toolbar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.navigation_back)
        binding.chatBackImage.setOnClickListener {
            //findNavController().popBackStack(R.id.navigation_home, true)
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }
        /*binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.filter_menu -> {
                    // Handle favorite icon press
                    filterAlert()
                    true
                }
                else -> false
            }
        }*/

        binding.buttonSend.setOnClickListener {
            messages.add(MessageModel.getNew())
            chatAdapter.notifyItemInserted(messages.count())
            binding.recyclerGchat.scrollToPosition(messages.count()-1)
        }

        setupAdapters()

        return root
    }

    private fun setupAdapters() {
        chatAdapter = ChatAdapter(messages).apply {
            /*onItemClick = {
                EventBottomSheet.create(this@TribuMainFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }*/
        }

        binding.recyclerGchat.adapter = chatAdapter
        binding.recyclerGchat.setHasFixedSize(true)
        binding.recyclerGchat.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.recyclerGchat.scrollToPosition(messages.count()-1)
    }


    /*
     Listeners
     */

    override fun onOpenEvent(event: String) {
        // open video
    }

    override fun onOpenInscription(event: String) {
        //TODO("Not yet implemented")
    }

    override fun onSearch() {
        println("onSearch")
    }

    override fun onFilters() {
        println("onFilters")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}