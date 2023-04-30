package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asdeporte.asdeportev2.databinding.FragmentTabTribuProfileBinding

class TabTribuProfileFragment : Fragment() {
    private var binding: FragmentTabTribuProfileBinding? = null
    private var isProfilePicture: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabTribuProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.bannerImage?.setOnClickListener {
            isProfilePicture = true
        }

        binding?.profileImage?.setOnClickListener {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}