package com.asdeporte.asdeportev2.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.ActivitySuscriptionBinding
import com.asdeporte.hermes.adapters.RecyclerViewAdapterBase
import com.asdeporte.hermes.adapters.ViewWrapper
import com.chargebee.android.Chargebee
import com.chargebee.android.exceptions.ChargebeeResult

class SuscriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuscriptionBinding

    private lateinit var adapter: SuscriptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySuscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.close.setOnClickListener {
            //this.finish()
            onBackPressed()
        }

        adapter = SuscriptionAdapter().apply {
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
        }

        //hargebee.configure(site= "your-site",
        //                    publishableApiKey= "api_key",
        //                    sdkKey= "sdk_key",packageName = "packageName")
        //Chargebee.configure\

        binding.loginButton.setOnClickListener {
            Chargebee.configure("asdeporteapp-test.integrations",
                "test_jcdnj3WaBj6PbyWe5fWCs2isCPcdcdVxUCI",
                sdkKey = "cb-eyk6gew6mjb6bariha45dgncuq",
                packageName = "es.as.deportesapp")

            Chargebee.retrieveAllPlans(arrayOf("Mensual-Android")) {
                when (it) {
                    is ChargebeeResult.Success -> {
                        Log.i(javaClass.simpleName, "list Plans :  ${it.data}")
                    }
                    is ChargebeeResult.Error -> {
                        Log.d(javaClass.simpleName, "Error :  ${it.exp.message}")
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}

class SuscriptionAdapter : RecyclerViewAdapterBase<EventData, SuscriptionItemView>() {

    var onItemClick: ((item: EventData) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): SuscriptionItemView =
        SuscriptionItemView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<SuscriptionItemView>, position: Int) {
        val item = items[position]

        holder.view.apply {
            bind(item)
        }

        /*holder.view.setOnClickListener {
            onProfileClick?.invoke(item)
        }*/
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}

class SuscriptionItemView constructor(
    context: Context
): RelativeLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.suscription_item, this, true)
    }

    fun bind(item: EventData) {


    }
}
