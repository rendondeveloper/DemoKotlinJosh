package com.asdeporte.asdeportev2.ui.profile.details

import android.graphics.Color
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
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentPersonalHistoryBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.profile.adapters.EventHistoryAdapter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.material.tabs.TabLayout

class PersonalHistoryFragment : Fragment() {

    private var binding: FragmentPersonalHistoryBinding? = null
    private var chart: PieChart? = null
    private var lineChart: LineChart? = null
    private var mTf: Typeface? = null

    private var chartAverageSleep: PieChart? = null
    private var chartAverageSleepTwo: LineChart? = null

    private lateinit var eventsAdapter: EventHistoryAdapter
    val testEvent = EventData("123",
        "7, 14 y 21K by WomanUp",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")

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

            tabViewMain.addOnTabSelectedListener(object  : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val index = tab?.position ?: 0
                    llActivity.visibility = if(index == 0) View.VISIBLE else View.GONE
                    llSleep.visibility = if(index == 1) View.VISIBLE else View.GONE
                    llHrv.visibility = if(index == 2) View.VISIBLE else View.GONE

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
        setupStatistics()
        setupGraphicSleep()
        setupGraphicSleepTwo()
        setupEvents()
        setupCalories()
    }

    private fun setupCalories(){
        mTf = ResourcesCompat.getFont(requireContext(), R.font.kanit_regular)
        binding?.apply {
            lineChart = caloriesHeart.lineChart
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
            lineFrecuencia.color = Color.RED
            lineFrecuencia.valueTextColor = Color.BLACK
            val lineCalorias = LineDataSet(calorias, "Calorias")
            lineCalorias.color = Color.BLACK
            lineCalorias.valueTextColor = Color.BLACK
            val lineDataSets = ArrayList<ILineDataSet>()
            lineDataSets.add(lineFrecuencia)
            lineDataSets.add(lineCalorias)
            val lineData = LineData(lineDataSets)
            lineChart?.data = lineData
            lineChart?.description?.isEnabled = false
            lineChart?.setDrawGridBackground(false)
            val xAxis = lineChart?.xAxis
            xAxis?.position = XAxis.XAxisPosition.BOTTOM
            val yAxisLeft = lineChart?.axisLeft
            yAxisLeft?.axisMinimum = 0f
            val yAxisRight = lineChart?.axisRight
            yAxisRight?.isEnabled = false
            xAxis?.setDrawGridLines(false)
            lineChart?.invalidate()
        }
    }
    private fun setupGraphicSleepTwo() {
        chartAverageSleepTwo =  binding!!.activitySleep.graphSleep
        chartAverageSleepTwo!!.setViewPortOffsets(0f, 0f, 0f, 0f)
        chartAverageSleepTwo!!.description.isEnabled = false
        chartAverageSleepTwo!!.setTouchEnabled(false)
        chartAverageSleepTwo!!.isDragEnabled = false
        chartAverageSleepTwo!!.setScaleEnabled(false)
        chartAverageSleepTwo!!.setPinchZoom(false)
        val x =  chartAverageSleepTwo!!.xAxis
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

    private fun setupGraphicSleep(){
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
            l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            l.orientation = Legend.LegendOrientation.VERTICAL
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

    private fun setDataSleep(){
        val values = arrayOf(3.8f, 3f, 2f, 1.2f)
        val titles = arrayOf(
                "Sueño profundo 38.6%", "Sueño ligero 30.8%", "Sueño REM 22.5%", "Interrupciones 8.1%"
        )

        val entries = ArrayList<PieEntry>()

        values.forEachIndexed { index, _ ->
            entries.add(PieEntry(
                    values[index], titles[index]
            ))
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
        binding?.eventsList?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val items = listOf(testEvent, testEvent, testEvent, testEvent, testEvent, testEvent)
        eventsAdapter.setItems(items)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}