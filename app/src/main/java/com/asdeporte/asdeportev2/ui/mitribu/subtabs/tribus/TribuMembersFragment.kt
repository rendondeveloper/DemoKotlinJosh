package com.asdeporte.asdeportev2.ui.mitribu.subtabs.tribus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentTribuMembersBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.adapters.TribuResultAdapter
import com.asdeporte.asdeportev2.ui.reusableview.tribu.FriendDefaultView
import com.asdeporte.asdeportev2.ui.reusableview.tribu.FriendMenuOption

class TribuMembersFragment : Fragment(), FriendDefaultView.FriendDefaultViewListener {

    private var _binding: FragmentTribuMembersBinding? = null
    private val binding get() = _binding!!

    private lateinit var resultsdapter: TribuResultAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTribuMembersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
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

        resultsdapter = TribuResultAdapter().apply {
            onItemClick = {
                //EventBottomSheet.create(this@TabTribuTribusFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }
        }

        binding.moreUsers.adapter = resultsdapter
        binding.moreUsers.setHasFixedSize(true)
        binding.moreUsers.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        resultsdapter.setItems(items)


        binding.friendPlaceholder1.setData(this, requireActivity().supportFragmentManager)
        binding.friendPlaceholder2.setData(this, requireActivity().supportFragmentManager)
    }

    /*
     Listeners
     */
    override fun onOptionSelected(friendId: Int, option: FriendMenuOption) {
        //TODO("Not yet implemented")
    }

}