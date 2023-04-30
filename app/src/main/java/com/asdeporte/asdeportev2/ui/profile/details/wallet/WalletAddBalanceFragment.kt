package com.asdeporte.asdeportev2.ui.profile.details.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentWalletAddBalanceBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.profile.adapters.CreditCardAdapter
import com.asdeporte.asdeportev2.ui.reusableview.inscription.PaymentType

class WalletAddBalanceFragment : Fragment(), WalletNewCardSheet.NewCardSheetListener {

    private var _binding: FragmentWalletAddBalanceBinding? = null
    private val binding get() = _binding!!
    private var currentCustomAmount = ""

    //CreditCardAdapter
    private lateinit var creditCardAdapter: CreditCardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWalletAddBalanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.firstAmount.setOnClickListener {
            setCustomAmount("$100")
        }
        binding.secondAmount.setOnClickListener {
            setCustomAmount("$500")
        }
        binding.thirdAmount.setOnClickListener {
            setCustomAmount("$1,000")
        }

        setupCards()

        binding.paypalView.setData(PaymentType.PAYPAL)
        binding.kueskiView.setData(PaymentType.KUESKI)
        binding.storeView.setData(PaymentType.STORE)
    }

    private fun setCustomAmount(amount: String) {
        currentCustomAmount = amount
        when (amount) {
            binding.firstAmount.text -> {
                binding.firstAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.pill_background)
                binding.firstAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.white_dynamic))
                binding.secondAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.secondary_button)
                binding.secondAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.label_primary))
                binding.thirdAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.secondary_button)
                binding.thirdAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.label_primary))
            }
            binding.secondAmount.text -> {
                binding.firstAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.secondary_button)
                binding.firstAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.label_primary))
                binding.secondAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.pill_background)
                binding.secondAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.white_dynamic))
                binding.thirdAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.secondary_button)
                binding.thirdAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.label_primary))
            }
            binding.thirdAmount.text -> {
                binding.firstAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.secondary_button)
                binding.firstAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.label_primary))
                binding.secondAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.secondary_button)
                binding.secondAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.label_primary))
                binding.thirdAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.pill_background)
                binding.thirdAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.white_dynamic))
            }
            else -> {
                binding.firstAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.secondary_button)
                binding.firstAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.label_primary))
                binding.secondAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.secondary_button)
                binding.secondAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.label_primary))
                binding.thirdAmount.background = ContextCompat.getDrawable(requireContext(), R.drawable.secondary_button)
                binding.thirdAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.label_primary))
            }
        }
    }

    private fun setupCards() {
        creditCardAdapter = CreditCardAdapter().apply {
            onItemClick = {
                //TODO("Not yet implemented")
            }
        }

        binding.cardsList.adapter = creditCardAdapter
        binding.cardsList.setHasFixedSize(true)
        binding.cardsList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val emptyEvent = EventData("", "", "", "")
        val items = listOf(emptyEvent, emptyEvent, emptyEvent, emptyEvent)
        creditCardAdapter.setItems(items)

    }

    /*
     Listeners
     */
    override fun onCardCreated() {
        //
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}