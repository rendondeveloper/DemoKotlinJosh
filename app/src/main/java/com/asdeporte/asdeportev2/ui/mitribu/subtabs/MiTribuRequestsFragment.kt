package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentMiTribuRequestsBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.adapters.SmallTribuJoinAdapter
import com.asdeporte.asdeportev2.ui.mitribu.adapters.TribuUserRequestAdapter

class MiTribuRequestsFragment : Fragment(), JoinTribusFilterSheet.JoinTribusFilterSheetListener {
    private var _binding: FragmentMiTribuRequestsBinding? = null
    private val binding get() = _binding!!

    interface MiTribuRequestsListener {
        fun onRequestViewBack()
    }

    lateinit var listener: MiTribuRequestsListener

    private lateinit var requestAdapter: TribuUserRequestAdapter
    private lateinit var tribusAdapter: SmallTribuJoinAdapter

    companion object {
        fun create(listener: MiTribuRequestsListener) = MiTribuRequestsFragment().apply {
            this.listener = listener
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMiTribuRequestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as MainActivity).hideActionBar()
        /*binding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }*/


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarRequests.setOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
            //val fm = activity?.supportFragmentManager?.beginTransaction()
            //fm?.detach(this)
            //fm?.commit()
            //listener.onRequestViewBack()
        }

        binding.filterButton.setOnClickListener {
            JoinTribusFilterSheet.create(this@MiTribuRequestsFragment)
                .show(requireActivity().supportFragmentManager, "MiTribuRequestsFragment")
        }

        setupAdapters()
    }


    fun setupAdapters() {
        val testEvent = EventData(
            "123",
            "7, 14 y 21K by WomanUp",
            "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
            "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80"
        )
        val requests = listOf(testEvent, testEvent, testEvent)

        // Requests send
        requestAdapter = TribuUserRequestAdapter().apply {
            onItemClick = {
                findNavController().safelyNavigate(R.id.toUserProfile)
            }
        }

        binding.requestsList.adapter = requestAdapter
        binding.requestsList.setHasFixedSize(true)
        binding.requestsList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        requestAdapter.setItems(requests)

        /*binding.requestsListView.removeAllViews()
        for (event in requests) {
            val requestView = TribuUserRequestView(requireContext())
            requestView.bind(event, false)
            //valueTV.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
            requestView.layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            binding.requestsListView.addView(requestView)
        }*/

        // More Tribus
        val tribus = listOf(
            testEvent,
            testEvent,
            testEvent,
            testEvent,
            testEvent,
            testEvent,
            testEvent,
            testEvent,
            testEvent
        )
        tribusAdapter = SmallTribuJoinAdapter().apply {
            onItemClick = {
                findNavController().safelyNavigate(R.id.toJoinTribu)
            }
        }

        binding.moreTribus.adapter = tribusAdapter
        binding.moreTribus.setHasFixedSize(true)
        binding.moreTribus.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        tribusAdapter.setItems(tribus)
    }

    /*
    Listeners
     */
    override fun onApplyFilters() {
        TODO("Not yet implemented")
    }

    override fun onCleanFilter() {
        TODO("Not yet implemented")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}