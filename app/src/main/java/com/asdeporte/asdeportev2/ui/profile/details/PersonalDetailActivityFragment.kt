package com.asdeporte.asdeportev2.ui.profile.details

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentPersonalDetailActivityBinding
import com.asdeporte.asdeportev2.ui.profile.details.formater.AxisAverageForceValueFormatter
import com.asdeporte.asdeportev2.ui.profile.details.formater.AxisAverageForceZonesValueFormatter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class PersonalDetailActivityFragment : Fragment() {

    private var _binding: FragmentPersonalDetailActivityBinding? = null
    private val binding get() = _binding!!

    private var lineChart: LineChart? = null
    private var barChart: BarChart? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPersonalDetailActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backImage.setOnClickListener {
            findNavController().popBackStack()
        }
        setupCaloriesAndHeartRate()
        setupAverageForce()
    }

    private fun setupCaloriesAndHeartRate() {
        binding.apply {
            lineChart = graphCaloriesAndHeartRate.lineChart
            graphCaloriesAndHeartRate.lineChart.setPinchZoom(false)
            graphCaloriesAndHeartRate.lineChart.setTouchEnabled(false)
            graphCaloriesAndHeartRate.lineChart.isDragEnabled = false
            graphCaloriesAndHeartRate.lineChart.setScaleEnabled(false)
            graphCaloriesAndHeartRate.lineChart.setDrawGridBackground(false)
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

    private fun setupAverageForce() {
        barChart = binding.graphAverageForce.barChart

        barChart!!.setBackgroundColor(Color.WHITE)
        barChart!!.description.isEnabled = false
        barChart!!.setMaxVisibleValueCount(60)
        barChart!!.setPinchZoom(false)
        barChart!!.setTouchEnabled(false)
        barChart!!.isDragEnabled = false
        barChart!!.setScaleEnabled(false)
        barChart!!.setDrawBarShadow(false)
        barChart!!.setDrawGridBackground(false)
        //chartBar!!.axisRight.isEnabled = false

        val leftAxis: YAxis = barChart!!.axisLeft
        leftAxis.typeface = ResourcesCompat.getFont(requireContext(), R.font.kanit_regular)
        leftAxis.textSize = 12f
        leftAxis.textColor = resources.getColor(R.color.label_secondary)
        leftAxis.setLabelCount(8, false)
        leftAxis.valueFormatter = AxisAverageForceValueFormatter()
        leftAxis.setDrawGridLines(true)
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.spaceTop = 15f
        leftAxis.axisMinimum = 0f

        val rightAxis: YAxis = barChart!!.axisRight
        rightAxis.setDrawGridLines(false)
        rightAxis.isEnabled = false
        rightAxis.typeface = ResourcesCompat.getFont(requireContext(), R.font.kanit_regular)
        rightAxis.textSize = 12f
        rightAxis.textColor = resources.getColor(R.color.label_secondary)
        rightAxis.setLabelCount(8, false)
        rightAxis.valueFormatter = AxisAverageForceValueFormatter()
        rightAxis.spaceTop = 15f
        rightAxis.axisMinimum = 0f //

        val xAxis = barChart!!.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.typeface = ResourcesCompat.getFont(requireContext(), R.font.kanit_regular)
        xAxis.textSize = 10f
        xAxis.textColor = resources.getColor(R.color.label_secondary)
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.labelCount = 14
        xAxis.valueFormatter = AxisAverageForceZonesValueFormatter()

        barChart!!.legend.apply {
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
        set1.form = Legend.LegendForm.CIRCLE
        set1.setDrawValues(false)

        val dataSets = java.util.ArrayList<IBarDataSet>()
        dataSets.add(set1)

        val data = BarData(dataSets)
        barChart!!.data = data
        barChart!!.setFitBars(false)

        barChart!!.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}