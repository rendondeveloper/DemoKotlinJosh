package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentTabTribuTribusBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.ChangeDefaultTribuDialog
import com.asdeporte.asdeportev2.ui.mitribu.adapters.AddTribuMemberAdapter
import com.asdeporte.asdeportev2.ui.mitribu.adapters.SmallTribuJoinAdapter
import com.asdeporte.asdeportev2.ui.reusableview.home.EventBottomSheet
import java.util.*

class TabTribuTribusFragment : Fragment(), EventBottomSheet.EventBottomSheetListener,
    TextToSpeech.OnInitListener,
    MiTribuRequestsFragment.MiTribuRequestsListener,
    JoinTribusFilterSheet.JoinTribusFilterSheetListener {

    private var _binding: FragmentTabTribuTribusBinding? = null
    private val binding get() = _binding!!

    private lateinit var tribusAdapter: SmallTribuJoinAdapter
    private lateinit var addTribuMemberAdapter: AddTribuMemberAdapter

    private var requestsFragment = MiTribuRequestsFragment()

    private lateinit var tts: TextToSpeech

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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabTribuTribusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeTribus.visibility = View.VISIBLE
        binding.createTribuView.visibility = View.GONE
        binding.createTribuButtonsView.visibility = View.GONE

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

            (activity as MainActivity).hideActionBar()

            binding.homeTribus.visibility = View.GONE
            binding.createTribuView.visibility = View.VISIBLE
            binding.createTribuButtonsView.visibility = View.VISIBLE
        }
        binding.toolbarCreate.setOnClickListener {

            (activity as MainActivity).showActionBar()

            binding.homeTribus.visibility = View.VISIBLE
            binding.createTribuView.visibility = View.GONE
            binding.createTribuButtonsView.visibility = View.GONE
        }
        binding.cancelCreateButton.setOnClickListener {

            (activity as MainActivity).showActionBar()

            binding.homeTribus.visibility = View.VISIBLE
            binding.createTribuView.visibility = View.GONE
            binding.createTribuButtonsView.visibility = View.GONE
        }

        binding.createTribu.setOnClickListener {
            tts.speak("Acabas de pasar el kilometro 1", TextToSpeech.QUEUE_FLUSH, null, "")
        }

        binding.mediaPickerView.setOnClickListener {
            openMedia()
        }

        requestsFragment.listener = this
        binding.pendingRequests.setOnClickListener {
            findNavController().safelyNavigate(R.id.toRequests)
        }

        binding.filterButton.setOnClickListener {
            JoinTribusFilterSheet.create(this@TabTribuTribusFragment)
                .show(requireActivity().supportFragmentManager, "MiTribuRequestsFragment")
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
        tribusAdapter = SmallTribuJoinAdapter().apply {
            onItemClick = {
                findNavController().safelyNavigate(R.id.toJoinTribu)
                //EventBottomSheet.create(this@TabTribuTribusFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }
        }

        binding.moreTribus.adapter = tribusAdapter
        binding.moreTribus.setHasFixedSize(true)
        binding.moreTribus.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        tribusAdapter.setItems(items)

        // More Tribus
        val users = listOf(testEvent, testEvent, testEvent)
        addTribuMemberAdapter = AddTribuMemberAdapter().apply {
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

    /*
     Listeners
     */
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.getDefault()
            tts.setPitch(1f)
        }
    }

    override fun onOpenEvent(event: String) {
        //TODO("Not yet implemented")
    }

    override fun onOpenInscription(event: String) {
        //TODO("Not yet implemented")
    }

    override fun onRequestViewBack() {
        binding.homeTribus.visibility = View.VISIBLE

        binding.createTribuView.visibility = View.GONE
        binding.createTribuButtonsView.visibility = View.GONE
        binding.subFrames.visibility = View.GONE

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

    override fun onResume() {
        super.onResume()
        tts = TextToSpeech(requireContext(), this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        tts.stop()
        tts.shutdown()
    }

    fun openMedia() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}