package com.asdeporte.asdeportev2.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentHomeBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.home.adapters.EventsHorizontalAdapter
import com.asdeporte.asdeportev2.ui.home.adapters.EventsHorizontalBigAdapter
import com.asdeporte.asdeportev2.ui.home.adapters.serial.SerialAdapter
import com.asdeporte.asdeportev2.ui.home.adapters.serial.SerialModel
import com.asdeporte.asdeportev2.ui.inscription.InscriptionActivity
import com.asdeporte.asdeportev2.ui.reusableview.PartnersGridView
import com.asdeporte.asdeportev2.ui.reusableview.home.EventBottomSheet
import com.asdeporte.asdeportev2.ui.reusableview.home.SearchTribuView
import com.bumptech.glide.Glide
import kotlinx.coroutines.ExperimentalCoroutinesApi

class HomeFragment : Fragment(), EventBottomSheet.EventBottomSheetListener, SearchTribuView.SearchTribuViewListener,
  PartnersGridView.PartnersGridViewListener {

  private var binding: FragmentHomeBinding? = null
  private lateinit var eventsHorizontalBigAdapter: EventsHorizontalBigAdapter
  private lateinit var eventsHorizontalAdapter: EventsHorizontalAdapter
  private lateinit var serialAdapter: SerialAdapter
  private val testEvent = EventData("123",
    "7, 14 y 21K by WomanUp",
    "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
    "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")
  private val testSerial = SerialModel(imageResource = R.drawable.serial_dummy)
  private val items = listOf(testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent)
  private val itemsSeriales = listOf(testSerial, testSerial, testSerial, testSerial, testSerial, testSerial, testSerial, testSerial, testSerial, testSerial, testSerial, testSerial, testSerial, testSerial, testSerial)
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentHomeBinding.inflate(inflater, container, false)
    return binding?.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding?.sliderHome?.setData("47")
    binding?.latestResult?.setOnClickListener {
      EventBottomSheet.create(this, testEvent).show(requireActivity().supportFragmentManager, "EventBottomSheet")
    }
    binding?.resumeHome?.setData("47")
    binding?.planPlusView?.setData()
    binding?.myTribe?.setData(this)
    setupAdapters()
  }

  private fun setupAdapters() {

    // Seriales
    serialAdapter = SerialAdapter().apply {
      onItemClick = {
      }
    }
    binding?.serialsGrid?.adapter = serialAdapter
    binding?.serialsGrid?.setHasFixedSize(true)
    binding?.serialsGrid?.layoutManager = GridLayoutManager(context, 3)
    binding?.serialsGrid?.isNestedScrollingEnabled = false
    serialAdapter.setItems(itemsSeriales)
    binding?.apply {
      Glide.with(requireContext())
        .load(R.drawable.proximos_serial_dummy)
        .centerInside()
        .into(imgNext2)
    }

    //Partner
    binding?.partnersGrid?.setData(this)

    // Estelares
    binding?.topEventsTitle?.setTitle("Estelares")
    eventsHorizontalBigAdapter = EventsHorizontalBigAdapter().apply {
      onItemClick = {
        EventBottomSheet.create(this@HomeFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
      }
    }
    binding?.topEvents?.adapter = eventsHorizontalBigAdapter
    binding?.topEvents?.setHasFixedSize(true)
    binding?.topEvents?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    eventsHorizontalBigAdapter.setItems(items)

    // Eventos cerca de mi
    binding?.moreEventsTitle?.setTitle("Eventos cerca de mi")
    eventsHorizontalAdapter = EventsHorizontalAdapter().apply {
      onItemClick = {
        EventBottomSheet.create(this@HomeFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
      }
    }
    binding?.moreEvents?.adapter = eventsHorizontalAdapter
    binding?.moreEvents?.setHasFixedSize(true)
    binding?.moreEvents?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    eventsHorizontalAdapter.setItems(items)

    // Recomendados para mi
    binding?.recomendedForMeTitle?.setTitle("Recomendados para mi")
    eventsHorizontalAdapter = EventsHorizontalAdapter().apply {
      onItemClick = {
        EventBottomSheet.create(this@HomeFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
      }
    }
    binding?.recomendedForMeEvents?.adapter = eventsHorizontalAdapter
    binding?.recomendedForMeEvents?.setHasFixedSize(true)
    binding?.recomendedForMeEvents?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    eventsHorizontalAdapter.setItems(items)

    // Los 10 + populares
    binding?.topTenTitle?.setTitle("Los 10 + populares")
    eventsHorizontalAdapter = EventsHorizontalAdapter().apply {
      onItemClick = {
        EventBottomSheet.create(this@HomeFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
      }
    }
    binding?.topTenEvents?.adapter = eventsHorizontalBigAdapter
    binding?.topTenEvents?.setHasFixedSize(true)
    binding?.topTenEvents?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    eventsHorizontalBigAdapter.setItems(items)

    // Experiencias y retos
    binding?.experiencesTitle?.setTitle("Experiencias y Retos")
    eventsHorizontalAdapter = EventsHorizontalAdapter().apply {
      onItemClick = {
        EventBottomSheet.create(this@HomeFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
      }
    }
    binding?.experiencesEvents?.adapter = eventsHorizontalAdapter
    binding?.experiencesEvents?.setHasFixedSize(true)
    binding?.experiencesEvents?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    eventsHorizontalAdapter.setItems(items)

    // Este fin de semana
    binding?.weekendTitle?.setTitle("Este fin de semana")
    eventsHorizontalAdapter = EventsHorizontalAdapter().apply {
      onItemClick = {
        EventBottomSheet.create(this@HomeFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
      }
    }
    binding?.weekendEvents?.adapter = eventsHorizontalAdapter
    binding?.weekendEvents?.setHasFixedSize(true)
    binding?.weekendEvents?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    eventsHorizontalAdapter.setItems(items)

  }

  override fun onOpenEvent(event: String) {
    findNavController().safelyNavigate(R.id.toEventDetailAction)
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  override fun onOpenInscription(event: String) {
    startActivity(Intent(requireContext(), InscriptionActivity::class.java))
  }

  override fun onSearch() {
    TODO("Not yet implemented")
  }

  override fun onFilters() {
    TODO("Not yet implemented")
  }

  override fun onDestroyView() {
    super.onDestroyView()
    binding = null
  }

  override fun onItemSelected() {
    TODO("Not yet implemented")
  }
}