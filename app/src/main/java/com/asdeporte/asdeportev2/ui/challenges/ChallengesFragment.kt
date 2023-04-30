package com.asdeporte.asdeportev2.ui.challenges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.asdeporte.asdeportev2.databinding.FragmentDashboardBinding

open class ChallengesFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: Button = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        textView.setOnClickListener {
            /*val manager = context?.packageManager
            val info = context?.packageName?.let { manager?.getPackageInfo(it, PackageManager.GET_ACTIVITIES) }
            Hermes.Builder.appVersion = info?.versionName ?: ""

            val inscription = HermesInscription()
            inscription.userid = "bb94d8ef-765b-4fcf-8638-77b2a2f72830"
            inscription.inscriptionid = "3256a91c-2b47-4a65-a9b4-d20fd68e9e8c"
            inscription.raceid = "b5594275-d958-4549-a2c8-f6b4b621379d"
            inscription.eventid = "260c0bba-1438-43dd-9b97-1ba53c532683"
            inscription.routename = "10 km"
            Hermes().launchTracking(requireContext(), inscription)*/

            // Sportmaniacs
            //triatlon-las-estacas-2022
            //val appAssets = AppAssets()
            //(activity as MainActivity).viewLiveApp?.launchRace(activity, "triatlon-las-estacas-2022", 0x55, appAssets)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}