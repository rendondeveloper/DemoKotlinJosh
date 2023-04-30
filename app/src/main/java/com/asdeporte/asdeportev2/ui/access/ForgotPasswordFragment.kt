package com.asdeporte.asdeportev2.ui.access

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white_dynamic))
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.black_dynamic))
        binding.toolbar.title = getString(R.string.init_session)
        binding.toolbar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.arrow_left)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.initSesionButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.forgotButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}