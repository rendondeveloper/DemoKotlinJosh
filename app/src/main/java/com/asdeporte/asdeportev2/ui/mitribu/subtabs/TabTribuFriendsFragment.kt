package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentTabTribuFriendsBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.mitribu.adapters.FriendRequestAdapter

class TabTribuFriendsFragment(private val flow: String = "") : Fragment(),
    MiTribuRequestsFragment.MiTribuRequestsListener {
    private var _binding: FragmentTabTribuFriendsBinding? = null
    private val binding get() = _binding!!
    private lateinit var otherFriendsAdapter: FriendRequestAdapter

    private var requestsFragment = MiTribuRequestsFragment()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTabTribuFriendsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validFlow()
        binding.homeTribus.visibility = VISIBLE
        requestsFragment.listener = this
        binding.pendingRequests.setOnClickListener {
            findNavController().safelyNavigate(R.id.toRequests)
        }

        binding.friendPlaceholder1.setData(requireActivity().supportFragmentManager)
        binding.friendPlaceholder1.setOnClickListener {
            findNavController().safelyNavigate(R.id.toUserProfile)
        }

        binding.friendPlaceholder2.setData(requireActivity().supportFragmentManager)
        binding.friendPlaceholder2.setOnClickListener {
            findNavController().safelyNavigate(R.id.toUserProfile)
        }

        setupAdapters()
    }

    private fun validFlow(){
        if(flow == "friends"){
            binding.lnTitle.visibility = GONE
            binding.txtTitle2.visibility = GONE
            binding.moreFriends.visibility = GONE
        }
    }

    private fun setupAdapters() {
        val testEvent = EventData("123",
            "7, 14 y 21K by WomanUp",
            "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
            "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")
        val items = listOf(testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent)

        // More Friends
        otherFriendsAdapter = FriendRequestAdapter().apply {
            onItemClick = {
                findNavController().safelyNavigate(R.id.toUserProfile)
            }
        }

        binding.moreFriends.adapter = otherFriendsAdapter
        binding.moreFriends.setHasFixedSize(true)
        binding.moreFriends.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        otherFriendsAdapter.setItems(items)

    }

    override fun onRequestViewBack() {
        binding.homeTribus.visibility = VISIBLE
        binding.subFrames.visibility = GONE
        val fm = activity?.supportFragmentManager?.beginTransaction()
        fm?.remove(requestsFragment)
        fm?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}