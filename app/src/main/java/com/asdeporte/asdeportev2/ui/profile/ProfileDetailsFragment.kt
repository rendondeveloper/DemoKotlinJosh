package com.asdeporte.asdeportev2.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentProfileDetailsBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.profile.adapters.ProfileItemDetailAdapter

class ProfileDetailsFragment : Fragment() {

    private var _binding: FragmentProfileDetailsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var profileItemAdapter: ProfileItemDetailAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        /*binding.subscribeButton.setOnClickListener {
            startActivity(Intent(requireActivity(), SuscriptionActivity::class.java))
        }*/
        /*requireActivity().onBackPressedDispatcher.addCallback {
            println("onBackPressedDispatcher")
        }*/

        setupAdapter()

    }

    private fun setupAdapter() {
        profileItemAdapter = ProfileItemDetailAdapter().apply {
            onItemClick = {
                when (it.type) {
                    MoreItem.HISTORIC -> { findNavController().navigate(R.id.to_personal_history) }
                    MoreItem.PERSONALDATA -> { findNavController().navigate(R.id.to_personal_data) }
                    MoreItem.MYDEPENDENTS -> { findNavController().navigate(R.id.to_personal_dependents) }
                    MoreItem.BENEFITS -> { findNavController().navigate(R.id.to_personal_my_benefits) }
                    MoreItem.WALLET -> { findNavController().navigate(R.id.to_personal_wallet) }
                    else -> {
                        println("do nothing")
                    }
                }
            }
        }

        binding.itemList.adapter = profileItemAdapter
        binding.itemList.setHasFixedSize(true)
        binding.itemList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        val menuItems = listOf(
            ProfileMenuItem(
                "Mi Histórico",
                "Encuentra toda la información sobre tus competencias",
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_graph),
                MoreItem.HISTORIC
            ),
            ProfileMenuItem(
                "Datos Personales",
                "Gestiona tus datos personales",
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_person),
                MoreItem.PERSONALDATA
            ),
            ProfileMenuItem(
                "Mis Dependientes",
                "Gestiona tus dependientes y permisos",
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_persons),
                MoreItem.MYDEPENDENTS
            ),
            ProfileMenuItem(
                "Beneficios",
                "Todo lo que Asdeporte tiene para ti",
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_star),
                MoreItem.BENEFITS
            ),
            ProfileMenuItem(
                "Mis Metas",
                "No te rindas y fíjate metas más altas",
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_goals),
                MoreItem.MYGOALS
            ),
            ProfileMenuItem(
                "Wallet",
                "Administra tus metodos de pago",
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_wallet),
                MoreItem.WALLET
            ),
            ProfileMenuItem(
                "Suscripciones",
                "Gestiona tus suscripciones",
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_history_money),
                MoreItem.SUBSCRIPTIONS
            ),
            ProfileMenuItem(
                "Facturación",
                "Tus facturas siempre a la mano",
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_doc),
                MoreItem.BILLING
            ),
            ProfileMenuItem(
                "Mis Compras",
                "Lorem ipsum",
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_shop),
                MoreItem.SHOPPING
            ),
            ProfileMenuItem(
                "Cerrar sesión",
                null,
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_exit),
                MoreItem.SIGNOFF
            ),
        )
        profileItemAdapter.setItems(menuItems)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}