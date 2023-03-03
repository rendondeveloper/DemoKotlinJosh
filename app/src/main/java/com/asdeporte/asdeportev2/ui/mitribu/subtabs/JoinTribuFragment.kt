package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asdeporte.asdeportev2.databinding.FragmentJoinTribuBinding
import com.asdeporte.asdeportev2.databinding.FragmentMiTribuRequestsBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.adapters.SmallTribuJoinAdapter
import com.asdeporte.asdeportev2.ui.mitribu.adapters.TribuUserRequestAdapter

class JoinTribuFragment : Fragment() {
    private var _binding: FragmentJoinTribuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentJoinTribuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }

        //binding.filterButton.setOnClickListener {
            //JoinTribusFilterSheet.create(this@JoinTribuFragment).show(requireActivity().supportFragmentManager, "MiTribuRequestsFragment")
        //}

        binding.notNowButton.setOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }

        binding.joinButton.setOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}