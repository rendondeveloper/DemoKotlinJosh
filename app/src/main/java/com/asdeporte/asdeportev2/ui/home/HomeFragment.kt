package com.asdeporte.asdeportev2.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentHomeBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.home.adapters.EventsHorizontalAdapter
import com.asdeporte.asdeportev2.ui.home.adapters.EventsHorizontalBigAdapter
import com.asdeporte.asdeportev2.ui.inscription.InscriptionActivity
import com.asdeporte.asdeportev2.ui.reusableview.home.EventBottomSheet
import com.asdeporte.asdeportev2.ui.reusableview.home.SearchTribuView
import kotlinx.coroutines.ExperimentalCoroutinesApi

class HomeFragment : Fragment(), EventBottomSheet.EventBottomSheetListener, SearchTribuView.SearchTribuViewListener {

  private var _binding: FragmentHomeBinding? = null
  private val binding get() = _binding!!

  private lateinit var eventsHorizontalBigAdapter: EventsHorizontalBigAdapter
  private lateinit var eventsHorizontalAdapter: EventsHorizontalAdapter

  val testEvent = EventData("123",
    "7, 14 y 21K by WomanUp",
    "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
    "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root

    //binding.latestResult.setData("47")
    binding.sliderHome.setData("47")
    binding.serialsGrid.setData("47")
    //binding.nextRace.setData("")

    /*binding.latestResult.setOnClickListener {
      EventBottomSheet.create(this, testEvent).show(requireActivity().supportFragmentManager, "EventBottomSheet")
    }*/

    //binding.resumeHome.setData("47")

    //binding.galleryHome.setData("")

    setupAdapters()

    //binding.searchTribu.setData(this)

    return root
  }

  private fun setupAdapters() {
    val items = listOf(testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent)

    // Estelares
    binding.topEventsTitle.setTitle("Estelares")
    eventsHorizontalBigAdapter = EventsHorizontalBigAdapter().apply {
      onItemClick = {
        EventBottomSheet.create(this@HomeFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
      }
    }
    binding.topEvents.adapter = eventsHorizontalBigAdapter
    binding.topEvents.setHasFixedSize(true)
    binding.topEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    eventsHorizontalBigAdapter.setItems(items)

    // Eventos cerca de mi
    binding.moreEventsTitle.setTitle("Eventos cerca de mi")
    eventsHorizontalAdapter = EventsHorizontalAdapter().apply {
      onItemClick = {
        EventBottomSheet.create(this@HomeFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
      }
    }
    binding.moreEvents.adapter = eventsHorizontalAdapter
    binding.moreEvents.setHasFixedSize(true)
    binding.moreEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    eventsHorizontalAdapter.setItems(items)

    // Recomendados para mi
    binding.recomendedForMeTitle.setTitle("Recomendados para mi")
    eventsHorizontalAdapter = EventsHorizontalAdapter().apply {
      onItemClick = {
        EventBottomSheet.create(this@HomeFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
      }
    }
    binding.recomendedForMeEvents.adapter = eventsHorizontalAdapter
    binding.recomendedForMeEvents.setHasFixedSize(true)
    binding.recomendedForMeEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    eventsHorizontalAdapter.setItems(items)

    // Los 10 + populares
    binding.topTenTitle.setTitle("Los 10 + populares")
    eventsHorizontalAdapter = EventsHorizontalAdapter().apply {
      onItemClick = {
        EventBottomSheet.create(this@HomeFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
      }
    }
    binding.topTenEvents.adapter = eventsHorizontalAdapter
    binding.topTenEvents.setHasFixedSize(true)
    binding.topTenEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    eventsHorizontalAdapter.setItems(items)

    // Este fin de semana
    binding.weekendTitle.setTitle("Este fin de semana")
    eventsHorizontalAdapter = EventsHorizontalAdapter().apply {
      onItemClick = {
        EventBottomSheet.create(this@HomeFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
      }
    }
    binding.weekendEvents.adapter = eventsHorizontalAdapter
    binding.weekendEvents.setHasFixedSize(true)
    binding.weekendEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    eventsHorizontalAdapter.setItems(items)


  }

  /*
   Listeners
   */

  override fun onOpenEvent(event: String) {
    // open event
    findNavController().safelyNavigate(R.id.toEventDetailAction)
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  override fun onOpenInscription(event: String) {
    startActivity(Intent(requireContext(), InscriptionActivity::class.java))
  }

  override fun onSearch() {
  }

  override fun onFilters() {
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}