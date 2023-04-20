package com.asdeporte.asdeportev2.ui.profile.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentBadgesBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.profile.adapters.badget.BadgeAdapter
import com.asdeporte.asdeportev2.ui.profile.adapters.badget.BadgeModel
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator


class PersonalBadgeFragment : Fragment() {

    private lateinit var binding: FragmentBadgesBinding


    private lateinit var demoCollectionAdapter: BadgeCollectionAdapter
    private lateinit var badgeAdapter: BadgeAdapter
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var viewPager: ViewPager2

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


        demoCollectionAdapter = BadgeCollectionAdapter(this)
        viewPager = binding.pagerBadge

        // Our object is just a integer :-P
        viewPager.offscreenPageLimit = 3

        viewPager.adapter = demoCollectionAdapter

        viewPager.apply {
            offscreenPageLimit = 1
            val recyclerView = getChildAt(0) as RecyclerView
            recyclerView.apply {
                setPadding(0, 0, 360, 0)
                clipToPadding = false
            }
        }

        dotsIndicator = binding.dotsIndicator
        dotsIndicator.selectedDotColor = ContextCompat.getColor(requireContext(), R.color.orange_as_light)
        dotsIndicator.attachTo(viewPager)

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

class BadgeCollectionAdapter(fragment: PersonalBadgeFragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    var images = listOf(R.drawable.badge_next_dummy, R.drawable.badge_next_dummy, R.drawable.badge_next_dummy)
    var titles = listOf(fragment.getString(R.string.badge_event_one),
            fragment.getString(R.string.badge_event_two),
            fragment.getString(R.string.badge_event_three))

    override fun createFragment(position: Int): Fragment {
        val fragment = BadgeObjectFragment()

        fragment.arguments = Bundle().apply {
            putInt("Image", images[position])
            putString("Title", titles[position])
        }
        return fragment
    }
}

class BadgeObjectFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.badge_next, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey("Title") }?.apply {

            val imageView: ImageView = view.findViewById(R.id.badge_image)
            imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(), getInt("Image")))

            val textView: TextView = view.findViewById(R.id.badge_name_short)
            textView.text = getString("Title").toString().uppercase()
        }
    }
}