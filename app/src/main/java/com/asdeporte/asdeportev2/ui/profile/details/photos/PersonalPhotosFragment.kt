package com.asdeporte.asdeportev2.ui.profile.details.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentPersonalPhotosBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.google.android.material.tabs.TabLayout

class PersonalPhotosFragment : Fragment() {

    private var binding: FragmentPersonalPhotosBinding? = null
    private lateinit var adapter: TimeLineAdapter
    private var historyPhotosAdapter = HistoryPhotosAdapter()
    private val testEvent = EventData("123",
        "Spartan San Luis Potosí",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")
    private val timeListPhotos = listOf(
        TimeLineModel("Abr 2022",true),
        TimeLineModel("Mar 2022",false),
        TimeLineModel("Dic 2021",false))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalPhotosBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding?.toolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        setupData()
    }

    private fun setupData() {
        initRecyclerView()
        binding?.apply {
            binding?.tabView?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    when (tab.position) {
                        0 -> {
                            rvTimeLine.visibility = VISIBLE
                        }
                        1 -> {
                            rvTimeLine.visibility = GONE
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })

        }
    }

    private fun initRecyclerView() {
        val layoutHorizontalManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding?.rvTimeLine?.layoutManager = layoutHorizontalManager
        adapter = TimeLineAdapter(timeListPhotos)
        binding?.rvTimeLine?.setHasFixedSize(true)
        binding?.rvTimeLine?.adapter = adapter

        val layoutVerticalManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding?.rvHistory?.layoutManager = layoutVerticalManager
        binding?.rvHistory?.setHasFixedSize(true)
        binding?.rvHistory?.adapter = historyPhotosAdapter
        val items = listOf(testEvent, testEvent)
        historyPhotosAdapter.setItems(items)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}