package com.asdeporte.asdeportev2.ui.profile.details.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.BadgeFilterBottomSheetBinding
import com.asdeporte.asdeportev2.databinding.DeviceIntegrationBottomSheetBinding
import com.asdeporte.asdeportev2.ui.profile.adapters.badget.BadgeAdapter
import com.asdeporte.asdeportev2.ui.profile.adapters.badget.BadgeModel
import com.asdeporte.asdeportev2.ui.profile.adapters.dialog.BadgeDetailDialog
import com.asdeporte.asdeportev2.ui.profile.details.bottomSheet.adapter.DeviceIntegrationBottomSheetAdapter
import com.asdeporte.asdeportev2.ui.profile.details.bottomSheet.model.DeviceIntegrationModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DeviceIntegrationBottomSheet : BottomSheetDialogFragment() {

    private lateinit var deviceIntegrationBottomSheetAdapter : DeviceIntegrationBottomSheetAdapter

    private lateinit var binding: DeviceIntegrationBottomSheetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DeviceIntegrationBottomSheetBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deviceIntegrationBottomSheetAdapter = DeviceIntegrationBottomSheetAdapter().apply {
            onItemClick = {}
        }

        binding.rvDevices.adapter = deviceIntegrationBottomSheetAdapter
        binding.rvDevices.setHasFixedSize(true)
        binding.rvDevices.layoutManager =  LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val deviceIntegrationModel = DeviceIntegrationModel("Polar Flow", "Polar Flow allows you to analyze sports activity and fitness and is used with GPS-enabled heart rate, fitness devices and activity trackers from Polar.* Track your training and activity and instantly achievements. You can view all your training and activity data on your phone on the go and wirelessly sync it to the Polar Flow service.\n" +
                "\n" +
                "Una vez vinculada con un dispositivo Garmin compatible(1), el seguimiento de la actividad es solo el principio de todo lo que Garmin Connect™ puede hacer por ti. Podrás crear nuevas sesiones de entrenamiento, confeccionar trayectos e incluso desafiar a tus amigos en competiciones, todo ello directamente en la aplicación.",
                true, R.drawable.ic_dummy_device)
        val items = listOf(deviceIntegrationModel, deviceIntegrationModel.copy(isConnect = false), deviceIntegrationModel.copy(isConnect = false))
        deviceIntegrationBottomSheetAdapter.setItems(items)

    }
}