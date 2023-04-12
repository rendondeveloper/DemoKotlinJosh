package com.asdeporte.asdeportev2.ui.mitribu.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentNewPostMediaBinding
import com.asdeporte.asdeportev2.ui.MainActivity

class NewPostMediaFragment : Fragment() {

    private lateinit var binding: FragmentNewPostMediaBinding

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
    ): View? {
        binding = FragmentNewPostMediaBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding.apply {
            backImage.setOnClickListener {
                (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
            }

            mediaView.setOnClickListener {
                openMedia()
            }

            mediaContainerView.setOnClickListener {
                openMedia()
            }

            searchTextInput.addTextChangedListener {
                if (it?.isEmpty() == true) {
                    cancelButton.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.gray_secondary_text
                        )
                    )

                    postButton.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.gray_secondary_text
                        )
                    )

                    postButton.backgroundTintList = ContextCompat.getColorStateList(
                        requireContext(),
                        R.color.border_gray
                    )

                } else {
                    cancelButton.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.black_dynamic
                        )
                    )

                    postButton.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.white
                        )
                    )
                }

                postButton.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.orange_as_light
                )
            }

            cancelButton.setOnClickListener {
                (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
            }

            postButton.setOnClickListener {
                (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    fun openMedia() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}