package com.asdeporte.asdeportev2.ui.profile.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.asdeporte.asdeportev2.databinding.FragmentBadgesBinding
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.profile.adapters.badget.BadgeAdapter
import com.asdeporte.asdeportev2.ui.profile.adapters.badget.BadgeModel


class PersonalBadgeFragment : Fragment() {

    private lateinit var binding: FragmentBadgesBinding

    private lateinit var badgeAdapter: BadgeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBadgesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        setupBadges()
    }

    private fun setupBadges() {
        badgeAdapter = BadgeAdapter().apply {
            onItemClick = {}
        }

        binding.badgesGrid.adapter = badgeAdapter
        binding.badgesGrid.setHasFixedSize(true)
        binding.badgesGrid.layoutManager = GridLayoutManager(context, 2)

        val badge = BadgeModel("2023", "Circuito de Las Estaciones PRIMAVERA 2023", "CDMX", "12/02/2023",
                "Circuito de Las Estaciones PRIMAVERA 2023", "2323", "10:23", imageResource = R.drawable.badge_dummy)
        val items = listOf(badge, badge, badge, badge)
        badgeAdapter.setItems(items)

    }
}