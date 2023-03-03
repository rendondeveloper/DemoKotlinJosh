package com.asdeporte.asdeportev2.ui.inscription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.ActivityResumeBinding
import com.asdeporte.asdeportev2.databinding.ActivitySuscriptionBinding
import com.asdeporte.asdeportev2.databinding.FragmentPaymentBinding
import com.asdeporte.asdeportev2.ui.SuscriptionAdapter

class ResumeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResumeBinding

    private lateinit var adapter: SuscriptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResumeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.close.setOnClickListener {
            //this.finish()
            onBackPressed()
        }*/
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.totalView.isTotal()

        /*adapter = SuscriptionAdapter().apply {
            onItemClick = {

            }
        }

        binding.suscriptionList.adapter = adapter
        binding.suscriptionList.setHasFixedSize(true)
        binding.suscriptionList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val emptyEvent = EventData("", "", "", "")
        val items = listOf(emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent)
        adapter.setItems(items)

        binding.monthlyCard.setOnClickListener {
            val emptyEvent = EventData("", "", "", "")
            val items = listOf(emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent)
            adapter.setItems(items)
            binding.monthlyCard.foreground = ContextCompat.getDrawable(this, R.drawable.black_dynamic_border)
            binding.yearCard.foreground = null
        }

        binding.yearCard.setOnClickListener {
            val emptyEvent = EventData("", "", "", "")
            val items = listOf(emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent, emptyEvent)
            adapter.setItems(items)
            binding.monthlyCard.foreground = null
            binding.yearCard.foreground = ContextCompat.getDrawable(this, R.drawable.black_dynamic_border)
        }*/
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_out_bottom)
    }
}