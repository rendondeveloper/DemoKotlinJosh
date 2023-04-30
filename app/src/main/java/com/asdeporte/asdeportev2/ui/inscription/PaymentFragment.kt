package com.asdeporte.asdeportev2.ui.inscription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentPaymentBinding
import com.asdeporte.asdeportev2.ui.reusableview.inscription.PaymentType
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    val testEvent = EventData("123",
        "7, 14 y 21K by WomanUp",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topEvent.bind(testEvent)
        binding.paypalPayment.setupPayment(PaymentType.PAYPAL)
        binding.storePayment.setupPayment(PaymentType.STORE)
        binding.kueskiPayment.setupPayment(PaymentType.KUESKI)
        binding.speiPayment.setupPayment(PaymentType.SPEI)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}