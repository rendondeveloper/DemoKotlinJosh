package com.asdeporte.asdeportev2.ui.access

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.RetrofitHelper
import com.asdeporte.asdeportev2.data.api.UserApi
import com.asdeporte.asdeportev2.data.bodies.LoginRequestDTO
import com.asdeporte.asdeportev2.data.calls.UserCall
import com.asdeporte.asdeportev2.databinding.FragmentForgotPasswordBinding
import com.asdeporte.asdeportev2.utils.AnimationsUtils
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.util.*

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

        //toolbarTitle.text = getString(R.string.profile_gallery)

        /*toolbarBtnBack.setOnClickListener {
            (activity as? MainActivity)?.onBackPressed()
        }*/
        binding.forgotButton.setOnClickListener {
            (activity as LoginActivity).showLoading()
            makeForgot()
        }
    }

    /* Data */
    private fun onGetUser(response: UserCall.GeneralResult) {
        if (response.success == true) {
            //nextActivity()
            binding.forgotView.visibility = View.GONE
            binding.forgotSuccessView.visibility = View.VISIBLE
            //AnimationsUtils.fadeOutAndGone(requireContext(), binding.forgotView, 700L)
            //AnimationsUtils.fadeInAndVisible(requireContext(), binding.forgotSuccessView, 1000L)
        } else {
            binding.forgotView.visibility = View.GONE
            binding.forgotSuccessView.visibility = View.VISIBLE
            //AnimationsUtils.fadeOutAndGone(requireContext(), binding.forgotView, 700L)
            //AnimationsUtils.fadeInAndVisible(requireContext(), binding.forgotSuccessView, 1000L)
            /*requireActivity().runOnUiThread {
                Toast.makeText(
                    requireActivity(),
                    response.message,
                    Toast.LENGTH_LONG
                ).show()
            }*/
        }
    }

    /* Services */
    @OptIn(DelicateCoroutinesApi::class)
    private fun makeForgot() {

        val body = LoginRequestDTO()
        body.email = binding.emailTextInput.text.toString()

        val quotesApi = RetrofitHelper.getInstance().create(UserApi::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = quotesApi.forgotPassword(Locale.getDefault().isO3Language, binding.emailTextInput.text.toString())
                withContext(Dispatchers.Main) {
                    //(activity as LoginActivity).hideLoading()
                    if (response.isSuccessful) {
                        onGetUser(UserCall.GeneralResult(true, response.body()?.data?.message))
                    } else {
                        val error = RetrofitHelper.getMessageException(response.errorBody()?.charStream()?.readText())
                        onGetUser(UserCall.GeneralResult(false, error.message))
                    }
                }
            } catch (e: HttpException) {
                onGetUser(UserCall.GeneralResult(false, e.message()))
            } catch (e: Throwable) {
                onGetUser(UserCall.GeneralResult(false, e.localizedMessage))
            }
        }

    }

}