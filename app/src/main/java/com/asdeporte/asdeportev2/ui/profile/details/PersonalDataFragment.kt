package com.asdeporte.asdeportev2.ui.profile.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.databinding.FragmentPersonalDataBinding
import com.asdeporte.asdeportev2.ui.MainActivity

class PersonalDataFragment : Fragment() {

    private var _binding: FragmentPersonalDataBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        /*requireActivity().onBackPressedDispatcher.addCallback {
            println("onBackPressedDispatcher")
        }*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}