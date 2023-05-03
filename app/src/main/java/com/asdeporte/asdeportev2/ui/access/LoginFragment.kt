package com.asdeporte.asdeportev2.ui.access

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentLoginBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.MainActivity

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            LoginMenuBottomSheet().show(requireActivity().supportFragmentManager, "MY_BOTTOM_SHEET")
            // nextActivity()
        }
        binding.registerButton.setOnClickListener {
            findNavController().safelyNavigate(R.id.action_to_RegisterFragment)
        }
        binding.forgotText.setOnClickListener {
            findNavController().safelyNavigate(R.id.action_to_ForgotPasswordFragment)
        }
    }

    private fun nextActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}