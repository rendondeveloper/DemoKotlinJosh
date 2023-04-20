package com.asdeporte.asdeportev2.ui.access

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.RetrofitHelper
import com.asdeporte.asdeportev2.data.api.UserApi
import com.asdeporte.asdeportev2.data.bodies.LoginRequestDTO
import com.asdeporte.asdeportev2.data.calls.UserCall
import com.asdeporte.asdeportev2.databinding.FragmentLoginBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.utils.SharedPreferencesAsd
//import com.sportmaniac.view_live.view.ActivityMap
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.util.*


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
            //(activity as LoginActivity).showLoading()
            //makeLogin()
            nextActivity()
        }

        binding.registerButton.setOnClickListener {
            findNavController().safelyNavigate(R.id.action_to_RegisterFragment)
        }

        binding.forgotText.setOnClickListener {
            findNavController().safelyNavigate(R.id.action_to_ForgotPasswordFragment)
        }

    }

    private fun nextActivity() {
        //requireActivity().runOnUiThread {
            requireActivity().run {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        //}
    }

    /* Data */
    private fun onGetUser(user: UserCall.UserResult) {
        user.user?.let {
            SharedPreferencesAsd.saveUserId(requireContext(), it.userid)
            nextActivity()
        } ?: run {
            //user.message
            requireActivity().runOnUiThread {
                Toast.makeText(
                    requireActivity(),
                    user.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    /* Services */
    @OptIn(DelicateCoroutinesApi::class)
    private fun makeLogin() {

        val body = LoginRequestDTO()
        body.email = binding.emailTextInput.text.toString()
        body.password = binding.passwordTextInput.text.toString()

        val quotesApi = RetrofitHelper.getInstance().create(UserApi::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = quotesApi.makeLogin(Locale.getDefault().isO3Language, body)
                withContext(Dispatchers.Main) {
                    //(activity as LoginActivity).hideLoading()
                    if (response.isSuccessful) {
                        onGetUser(UserCall.UserResult(response.body()?.data, null))
                    } else {
                        val error = RetrofitHelper.getMessageException(response.errorBody()?.charStream()?.readText())
                        onGetUser(UserCall.UserResult(null, error.message))
                    }
                }
            } catch (e: HttpException) {
                onGetUser(UserCall.UserResult(null, e.message()))
            } catch (e: Throwable) {
                onGetUser(UserCall.UserResult(null, e.localizedMessage))
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}