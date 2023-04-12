package com.asdeporte.asdeportev2.ui.mitribu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.ScoreBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ScoreBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: ScoreBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScoreBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }
}