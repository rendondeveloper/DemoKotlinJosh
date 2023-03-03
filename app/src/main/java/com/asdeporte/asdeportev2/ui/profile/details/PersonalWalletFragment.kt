package com.asdeporte.asdeportev2.ui.profile.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentPersonalWalletBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.profile.adapters.CreditCardAdapter
import com.asdeporte.asdeportev2.ui.profile.details.wallet.WalletNewCardSheet

class PersonalWalletFragment : Fragment(), WalletNewCardSheet.NewCardSheetListener {

    private var _binding: FragmentPersonalWalletBinding? = null
    private val binding get() = _binding!!

    //CreditCardAdapter
    private lateinit var creditCardAdapter: CreditCardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPersonalWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        setupCards()

        binding.addCard.setOnClickListener {
            WalletNewCardSheet.create(this).show(requireActivity().supportFragmentManager, "PersonalWalletFragment")
        }
        binding.addBalance.setOnClickListener {
            findNavController().navigate(R.id.toAddBalance)
        }
    }

    private fun setupCards() {
        creditCardAdapter = CreditCardAdapter().apply {
            onItemClick = {
                println("do nothing")
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