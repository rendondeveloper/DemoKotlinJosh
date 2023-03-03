package com.asdeporte.asdeportev2.ui.mitribu.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.SheetNewPostAudienceBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

enum class NewPostAudience {
    PUBLIC,
    FRIENDS,
    PRIVATE
}

class NewPostAudienceSheet : BottomSheetDialogFragment() {

    private var _binding: SheetNewPostAudienceBinding? = null
    private val binding get() = _binding!!

    interface NewPostAudienceSheetListener {
        fun onAudienceSelected(audience: NewPostAudience?)
    }

    lateinit var listener: NewPostAudienceSheetListener

    companion object {
        fun create(listener: NewPostAudienceSheetListener) = NewPostAudienceSheet().apply {
            this.listener = listener
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = SheetNewPostAudienceBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as MainActivity).hideActionBar()

        binding.publicView.setOnClickListener {
            binding.radioPublic.isChecked = true
            binding.radioFriends.isChecked = false
            binding.radioPrivate.isChecked = false
            this.listener.onAudienceSelected(NewPostAudience.PUBLIC)
            this.dismiss()
        }
        binding.friendsView.setOnClickListener {
            binding.radioPublic.isChecked = false
            binding.radioFriends.isChecked = true
            binding.radioPrivate.isChecked = false
            this.listener.onAudienceSelected(NewPostAudience.FRIENDS)
            this.dismiss()
        }
        binding.privateView.setOnClickListener {
            binding.radioPublic.isChecked = false
            binding.radioFriends.isChecked = false
            binding.radioPrivate.isChecked = true
            this.listener.onAudienceSelected(NewPostAudience.PRIVATE)
            this.dismiss()
        }

        return root
    }

    /*
     Listeners
     */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}