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
import com.asdeporte.asdeportev2.databinding.FragmentRequestsSentBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.mitribu.adapters.TribuSentAdapter

class RequestsSentFragment : Fragment() {

    lateinit var binding: FragmentRequestsSentBinding
    private lateinit var tribuSentAdapter: TribuSentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRequestsSentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val testEvent = EventData(
            "123",
            "Aarón Méndez quiere unirse \n" +
                    "a Corredores Narvarte",
            "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
            "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80"
        )

        val requests = listOf(testEvent, testEvent, testEvent)

        tribuSentAdapter = TribuSentAdapter(requests as MutableList<EventData>) {
            findNavController().safelyNavigate(R.id.action_requestsSentFragment_to_navigation_tribu_profile)
        }

        binding.rvRequests.adapter = tribuSentAdapter
        binding.rvRequests.setHasFixedSize(true)
        binding.rvRequests.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}