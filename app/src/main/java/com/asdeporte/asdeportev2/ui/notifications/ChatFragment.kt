package com.asdeporte.asdeportev2.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.models.MessageModel
import com.asdeporte.asdeportev2.databinding.FragmentChatBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.notifications.adapter.*
import com.asdeporte.asdeportev2.ui.reusableview.home.EventBottomSheet
import com.asdeporte.asdeportev2.ui.reusableview.home.SearchTribuView

class ChatFragment : Fragment(), EventBottomSheet.EventBottomSheetListener, SearchTribuView.SearchTribuViewListener {

    private var binding: FragmentChatBinding? = null
    private lateinit var chatAdapter: ChatAdapter
    private val messages = MessageModel.getMessages()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()

        //binding.toolbar.title = "Notificaciones"
        //binding.toolbar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.navigation_back)
        binding?.chatBackImage?.setOnClickListener {
            //findNavController().popBackStack(R.id.navigation_home, true)
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }
        binding?.toolbar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.filter_menu -> {
                    NotificationChatBottomSheet().show(requireActivity().supportFragmentManager, "MY_BOTTOM_SHEET")
                    true
                }
                else -> false
            }
        }

        binding?.buttonSend?.setOnClickListener {
            messages.add(MessageModel.getNew())
            chatAdapter.notifyItemInserted(messages.count())
            binding?.recyclerGchat?.scrollToPosition(messages.count()-1)
        }
        setupAdapters()
    }

    private fun setupAdapters() {
        chatAdapter = ChatAdapter(messages).apply {
            /*onItemClick = {
                EventBottomSheet.create(this@TribuMainFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }*/
        }
        binding?.recyclerGchat?.adapter = chatAdapter
        binding?.recyclerGchat?.setHasFixedSize(true)
        binding?.recyclerGchat?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.recyclerGchat?.scrollToPosition(messages.count()-1)
    }

    override fun onOpenEvent(event: String) {
        //TODO("Not yet implemented")
    }

    override fun onOpenInscription(event: String) {
        //TODO("Not yet implemented")
    }

    override fun onSearch() {
        //TODO("Not yet implemented")
    }

    override fun onFilters() {
        //TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}