package com.asdeporte.asdeportev2.ui.profile.details

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentPersonalHistoryBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.profile.adapters.EventHistoryAdapter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.material.tabs.TabLayout

class PersonalHistoryFragment : Fragment() {

    private var _binding: FragmentPersonalHistoryBinding? = null
    private val binding get() = _binding!!

    private var chart: PieChart? = null

    private lateinit var eventsAdapter: EventHistoryAdapter
    val testEvent = EventData("123",
        "7, 14 y 21K by WomanUp",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.statisticsView.visibility = View.VISIBLE
        binding.eventsView.visibility = View.GONE

        binding.tabView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) {
                    binding.statisticsView.visibility = View.VISIBLE
                    binding.eventsView.visibility = View.GONE
                } else {
                    binding.statisticsView.visibility = View.GONE
                    binding.eventsView.visibility = View.VISIBLE
                }
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })

        setupStatistics()
        setupEvents()
    }

    private fun setupStatistics() {

        chart = binding.pieChart
        chart!!.setUsePercentValues(true)
        chart!!.description.isEnabled = false
        chart!!.setExtraOffsets(5f, 10f, 5f, 5f)

        chart!!.dragDecelerationFrictionCoef = 0.95f

        //chart!!.setCenterTextTypeface(tfLight)
        //chart!!.centerText = generateCenterSpannableText()

        chart!!.isDrawHoleEnabled = false
        //chart!!.setHoleColor(Color.WHITE)

        chart!!.setTransparentCircleColor(Color.WHITE)
        chart!!.setTransparentCircleAlpha(110)

        chart!!.setDrawCenterText(false)

        chart!!.rotationAngle = 0f
        // enable rotation of the chart by touch
        // enable rotation of the chart by touch
        chart!!.isRotationEnabled = true
        chart!!.isHighlightPerTapEnabled = true

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        //chart!!.animateY(1400, Easing.EaseInOutQuad)
        // chart.spin(2000, 0, 360);

        // chart.spin(2000, 0, 360);
        val l = chart!!.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f

        // entry label styling

        // entry label styling
        chart?.setEntryLabelColor(Color.WHITE)
        //chart!!.setEntryLabelTypeface(tfRegular)
        chart?.setEntryLabelTextSize(12f)

        chart?.setTouchEnabled(false)

        setData(2, 10f)

        binding.dotsIndicator.selectedDotColor = ContextCompat.getColor(requireContext(), R.color.asd_pink)
        //binding.dotsIndicator.attachTo(viewPager)

    }
    private fun setData(count: Int, range: Float) {
        val values = arrayOf(7f, 3f)
        val titles = arrayOf(
            "Triatleta", "Corredor"
        )

        val entries = ArrayList<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until count) {
            val value = (Math.random() * range + range / 5).toFloat()
            entries.add(PieEntry(
                    values[i],
                    titles[i % titles.size]
                )
            )
        }
        val dataSet = PieDataSet(entries, "")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        val orangeColor = ContextCompat.getColor(requireContext(), R.color.orange_as_light)
        val grayColor = ContextCompat.getColor(requireContext(), R.color.gray_600)
        // add a lot of colors
        val colors = ArrayList<Int>()
        colors.add(orangeColor)
        colors.add(grayColor)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
        colors.add(ColorTemplate.getHoloBlue())
        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextColor(Color.WHITE)
        //data.setValueTypeface(tfLight)
        chart!!.data = data

        // undo all highlights
        chart!!.highlightValues(null)
        chart!!.invalidate()
    }

    private fun setupEvents() {
        eventsAdapter = EventHistoryAdapter().apply {
            onItemClick = {
                findNavController().safelyNavigate(R.id.to_personal_history)
            }
        }

        binding.eventsList.adapter = eventsAdapter
        binding.eventsList.setHasFixedSize(true)
        binding.eventsList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val items = listOf(testEvent, testEvent, testEvent, testEvent, testEvent, testEvent)
        eventsAdapter.setItems(items)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}