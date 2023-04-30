package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentTabTribuTribusBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.mitribu.ChangeDefaultTribuDialog
import com.asdeporte.asdeportev2.ui.mitribu.adapters.SmallTribuJoinAdapter
import com.asdeporte.asdeportev2.ui.reusableview.home.EventBottomSheet

class TabTribuTribusFragment(private val flow: String = "") : Fragment(),
    EventBottomSheet.EventBottomSheetListener,
    MiTribuRequestsFragment.MiTribuRequestsListener,
    JoinTribusFilterSheet.JoinTribusFilterSheetListener {

    private var _binding: FragmentTabTribuTribusBinding? = null
    private val binding get() = _binding!!

    private lateinit var tribusAdapter: SmallTribuJoinAdapter

    private var requestsFragment = MiTribuRequestsFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabTribuTribusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validFlow()

        binding.changeTribuButton.setOnClickListener {
            //ChangeDefaultTribuSheet.create(this@TabTribuTribusFragment, EventData("", "", "", "")).show(requireActivity().supportFragmentManager, "TabTribuTribusFragment")
            val alert = ChangeDefaultTribuDialog().apply {
                onConfirmClick = {

                }
            }
            alert.show(requireActivity().supportFragmentManager, this.tag)
        }

        binding.defaultTribuView.setData()
        binding.defaultTribuView.setOnClickListener {
            findNavController().safelyNavigate(R.id.mainTribuAction)
        }
        binding.secondTribuView.setData()

        // Create tribu
        binding.createTribuButton.setOnClickListener {
            findNavController().safelyNavigate(R.id.action_navigation_tribu_to_createTribeFragment)
        }

        requestsFragment.listener = this
        binding.pendingRequests.setOnClickListener {
            findNavController().safelyNavigate(R.id.action_navigation_tribu_to_mitribuRequestFragment)
        }

        binding.filterButton.setOnClickListener {
            JoinTribusFilterSheet.create(this@TabTribuTribusFragment)
                .show(requireActivity().supportFragmentManager, "MiTribuRequestsFragment")
        }

        setupAdapters()
    }

    private fun validFlow() {
        when (flow) {
            "friends" -> {
                binding.txtTitle.text = getString(R.string.tribus).uppercase()
                binding.pendingRequests.visibility = GONE
                binding.txtDescription.visibility = GONE
                binding.rlAddTribu.visibility = GONE
                binding.lnOtherTribus.visibility = GONE
            }
        }
    }

    private fun setupAdapters() {
        val testEvent = EventData(
            "123",
            "7, 14 y 21K by WomanUp",
            "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
            "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80"
        )
        val items = listOf(
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

        // More Tribus
        tribusAdapter = SmallTribuJoinAdapter().apply {
            onItemClick = {
                findNavController().safelyNavigate(R.id.toJoinTribu)
            }
        }

        binding.moreTribus.adapter = tribusAdapter
        binding.moreTribus.setHasFixedSize(true)
        binding.moreTribus.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        tribusAdapter.setItems(items)
    }

    override fun onOpenEvent(event: String) {
        //TODO("Not yet implemented")
    }

    override fun onOpenInscription(event: String) {
        //TODO("Not yet implemented")
    }

    override fun onRequestViewBack() {
        val fm = activity?.supportFragmentManager?.beginTransaction()
        fm?.remove(requestsFragment)
        fm?.commit()
    }

    override fun onApplyFilters() {
        //TODO("Not yet implemented")
    }

    override fun onCleanFilter() {
        //TODO("Not yet implemented")
    }
}