package com.asdeporte.asdeportev2.ui.mitribu.subtabs.tribus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentTribuEventsBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.adapters.TribuEventsAdapter

class TribuEventsFragment : Fragment() {

    private var _binding: FragmentTribuEventsBinding? = null
    private val binding get() = _binding!!

    private lateinit var eventsAdapter: TribuEventsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTribuEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }

        binding.filterPosts.setOnClickListener {
            findNavController().safelyNavigate(R.id.action_navigation_tribu_events_to_eventFiltersFragment)
        }

        setupData()

    }

    private fun setupData() {

        // More Users
        val testEvent = EventData("123",
            "7, 14 y 21K by WomanUp",
            "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
            "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")
        val items = listOf(testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent)

        eventsAdapter = TribuEventsAdapter().apply {
            onItemClick = {
                findNavController().safelyNavigate(R.id.action_navigation_tribu_events_to_seeEventDetailFragment)
            }
        }

        binding.moreUsers.adapter = eventsAdapter
        binding.moreUsers.setHasFixedSize(true)
        binding.moreUsers.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        eventsAdapter.setItems(items)

    }

    /*
     Listeners
     */

}