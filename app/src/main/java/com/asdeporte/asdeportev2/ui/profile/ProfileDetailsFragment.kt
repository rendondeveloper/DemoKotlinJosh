package com.asdeporte.asdeportev2.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.BuildConfig
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentProfileDetailsBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
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

        }*/

        setupAdapter()

    }

    private fun setupAdapter() {
        profileItemAdapter = ProfileItemDetailAdapter().apply {
            onItemClick = {
                when (it.type) {
                    MoreItem.PERSONALDATA -> { findNavController().safelyNavigate(R.id.to_personal_data) }
                    MoreItem.MYDEPENDENTS -> { findNavController().safelyNavigate(R.id.to_personal_dependents) }
                    MoreItem.WALLET -> { findNavController().safelyNavigate(R.id.to_personal_wallet) }
                    else -> {
                        //TODO("Not yet implemented")
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
                "Configuración",
                "Datos Personales",
                null,
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_person),
                MoreItem.PERSONALDATA
            ),
            ProfileMenuItem(
                null,
                "Mis Dependientes",
                null,
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_persons),
                MoreItem.MYDEPENDENTS
            ),
            ProfileMenuItem(
                null,
                "Mis Metas",
                null,
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_goals),
                MoreItem.MYGOALS
            ),
            ProfileMenuItem(
                null,
                "Suscripciones",
                null,
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_history_money),
                MoreItem.SUBSCRIPTIONS
            ),
            ProfileMenuItem(
                null,
                "Método de pago y wallet",
                null,
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_wallet),
                MoreItem.WALLET
            ),
            ProfileMenuItem(
                null,
                "Facturación",
                null,
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_doc),
                MoreItem.BILLING
            ),
            ProfileMenuItem(
                null,
                "Idioma",
                null,
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_language),
                MoreItem.LANGUAGE
            ),
            ProfileMenuItem(
                null,
                "Cambiar contraseña",
                null,
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_lock),
                MoreItem.LOCK
            ),
            //SECCION ACTIVIDAD
            ProfileMenuItem(
                "Historial de actividad",
                "Mis Compras",
                "Gestiona todas tus compras",
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_shop),
                MoreItem.SHOPPING
            ),
            ProfileMenuItem(
                null,
                "Mis favoritos",
                "Encuentra tus eventos y retos favoritos",
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_favorite),
                MoreItem.FAVORITES
            ),
            //SESION
            ProfileMenuItem(
                "Sesión",
                "Eliminar cuenta",
                null,
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_delete),
                MoreItem.DELETE
            ),
            ProfileMenuItem(
                null,
                "Cerrar sesión",
                null,
                ContextCompat.getDrawable(requireContext(), R.drawable.profile_exit),
                MoreItem.SIGNOFF
            ),
            ProfileMenuItem(
                "VERSIÓN ${BuildConfig.VERSION_NAME}",
                null,
                null,
                null,
                null
            ),
        )
        profileItemAdapter.setItems(menuItems)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}