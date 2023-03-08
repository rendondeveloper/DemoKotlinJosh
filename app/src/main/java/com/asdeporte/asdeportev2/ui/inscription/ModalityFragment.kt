package com.asdeporte.asdeportev2.ui.inscription

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.models.StepperViewModel
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentModalityBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ModalityFragment : Fragment() {

    private var _binding: FragmentModalityBinding? = null
    private val binding get() = _binding!!

    val testEvent = EventData("123",
        "7, 14 y 21K by WomanUp",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")

    /*
    Stepper
     */
    private val viewModel: StepperViewModel by lazy { ViewModelProvider(requireActivity())[StepperViewModel::class.java] }
    private val onSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            /*binding.run {
                viewModel.updateStepper(
                    viewModel.stepperSettings.value.copy(
                        textColor = Color.rgb(
                            seekRed.progress,
                            seekGreen.progress,
                            seekBlue.progress
                        )
                    )
                )
            }*/
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}

        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentModalityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topEvent.bind(testEvent)

        binding.userOne.bind(testEvent)
        binding.userTwo.bind(testEvent)
        //binding.buttonFirst.setOnClickListener {
            //findNavController().safelyNavigate(R.id.action_FirstFragment_to_SecondFragment)
        //}

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}