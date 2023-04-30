package com.asdeporte.asdeportev2.ui.mitribu.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asdeporte.asdeportev2.databinding.FragmentNewMedalPreviewBinding
import com.bumptech.glide.Glide

class NewMedalPreviewFragment : Fragment() {

    private lateinit var binding: FragmentNewMedalPreviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewMedalPreviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            cancelButton.setOnClickListener {
                requireActivity().onBackPressed()
            }

            postButton.setOnClickListener {
                requireActivity().onBackPressed()
            }

            Glide.with(requireContext())
                .load("https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2022%2F5%2Fimg_1655144427045_TUP-Marquesa-22-logo-nvo-jun-13.JPG")
                .centerCrop()
                .into(binding.eventImage)
        }
    }
}