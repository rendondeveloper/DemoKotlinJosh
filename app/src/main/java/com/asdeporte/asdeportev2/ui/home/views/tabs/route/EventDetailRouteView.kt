package com.asdeporte.asdeportev2.ui.home.views.tabs.routeimport android.content.Contextimport android.graphics.Colorimport android.graphics.DashPathEffectimport android.util.AttributeSetimport android.view.LayoutInflaterimport android.widget.FrameLayoutimport androidx.core.content.ContextCompatimport com.asdeporte.asdeportev2.Rimport com.asdeporte.asdeportev2.databinding.EventDetailViewRouteBindingimport com.github.mikephil.charting.charts.LineChartimport com.github.mikephil.charting.components.Legendimport com.github.mikephil.charting.data.Entryimport com.github.mikephil.charting.data.LineDataimport com.github.mikephil.charting.data.LineDataSetimport com.github.mikephil.charting.interfaces.datasets.ILineDataSetimport com.github.mikephil.charting.utils.Utilsimport com.google.android.gms.maps.CameraUpdateFactoryimport com.google.android.gms.maps.model.LatLngclass EventDetailRouteView @JvmOverloads constructor(    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : FrameLayout(context, attrs, defStyle) {    private var binding: EventDetailViewRouteBinding    private var chartAverageRecovery: LineChart? = null    init {        binding = EventDetailViewRouteBinding.inflate(LayoutInflater.from(context), this, true)        binding.mapView.getMapAsync { googleMap ->            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-34.0, 151.0), 10f))        }    }    fun setDataGraphic() {        chartAverageRecovery = binding.lineChart        chartAverageRecovery?.setBackgroundColor(Color.WHITE)        chartAverageRecovery?.description?.isEnabled = false        chartAverageRecovery?.setTouchEnabled(false)        chartAverageRecovery?.setDrawGridBackground(false)        chartAverageRecovery?.isDragEnabled = false        chartAverageRecovery?.setScaleEnabled(false)        chartAverageRecovery?.setPinchZoom(false)        val xAxis = chartAverageRecovery?.xAxis        xAxis?.enableGridDashedLine(10f, 0f, 0f)        xAxis?.setDrawGridLines(false)        xAxis?.setDrawAxisLine(false)        xAxis?.setDrawLabels(false)        val yAxis = chartAverageRecovery?.axisLeft        chartAverageRecovery?.axisRight?.isEnabled = false        yAxis?.enableGridDashedLine(10f, 0f, 100f)        yAxis?.gridColor = R.color.white        yAxis?.axisMaximum = 3.3f        yAxis?.axisMinimum = 2.9f        yAxis?.setDrawGridLines(true)        yAxis?.setDrawLimitLinesBehindData(false)        xAxis?.setDrawLimitLinesBehindData(false)        val values = java.util.ArrayList<Entry>()        values.add(Entry(20f, 3.15f))        values.add(Entry(40f, 2.95f))        values.add(Entry(60f, 3.19f))        values.add(Entry(80f, 3.23f))        values.add(Entry(100f, 3.0f))        values.add(Entry(120f, 3.3f))        values.add(Entry(140f, 3.06f))        values.add(Entry(160f, 3.15f))        values.add(Entry(180f, 2.95f))        values.add(Entry(200f, 3.19f))        values.add(Entry(220f, 3.23f))        values.add(Entry(240f, 3.0f))        values.add(Entry(260f, 3.3f))        values.add(Entry(280f, 3.06f))        values.add(Entry(300f, 3.26f))        values.add(Entry(320f, 3.1f))        values.add(Entry(340f, 3.19f))        values.add(Entry(360f, 3.23f))        values.add(Entry(380f, 3.0f))        values.add(Entry(400f, 3.16f))        val set1 = LineDataSet(values, "")        set1.setDrawIcons(false)        set1.enableDashedLine(10f, 0f, 0f)        set1.color = Color.BLACK        set1.setCircleColor(Color.TRANSPARENT)        set1.lineWidth = 4f        set1.circleRadius = 0f        set1.setDrawCircleHole(false)        set1.formLineWidth = 1f        set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)        set1.color = Color.parseColor("#FF6A00")        set1.formSize = 15f        set1.valueTextSize = 0f        set1.enableDashedHighlightLine(10f, 5f, 0f)        set1.setDrawFilled(true)        if (Utils.getSDKInt() >= 18) {            val drawable = ContextCompat.getDrawable(context, R.drawable.background_graph)            set1.fillDrawable = drawable        } else {            set1.fillColor = Color.BLACK        }        val dataSets = java.util.ArrayList<ILineDataSet>()        dataSets.add(set1)        val data = LineData(dataSets)        chartAverageRecovery?.data = data        val legend = chartAverageRecovery?.legend        legend?.form = Legend.LegendForm.LINE    }}