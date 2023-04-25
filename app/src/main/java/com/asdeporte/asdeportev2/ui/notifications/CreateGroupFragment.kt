package com.asdeporte.asdeportev2.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentCreateGroupBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.notifications.adapter.ParticipantsAdapter

class CreateGroupFragment: Fragment() {
    private var binding: FragmentCreateGroupBinding? = null
    private lateinit var participantAdapter: ParticipantsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateGroupBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.toolbarCreate?.setOnClickListener {
            findNavController().popBackStack()
            (activity as MainActivity).showActionBar()
        }
        setupAdapters()
    }

    private fun setupAdapters() {
        val testEvent = EventData(
            "123",
            "7, 14 y 21K by WomanUp",
            "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
            "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80"
        )
        val users = listOf(testEvent, testEvent, testEvent)
        participantAdapter = ParticipantsAdapter(requireContext())
        binding?.listParticipants?.adapter = participantAdapter
        binding?.listParticipants?.setHasFixedSize(true)
        binding?.listParticipants?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        participantAdapter.setItems(users)

        binding?.create?.setOnClickListener{
            findNavController().safelyNavigate(R.id.action_createGroupFragment_to_chatGroupFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}