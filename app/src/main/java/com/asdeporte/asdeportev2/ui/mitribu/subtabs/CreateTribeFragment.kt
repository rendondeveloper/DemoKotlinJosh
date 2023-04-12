package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentCreateTribeBinding
import com.asdeporte.asdeportev2.databinding.FragmentTabTribuTribusBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.adapters.AddTribuMemberAdapter
import com.asdeporte.asdeportev2.ui.mitribu.adapters.SmallTribuJoinAdapter
import java.util.*

class CreateTribeFragment : Fragment(), TextToSpeech.OnInitListener {
    lateinit var binding:FragmentCreateTribeBinding

    private lateinit var tts: TextToSpeech
    private lateinit var addTribuMemberAdapter: AddTribuMemberAdapter
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                binding.mediaContainerView.visibility = View.VISIBLE
                binding.mediaContainerView.setImageURI(uri)
                binding.mediaPickerView.visibility = View.GONE
            } else {
                print("")
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateTribeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarCreate.setOnClickListener {
            findNavController().popBackStack()
            (activity as MainActivity).showActionBar()
        }

        binding.createTribu.setOnClickListener {
            tts.speak("Acabas de pasar el kilometro 1", TextToSpeech.QUEUE_FLUSH, null, "")
        }

        binding.mediaPickerView.setOnClickListener {
            openMedia()
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
        val users = listOf(testEvent, testEvent, testEvent)
        addTribuMemberAdapter = AddTribuMemberAdapter(requireContext()).apply {
            onItemClick = {
                //EventBottomSheet.create(this@TabTribuTribusFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }
        }

        binding.moreTribusCreateView.adapter = addTribuMemberAdapter
        binding.moreTribusCreateView.setHasFixedSize(true)
        binding.moreTribusCreateView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        addTribuMemberAdapter.setItems(users)
    }

    fun openMedia() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    override fun onResume() {
        super.onResume()
        tts = TextToSpeech(requireContext(), this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tts.stop()
        tts.shutdown()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.getDefault()
            tts.setPitch(1f)
        }
    }
}