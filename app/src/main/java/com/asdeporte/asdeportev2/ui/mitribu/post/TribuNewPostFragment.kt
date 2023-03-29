package com.asdeporte.asdeportev2.ui.mitribu.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentTribuNewPostBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.adapters.EventTribuHorizontalAdapter
import com.asdeporte.asdeportev2.ui.mitribu.adapters.PostsAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TribuNewPostFragment : Fragment(), NewPostPreviewSheet.NewPostPreviewSheetListener, NewPostAudienceSheet.NewPostAudienceSheetListener {

    private var _binding: FragmentTribuNewPostBinding? = null
    private val binding get() = _binding!!

    private lateinit var postsAdapter: PostsAdapter
    private lateinit var eventsAdapter: EventTribuHorizontalAdapter

    val testEvent = EventData("123",
        "7, 14 y 21K by WomanUp",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentTribuNewPostBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as MainActivity).hideActionBar()

        binding.backImage.setOnClickListener {
            exitDialog()
        }

        binding.publishButton.setOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }

        binding.audienceButton.setOnClickListener {
            NewPostAudienceSheet.create(this).show(requireActivity().supportFragmentManager, "TribuNewPostFragment")
        }

        binding.addActivity.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("fromNewPost", true)
            bundle.putSerializable("type", NewPostType.ACTIVITY)
            findNavController().safelyNavigate(R.id.toNewActivityPost, bundle)
        }
        binding.addMedal.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("fromNewPost", true)
            bundle.putSerializable("type", NewPostType.MEDAL)
            findNavController().safelyNavigate(R.id.toNewActivityPost, bundle)
        }
        binding.addMedia.setOnClickListener {
            NewPostPreviewSheet.create(this@TribuNewPostFragment, NewPostType.MEDIA).show(requireActivity().supportFragmentManager, "EventBottomSheet")
        }

        initPosts()
        return root
    }

    fun initPosts() {
    }
    /*
     Listeners
     */
    override fun onAudienceSelected(audience: NewPostAudience?) {
        when (audience) {
            NewPostAudience.PUBLIC -> {
                binding.audienceImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_world))
                binding.audienceTitle.text = "Publico"
            }
            NewPostAudience.FRIENDS -> {
                binding.audienceImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_friends_audience))
                binding.audienceTitle.text = getString(R.string.txt_friends)
            }
            NewPostAudience.PRIVATE -> {
                binding.audienceImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_user_audience))
                binding.audienceTitle.text = "Solo yo"
            }
            else -> {}
        }
    }
    override fun onMediaPost(item: String?) {
        (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
    }

    fun exitDialog() {
        MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle("¿Salir sin publicar?")
            .setMessage("Tu publicación no se guardará")
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                // Respond to neutral button press
            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                // Respond to positive button press
                (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}