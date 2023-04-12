package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.data.responses.events.ScoreData
import com.asdeporte.asdeportev2.databinding.FragmentResultsBinding
import com.asdeporte.asdeportev2.ui.mitribu.FilterResultsBottomSheetFragment
import com.asdeporte.asdeportev2.ui.mitribu.ScoreBottomSheetFragment
import com.asdeporte.asdeportev2.ui.mitribu.adapters.ScoreAdapter

class ResultsFragment : Fragment() {

    lateinit var binding: FragmentResultsBinding
    private lateinit var scoreBottomSheetFragment: ScoreBottomSheetFragment
    private lateinit var filterResultsBottomSheetFragment: FilterResultsBottomSheetFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scoreBottomSheetFragment = ScoreBottomSheetFragment()
        filterResultsBottomSheetFragment = FilterResultsBottomSheetFragment()

        val scores = arrayListOf(
            ScoreData(1, "Ernesto Torres", "00:30:09"),
            ScoreData(2, "Ernesto Torres", "00:30:09"),
            ScoreData(3, "Ernesto Torres", "00:30:09"),
            ScoreData(4, "Ernesto Torres", "00:30:09"),
            ScoreData(5, "Ernesto Torres", "00:30:09"),
            ScoreData(6, "Ernesto Torres", "00:30:09")
        )

        binding.apply {
            rvScores.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ScoreAdapter(scores) {
                    scoreBottomSheetFragment.show(parentFragmentManager, "MY_BOTTOM_SHEET")
                }
            }
        }

        binding.filterButton.setOnClickListener {
            filterResultsBottomSheetFragment.show(parentFragmentManager, "MY_BOTTOM_SHEET")
        }
    }
}