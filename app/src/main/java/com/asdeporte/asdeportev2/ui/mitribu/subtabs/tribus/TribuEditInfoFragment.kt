package com.asdeporte.asdeportev2.ui.mitribu.subtabs.tribus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentTribuEditInfoBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.adapters.AddTribuMemberAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class TribuEditInfoFragment : Fragment() {

    private var _binding: FragmentTribuEditInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var addTribuMemberAdapter: AddTribuMemberAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTribuEditInfoBinding.inflate(inflater, container, false)
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

        binding.mediaPickerView.setOnClickListener {
            binding.mediaPickerView.visibility = View.GONE
            binding.mediaContainerView.visibility = View.VISIBLE
        }
        var requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(10))
        Glide.with(this)
            .load("https://images.unsplash.com/photo-1518365658347-c4906efc5636?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1444&q=80")
            .centerCrop()
            .apply(requestOptions)
            .into(binding.mediaContainerView)

        // More Users
        val testEvent = EventData("123",
            "7, 14 y 21K by WomanUp",
            "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
            "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")
        val items = listOf(testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent)

        val users = listOf(testEvent, testEvent, testEvent)
        addTribuMemberAdapter = AddTribuMemberAdapter().apply {
            onItemClick = {
                //EventBottomSheet.create(this@TabTribuTribusFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }
        }

        binding.moreUsers.adapter = addTribuMemberAdapter
        binding.moreUsers.setHasFixedSize(true)
        binding.moreUsers.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        addTribuMemberAdapter.setItems(users)
    }

}