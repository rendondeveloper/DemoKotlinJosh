package com.asdeporte.asdeportev2.ui.profile.details

import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentPersonalHistoryBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.profile.adapters.EventHistoryAdapter
import com.asdeporte.asdeportev2.ui.profile.details.formater.AxisAverageForceValueFormatter
import com.asdeporte.asdeportev2.ui.profile.details.formater.AxisAverageForceZonesValueFormatter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils
import com.google.android.material.tabs.TabLayout
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class PersonalHistoryFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var eventsCollectionAdapter: EventsCollectionAdapter

    private var binding: FragmentPersonalHistoryBinding? = null
    private var chart: PieChart? = null
    private var lineChart: LineChart? = null
    private var mTf: Typeface? = null

    private var chartAverageSleep: PieChart? = null
    private var chartActivity: LineChart? = null
    private var chartAverageSleepTwo: LineChart? = null
    private var chartAverageRecovery: LineChart? = null
    private var chartBar: BarChart? = null

    private lateinit var eventsAdapter: EventHistoryAdapter
    val testEvent = EventData(
        "123",
        "7, 14 y 21K by WomanUp",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalHistoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding?.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            statisticsView.visibility = View.VISIBLE
            eventsView.visibility = View.GONE

            tabViewMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val index = tab?.position ?: 0
                    llActivity.visibility = if (index == 0) View.VISIBLE else View.GONE
                    llSleep.visibility = if (index == 1) View.VISIBLE else View.GONE
                    llHrv.visibility = if (index == 2) View.VISIBLE else View.GONE
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
            })

            tabView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab?.position == 0) {
                        statisticsView.visibility = View.VISIBLE
                        eventsView.visibility = View.GONE
                    } else {
                        statisticsView.visibility = View.GONE
                        eventsView.visibility = View.VISIBLE
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
            })
        }

        binding!!.historyItemActivity.tvShowDetail.setOnClickListener {
            findNavController().safelyNavigate(R.id.action_personal_history_to_personalDetailActivityFragment)
        }

        setupStatistics()
        setupGraphicSleep()
        setupGraphicSleepTwo()
        setupEvents()
        setupCalories()
        setupGraphicRecovery()
        setupGraphicActivity()
        setupGraphicBar()
        setupPager()
    }

    private fun setupPager() {
        binding?.let {
            eventsCollectionAdapter = EventsCollectionAdapter(this)
            viewPager = it.pagerEvents

            viewPager.offscreenPageLimit = 4
            viewPager.adapter = eventsCollectionAdapter

            dotsIndicator = it.dotsIndicator
            dotsIndicator.selectedDotColor =
                ContextCompat.getColor(requireContext(), R.color.orange_as_light)
            dotsIndicator.attachTo(viewPager)
        }
    }

    private fun setupGraphicBar() {
        chartBar = binding!!.historyItemAverageForce.barChart

        chartBar!!.setBackgroundColor(Color.WHITE)
        chartBar!!.description.isEnabled = false
        chartBar!!.setMaxVisibleValueCount(60)
        chartBar!!.setPinchZoom(false)
        chartBar!!.setTouchEnabled(false)
        chartBar!!.isDragEnabled = false
        chartBar!!.setScaleEnabled(false)
        chartBar!!.setDrawBarShadow(false)
        chartBar!!.setDrawGridBackground(false)
        //chartBar!!.axisRight.isEnabled = false

        val leftAxis: YAxis = chartBar!!.axisLeft
        leftAxis.typeface = ResourcesCompat.getFont(requireContext(), R.font.kanit_regular)
        leftAxis.textSize = 12f
        leftAxis.textColor = resources.getColor(R.color.label_secondary)
        leftAxis.setLabelCount(8, false)
        leftAxis.valueFormatter = AxisAverageForceValueFormatter()
        leftAxis.setDrawGridLines(true)
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.spaceTop = 15f
        leftAxis.axisMinimum = 0f

        val rightAxis: YAxis = chartBar!!.axisRight
        rightAxis.setDrawGridLines(false)
        rightAxis.isEnabled = false
        rightAxis.typeface = ResourcesCompat.getFont(requireContext(), R.font.kanit_regular)
        rightAxis.textSize = 12f
        rightAxis.textColor = resources.getColor(R.color.label_secondary)
        rightAxis.setLabelCount(8, false)
        rightAxis.valueFormatter = AxisAverageForceValueFormatter()
        rightAxis.spaceTop = 15f
        rightAxis.axisMinimum = 0f //

        val xAxis = chartBar!!.xAxis
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.typeface = ResourcesCompat.getFont(requireContext(), R.font.kanit_regular)
        xAxis.textSize = 10f
        xAxis.textColor = resources.getColor(R.color.label_secondary)
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.labelCount = 14
        xAxis.valueFormatter = AxisAverageForceZonesValueFormatter()

        chartBar!!.legend.apply {
            isEnabled = false
        }

        val values = ArrayList<BarEntry>()

        values.add(BarEntry(1f, 15f))
        values.add(BarEntry(2f, 22f))
        values.add(BarEntry(3f, 28f))
        values.add(BarEntry(4f, 44f))
        values.add(BarEntry(5f, 10f))

        val set1: BarDataSet = BarDataSet(values, "Data Set")
        set1.setColors(Color.parseColor("#FF6A00"))
        set1.form = LegendForm.CIRCLE
        set1.setDrawValues(false)

        val dataSets = java.util.ArrayList<IBarDataSet>()
        dataSets.add(set1)

        val data = BarData(dataSets)
        chartBar!!.data = data
        chartBar!!.setFitBars(false)

        chartBar!!.invalidate()
    }

    private fun setupCalories() {
        mTf = ResourcesCompat.getFont(requireContext(), R.font.kanit_regular)
        binding?.apply {
            lineChart = caloriesHeart.lineChart
            caloriesHeart.lineChart.setPinchZoom(false)
            caloriesHeart.lineChart.setTouchEnabled(false)
            caloriesHeart.lineChart.isDragEnabled = false
            caloriesHeart.lineChart.setScaleEnabled(false)
            caloriesHeart.lineChart.setDrawGridBackground(false)
            val frecuencia = ArrayList<Entry>()
            frecuencia.add(Entry(0f, 150f))
            frecuencia.add(Entry(10f, 300f))
            frecuencia.add(Entry(30f, 450f))
            frecuencia.add(Entry(50f, 600f))
            frecuencia.add(Entry(60f, 600f))
            val calorias = ArrayList<Entry>()
            calorias.add(Entry(0f, 120f))
            calorias.add(Entry(15f, 350f))
            calorias.add(Entry(35f, 400f))
            calorias.add(Entry(40f, 500f))
            calorias.add(Entry(55f, 560f))
            val lineFrecuencia = LineDataSet(frecuencia, "Frecuencia")
            lineFrecuencia.setDrawCircles(false)
            lineFrecuencia.color = Color.RED
            lineFrecuencia.valueTextColor = Color.TRANSPARENT
            val lineCalorias = LineDataSet(calorias, "Calorias")
            lineCalorias.setDrawCircles(false)
            lineCalorias.color = Color.BLACK
            lineCalorias.valueTextColor = Color.TRANSPARENT
            val lineDataSets = ArrayList<ILineDataSet>()
            lineDataSets.add(lineFrecuencia)
            lineDataSets.add(lineCalorias)
            val lineData = LineData(lineDataSets)
            lineChart?.data = lineData
            lineChart?.description?.isEnabled = false
            lineChart?.setDrawGridBackground(false)


            val rightAxis: YAxis = lineChart!!.axisRight


            val leftAxis: YAxis = lineChart!!.axisLeft


            val xAxis = lineChart?.xAxis
            xAxis?.position = XAxis.XAxisPosition.BOTTOM
            val yAxisLeft = lineChart?.axisLeft
            yAxisLeft?.axisMinimum = 0f
            val yAxisRight = lineChart?.axisRight
            yAxisRight?.isEnabled = false
            xAxis?.setDrawGridLines(false)

            lineChart!!.legend.apply {
                isEnabled = false
            }

            lineChart?.invalidate()
        }
    }

    private fun setupGraphicSleepTwo() {
        chartAverageSleepTwo = binding!!.activitySleep.graphSleep
        chartAverageSleepTwo!!.setViewPortOffsets(0f, 0f, 0f, 0f)
        chartAverageSleepTwo!!.description.isEnabled = false
        chartAverageSleepTwo!!.setTouchEnabled(false)
        chartAverageSleepTwo!!.isDragEnabled = false
        chartAverageSleepTwo!!.setScaleEnabled(false)
        chartAverageSleepTwo!!.setPinchZoom(false)
        val x = chartAverageSleepTwo!!.xAxis
        x.isEnabled = false

        val y: YAxis = chartAverageSleepTwo!!.axisLeft
        y.setLabelCount(6, false)
        y.textColor = Color.TRANSPARENT
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        y.setDrawGridLines(false)

        chartAverageSleepTwo!!.axisRight.isEnabled = false
        chartAverageSleepTwo!!.legend.isEnabled = false
        chartAverageSleepTwo!!.clearAnimation()
        chartAverageSleepTwo!!.invalidate()
        val values = ArrayList<Entry>()
        values.add(Entry(0f, 0f))
        values.add(Entry(40f, 5f))
        values.add(Entry(100f, 8f))
        values.add(Entry(200f, 9f))
        values.add(Entry(300f, 8f))
        values.add(Entry(400f, 7f))
        values.add(Entry(500f, 6f))
        values.add(Entry(600f, 6f))
        values.add(Entry(700f, 7f))
        values.add(Entry(800f, 10f))
        values.add(Entry(900f, 11f))
        values.add(Entry(1000f, 11f))
        values.add(Entry(1100f, 10f))
        values.add(Entry(1500f, 8f))
        values.add(Entry(1600f, 9f))

        val set1: LineDataSet = LineDataSet(values, "DataSet 1")

        set1.mode = LineDataSet.Mode.CUBIC_BEZIER
        set1.setDrawFilled(true)
        set1.setDrawCircles(false)
        set1.lineWidth = 1.8f
        set1.circleRadius = 4f
        //set1.color = Color.rgb(51, 180, 105)
        set1.color = Color.parseColor("#33B469")
        set1.fillColor = Color.parseColor("#f5e6e6")
        set1.fillAlpha = 100
        set1.setDrawHorizontalHighlightIndicator(false)
        val data = LineData(set1)
        data.setValueTextSize(9f)
        data.setDrawValues(false)
        chartAverageSleepTwo!!.data = data
    }

    private fun setupGraphicSleep() {
        chartAverageSleep = binding!!.averageSleep.pieChartAverageSleep
        chartAverageSleep!!.setUsePercentValues(true)
        chartAverageSleep!!.description.isEnabled = false
        chartAverageSleep?.setTouchEnabled(false)
        chartAverageSleep!!.isRotationEnabled = true
        chartAverageSleep!!.isHighlightPerTapEnabled = true
        chartAverageSleep!!.isDrawHoleEnabled = false
        chartAverageSleep

        chartAverageSleep!!.legend.apply {
            verticalAlignment = Legend.LegendVerticalAlignment.CENTER
            horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            orientation = Legend.LegendOrientation.VERTICAL
            xEntrySpace = 34f
            yEntrySpace = 0f
            yOffset = 0f
        }

        //chartAverageSleep?.setEntryLabelTextSize(0f)
        chartAverageSleep?.setDrawEntryLabels(false)
        setDataSleep()
    }

    private fun setupGraphicActivity() {
        chartActivity = binding!!.historyItemActivity.graphActivity
        chartActivity!!.setViewPortOffsets(0f, 0f, 0f, 0f)
        chartActivity!!.description.isEnabled = false
        chartActivity!!.setTouchEnabled(false)
        chartActivity!!.isDragEnabled = false
        chartActivity!!.setScaleEnabled(false)
        chartActivity!!.setPinchZoom(false)
        val x = chartActivity!!.xAxis
        x.isEnabled = false

        val y: YAxis = chartActivity!!.axisLeft
        y.setLabelCount(6, false)
        y.textColor = Color.TRANSPARENT
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        y.setDrawGridLines(false)

        chartActivity!!.axisRight.isEnabled = false
        chartActivity!!.legend.isEnabled = false
        chartActivity!!.clearAnimation()
        chartActivity!!.invalidate()
        val values = ArrayList<Entry>()
        values.add(Entry(0f, 0f))
        values.add(Entry(40f, 5f))
        values.add(Entry(100f, 8f))
        values.add(Entry(200f, 9f))
        values.add(Entry(300f, 8f))
        values.add(Entry(400f, 7f))
        values.add(Entry(500f, 6f))
        values.add(Entry(600f, 6f))
        values.add(Entry(700f, 7f))
        values.add(Entry(800f, 10f))
        values.add(Entry(900f, 11f))
        values.add(Entry(1000f, 11f))
        values.add(Entry(1100f, 10f))
        values.add(Entry(1500f, 8f))
        values.add(Entry(1600f, 9f))

        val set1: LineDataSet = LineDataSet(values, "DataSet 1")

        set1.mode = LineDataSet.Mode.CUBIC_BEZIER
        set1.setDrawFilled(true)
        set1.setDrawCircles(false)
        set1.lineWidth = 1.8f
        set1.circleRadius = 4f
        //set1.color = Color.rgb(51, 180, 105)
        set1.color = Color.parseColor("#33B469")
        set1.fillColor = Color.parseColor("#f5e6e6")
        set1.fillAlpha = 100
        set1.setDrawHorizontalHighlightIndicator(false)
        val data = LineData(set1)
        data.setValueTextSize(9f)
        data.setDrawValues(false)
        chartActivity!!.data = data
    }


    private fun setupGraphicRecovery() {
        chartAverageRecovery = binding!!.historyRecovery.lineChart
        chartAverageRecovery!!.setBackgroundColor(Color.WHITE)
        chartAverageRecovery!!.description.isEnabled = false
        chartAverageRecovery!!.setTouchEnabled(false)
        chartAverageRecovery!!.setDrawGridBackground(false)
        chartAverageRecovery!!.isDragEnabled = false
        chartAverageRecovery!!.setScaleEnabled(false)
        chartAverageRecovery!!.setPinchZoom(false)
        val xAxis = chartAverageRecovery!!.xAxis
        xAxis.enableGridDashedLine(10f, 0f, 0f)
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawLabels(false)
        val yAxis: YAxis = chartAverageRecovery!!.getAxisLeft()
        chartAverageRecovery!!.axisRight.isEnabled = false
        yAxis.enableGridDashedLine(10f, 0f, 100f)
        yAxis.gridColor = R.color.white
        yAxis.axisMaximum = 130f
        yAxis.axisMinimum = 90f
        yAxis.setDrawGridLines(true)
        yAxis.setDrawLimitLinesBehindData(false)
        xAxis.setDrawLimitLinesBehindData(false)

        val values = java.util.ArrayList<Entry>()

        values.add(Entry(20f, 106f))
        values.add(Entry(50f, 100f))
        values.add(Entry(80f, 96f))
        values.add(Entry(110f, 101f))
        values.add(Entry(140f, 106f))
        values.add(Entry(170f, 110f))
        values.add(Entry(200f, 120f))
        values.add(Entry(230f, 110f))
        values.add(Entry(270f, 96f))
        values.add(Entry(300f, 106f))
        values.add(Entry(310f, 110f))
        values.add(Entry(320f, 120f))
        values.add(Entry(340f, 110f))
        values.add(Entry(370f, 100f))

        val set1: LineDataSet = LineDataSet(values, "")
        set1.setDrawIcons(false)
        set1.enableDashedLine(10f, 0f, 0f)
        set1.color = Color.BLACK
        set1.setCircleColor(Color.TRANSPARENT)
        set1.lineWidth = 4f
        set1.circleRadius = 0f
        set1.setDrawCircleHole(false)
        set1.formLineWidth = 1f
        set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
        set1.color = Color.parseColor("#FF6A00")
        //set1.fillColor = Color.parseColor("#f5e6e6")
        set1.formSize = 15f
        set1.valueTextSize = 0f
        set1.enableDashedHighlightLine(10f, 5f, 0f)
        set1.setDrawFilled(true)
        //set1.fillFormatter = IFillFormatter { dataSet, dataProvider -> chartAverageRecoveryt.getAxisLeft().getAxisMinimum() }
        if (Utils.getSDKInt() >= 18) {
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.background_graph)
            set1.fillDrawable = drawable
        } else {
            set1.fillColor = Color.BLACK
        }

        val dataSets = java.util.ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val data = LineData(dataSets)
        chartAverageRecovery!!.data = data
        val l = chartAverageRecovery!!.legend
        l.form = LegendForm.LINE
    }

    private fun setupStatistics() {
        binding?.apply {
            chart = sportProfile.pieChart
            chart!!.setUsePercentValues(true)
            chart!!.description.isEnabled = false
            chart!!.setExtraOffsets(5f, 10f, 5f, 5f)
            chart!!.dragDecelerationFrictionCoef = 0.95f
            chart!!.isDrawHoleEnabled = false
            chart!!.setTransparentCircleColor(Color.WHITE)
            chart!!.setTransparentCircleAlpha(110)
            chart!!.setDrawCenterText(false)
            chart!!.rotationAngle = 0f
            chart!!.isRotationEnabled = true
            chart!!.isHighlightPerTapEnabled = true
            val l = chart!!.legend
            l.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            l.orientation = Legend.LegendOrientation.VERTICAL
            l.form = LegendForm.CIRCLE
            l.setDrawInside(false)
            l.xEntrySpace = 7f
            l.yEntrySpace = 0f
            l.yOffset = 0f
            chart?.setEntryLabelColor(Color.WHITE)
            chart?.setEntryLabelTextSize(12f)
            chart?.setTouchEnabled(false)
            setData(2, 10f)
        }
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
            entries.add(
                PieEntry(values[i], titles[i % titles.size])
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

    private fun setDataSleep() {
        val values = arrayOf(3.8f, 3f, 2f, 1.2f)
        val titles = arrayOf(
            "Sueño profundo 38.6%", "Sueño ligero 30.8%", "Sueño REM 22.5%", "Interrupciones 8.1%"
        )

        val entries = ArrayList<PieEntry>()

        values.forEachIndexed { index, _ ->
            entries.add(
                PieEntry(values[index], titles[index])
            )
        }

        val dataSet = PieDataSet(entries, "")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        val colors = ArrayList<Int>()
        colors.add(ContextCompat.getColor(requireContext(), R.color.blue))
        colors.add(ContextCompat.getColor(requireContext(), R.color.green))
        colors.add(ContextCompat.getColor(requireContext(), R.color.yellow))
        colors.add(ContextCompat.getColor(requireContext(), R.color.pink))
        dataSet.colors = colors
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(0f)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextColor(Color.WHITE)
        chartAverageSleep!!.data = data

        chartAverageSleep!!.highlightValues(null)
        chartAverageSleep!!.invalidate()
    }

    private fun setupEvents() {
        eventsAdapter = EventHistoryAdapter().apply {
            onItemClick = {
            }
        }

        binding?.eventsList?.adapter = eventsAdapter
        binding?.eventsList?.setHasFixedSize(true)
        binding?.eventsList?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val items = listOf(testEvent, testEvent, testEvent, testEvent, testEvent, testEvent)
        eventsAdapter.setItems(items)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

class EventsCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return EventObjectFragment()
    }
}

class EventObjectFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.history_item_total_events, container, false)
    }
}