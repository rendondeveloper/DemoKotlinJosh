package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asdeporte.asdeportev2.databinding.FragmentCompareBinding
import com.asdeporte.asdeportev2.ui.mitribu.FilterResultsBottomSheetFragment

class CompareFragment(val ListenerCompare: () -> Unit) : Fragment() {

    lateinit var binding: FragmentCompareBinding
    private lateinit var filterResultsBottomSheetFragment: FilterResultsBottomSheetFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompareBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filterResultsBottomSheetFragment = FilterResultsBottomSheetFragment()

        binding.filterButton.setOnClickListener {
            filterResultsBottomSheetFragment.show(parentFragmentManager, "MY_BOTTOM_SHEET")
        }

        binding.compareTextInput.setOnClickListener {
            ListenerCompare()
        }
    }
}