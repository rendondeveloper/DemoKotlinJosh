package com.asdeporte.asdeportev2.ui.mitribu.subtabs.tribus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentCompairFriendsBinding
import com.asdeporte.asdeportev2.ui.mitribu.adapters.CompairFriendAdapter

class CompairFriendsFragment : Fragment() {

    private lateinit var binding: FragmentCompairFriendsBinding
    private lateinit var compairFriendAdapter: CompairFriendAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompairFriendsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setOnClickListener {
            findNavController().popBackStack()
        }

        compairFriendAdapter = CompairFriendAdapter()

        val testEvent = EventData(
            "123",
            "7, 14 y 21K by WomanUp",
            "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
            "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80"
        )
        val items = listOf(
            testEvent,
            testEvent,
            testEvent
        )

        binding.rvCompareFriends.adapter = compairFriendAdapter
        binding.rvCompareFriends.setHasFixedSize(true)
        binding.rvCompareFriends.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        compairFriendAdapter.setItems(items)
    }
}