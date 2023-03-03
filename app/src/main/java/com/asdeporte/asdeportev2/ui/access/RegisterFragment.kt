package com.asdeporte.asdeportev2.ui.access

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentAccessBinding
import com.asdeporte.asdeportev2.databinding.FragmentRegisterBinding
import com.asdeporte.asdeportev2.utils.getStringDate
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var currentStep = 0

    private var datePicker: MaterialDatePicker<Long>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white_dynamic))
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.black_dynamic))
        binding.toolbar.title = "Registro"
        binding.toolbar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.arrow_left)
        binding.toolbar.setNavigationOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        currentStep = 0
        binding.firstStep.visibility = View.VISIBLE
        binding.secondStep.visibility = View.GONE

        binding.nextButton.setOnClickListener {
            currentStep += 1
            if (currentStep == 0) {
                binding.titleText.text = getString(R.string.register_create)
                binding.firstStep.visibility = View.VISIBLE
                binding.secondStep.visibility = View.GONE
                binding.nextButton.text = "Siguiente"
            } else if (currentStep == 1) {
                        binding.titleText.text = getString(R.string.register_create_end_it)
                binding.firstStep.visibility = View.GONE
                binding.secondStep.visibility = View.VISIBLE
                binding.nextButton.text = "Finalizar"
            }
        }

        val genderAdapter = ArrayAdapter<String>(requireContext(),
            R.layout.list_item, resources.getStringArray(R.array.profile_gender_values))
        binding.genderTextInput.setAdapter(genderAdapter)

        binding.birthTextInput.setOnClickListener {
            println("birthTextInput")
            initDatePicker()
        }

        //val feelings = resources.getStringArray(R.array.profile_level_values)
        val countries = arrayListOf("España", "México", "USA")
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item, countries)
        binding.countryTextInput.setAdapter(arrayAdapter)
        binding.stateTextInput.setAdapter(arrayAdapter)

    }

    private fun initDatePicker() {
        val constraintsBuilder =
            CalendarConstraints.Builder()
                .setValidator(DateValidatorPointBackward.now())

        datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select birthdate")
            .setCalendarConstraints(constraintsBuilder.build())
            .build()

        datePicker?.show(requireActivity().supportFragmentManager, this.tag)

        datePicker?.addOnPositiveButtonClickListener {
            val utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            utc.timeInMillis = it
            //+1: The first month of the year in the Gregorian and Julian calendars is JANUARY which is 0
            //val month = utc.get(Calendar.MONTH)+1
            val monthName = utc.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
            val stringData = "${utc.get(Calendar.DAY_OF_MONTH)}/${monthName}/${utc.get(Calendar.YEAR)}"
            binding.birthTextInput.setText(stringData)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}