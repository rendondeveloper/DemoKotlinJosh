package com.asdeporte.asdeportev2.ui.profile.details.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentGalleryPhotosBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import java.util.UUID

class GalleryPhotosFragment : Fragment() {

    private var binding: FragmentGalleryPhotosBinding? = null
    private lateinit var adapter: TimeLineAdapter
    private var galleryAdapter = GalleryAdapter()
    private val testEvent = EventData(
        UUID.randomUUID().toString(),
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
        binding = FragmentGalleryPhotosBinding.inflate(inflater, container, false)
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
    }

    private fun initRecyclerView() {
        val layoutHorizontalManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding?.rvTimeLine?.layoutManager = layoutHorizontalManager
        adapter = TimeLineAdapter(timeListPhotos)
        binding?.rvTimeLine?.setHasFixedSize(true)
        binding?.rvTimeLine?.adapter = adapter

        val layoutVerticalManager = GridLayoutManager(context, 2)
        binding?.rvGallery?.layoutManager = layoutVerticalManager
        binding?.rvGallery?.setHasFixedSize(true)
        binding?.rvGallery?.adapter = galleryAdapter
        val items = listOf(testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent)
        galleryAdapter.setItems(items)

        binding?.radioSelectAll?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding?.viewBottom?.lnHeader?.visibility = VISIBLE
            } else {
                binding?.viewBottom?.lnHeader?.visibility = GONE
            }
        }
        binding?.viewBottom?.imgUpDown?.setOnClickListener{
            binding?.viewBottom?.lnResume?.let { view ->
                if(view.isVisible){
                    binding?.viewBottom?.imgUpDown?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.arrow_up))
                    binding?.viewBottom?.txtMount?.visibility = VISIBLE
                    binding?.viewBottom?.btnBuy?.visibility = VISIBLE
                    view.visibility = GONE
                }else{
                    binding?.viewBottom?.imgUpDown?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.arrow_down_line))
                    binding?.viewBottom?.txtMount?.visibility = GONE
                    binding?.viewBottom?.btnBuy?.visibility = INVISIBLE
                    view.visibility = VISIBLE
                }
            }
        }
        binding?.buttonFilter2?.setOnClickListener{
            FilterGalleryBottomSheetFragment().show(parentFragmentManager, "MY_BOTTOM_SHEET")
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}