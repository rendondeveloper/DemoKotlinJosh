package com.asdeporte.asdeportev2.ui.reusableview.tribu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.PostMenuOptionsSheetBinding
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PostMenuOptionsSheet : BottomSheetDialogFragment() {

    interface PostMenuOptionsSheetListener {
        fun onOptionSelected(option: String?)
    }

    private lateinit var binding: PostMenuOptionsSheetBinding

    lateinit var listener: PostMenuOptionsSheetListener
    lateinit var event: EventData

    companion object {
        fun create(listener: PostMenuOptionsSheetListener, event: EventData) = PostMenuOptionsSheet().apply {
            this.listener = listener
            this.event = event
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PostMenuOptionsSheetBinding.inflate(inflater)
        println("PostMenuOptionsSheet event: $event")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("PostMenuOptionsSheet event: $event")



    }

}