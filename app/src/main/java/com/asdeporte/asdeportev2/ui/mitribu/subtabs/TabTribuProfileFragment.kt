package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.asdeporte.asdeportev2.databinding.FragmentTabTribuProfileBinding

class TabTribuProfileFragment : Fragment() {
    private var _binding: FragmentTabTribuProfileBinding? = null
    private val binding get() = _binding!!
    private var isProfilePicture: Boolean = false

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                if (isProfilePicture) {
                    binding.bannerImage.setImageURI(uri)
                    isProfilePicture = false
                } else binding.profileImage.setImageURI(uri)
            } else {
                print("")
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabTribuProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bannerImage.setOnClickListener {
            isProfilePicture = true
            openMedia()
        }

        binding.profileImage.setOnClickListener {
            openMedia()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun openMedia() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}