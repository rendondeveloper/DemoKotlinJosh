package com.asdeporte.asdeportev2.ui.access

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentAccessBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate

class AccessFragment : Fragment() {

    private var _binding: FragmentAccessBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAccessBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.buttonFirst.setOnClickListener {
            findNavController().safelyNavigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
        binding.previus.setOnClickListener {
            requireActivity().finish()
        }

        binding.registerButton.setOnClickListener {
            findNavController().safelyNavigate(R.id.action_to_RegisterFragment)
        }

        binding.loginText.setOnClickListener {
            findNavController().safelyNavigate(R.id.action_to_LoginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}