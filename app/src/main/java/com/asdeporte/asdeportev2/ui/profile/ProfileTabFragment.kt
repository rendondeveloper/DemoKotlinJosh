package com.asdeporte.asdeportev2.ui.profile

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentProfileTabBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.SuscriptionActivity
import com.asdeporte.asdeportev2.ui.profile.adapters.ProfileItemHomeAdapter

enum class MoreItem {
    EXPERIENCES,
    CHALLENGES,
    TRANING,
    PHOTOS,
    TUTORIALS,
    FEEDBACK,
    NUTRITION,
    BLOG,
    FAQ,
    TERMS,
    PRIVACY,
    ABOUT,
    CONTACT,
    // Details
    HISTORIC,
    BADGES,
    PERSONALDATA,
    MYDEPENDENTS,
    BENEFITS,
    MYGOALS,
    WALLET,
    SUBSCRIPTIONS,
    BILLING,
    SHOPPING,
    SIGNOFF,
    LANGUAGE,
    LOCK,
    DELETE,
    FAVORITES
}
class ProfileTabFragment : Fragment() {

    private var _binding: FragmentProfileTabBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var profileItemAdapter: ProfileItemHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileTabBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()

        binding.subscribeButton.setOnClickListener {
            startActivity(Intent(requireActivity(), SuscriptionActivity::class.java))
        }

        binding.profileView.setOnClickListener {
            findNavController().safelyNavigate(R.id.detailProfileAction)
        }

        setupAdapter()

    }

    private fun setupAdapter() {
        profileItemAdapter = ProfileItemHomeAdapter().apply {
            onItemClick = {
                //EventBottomSheet.create(this@ProfileTabFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
                when (it.type) {
                    MoreItem.HISTORIC -> { findNavController().safelyNavigate(R.id.personal_history) }
                    MoreItem.BENEFITS -> { findNavController().safelyNavigate(R.id.personal_my_benefits) }
                    MoreItem.BADGES -> { findNavController().navigate(R.id.to_badge_fragment) }
                    MoreItem.PHOTOS -> { findNavController().navigate(R.id.personalPhotosFragment) }
                    else -> {
                        //TODO("Not yet implemented")
                    }
                }
            }
        }

        binding.itemList.adapter = profileItemAdapter
        binding.itemList.setHasFixedSize(true)
        binding.itemList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        val menuItems = listOf(
            ProfileMenuItem(null,"Experiencias", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_tent), MoreItem.EXPERIENCES),
            ProfileMenuItem(null,"Retos", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_challenge), MoreItem.CHALLENGES),
            ProfileMenuItem(null,"Nutrición", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_nutrition), MoreItem.NUTRITION),
            ProfileMenuItem(null,"Entrenamientos", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_training), MoreItem.TRANING),
            ProfileMenuItem(null,"Blog", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_blog), MoreItem.BLOG),
            //SECCION DATA
            ProfileMenuItem("Data","Histórico", null, ContextCompat.getDrawable(requireContext(), R.drawable.profile_graph), MoreItem.HISTORIC),
            ProfileMenuItem(null,"Medallero", null, ContextCompat.getDrawable(requireContext(), R.drawable.ic_badge), MoreItem.BADGES),
            ProfileMenuItem(null,"Fotos", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_photos), MoreItem.PHOTOS),
            ProfileMenuItem(null,"Mis Beneficios", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_benefits), MoreItem.BENEFITS),
            //SECCION SOPORTE
            ProfileMenuItem("Soporte","Tutoriales", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_tutorials), MoreItem.TUTORIALS),
            ProfileMenuItem(null,"Preguntas frecuentes", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_question), MoreItem.FAQ),
            ProfileMenuItem(null,"Contáctanos", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_phone), MoreItem.CONTACT),
            ProfileMenuItem(null,"Feedback sobre la app", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_feedback), MoreItem.FEEDBACK),
            //SECCION ASDEPORTE
            ProfileMenuItem("Asdeporte","Sobre Asdeporte", null, ContextCompat.getDrawable(requireContext(), R.drawable.logo_asd_black), MoreItem.ABOUT),
            ProfileMenuItem(null,"Términos y condiciones", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_terms), MoreItem.TERMS),
            ProfileMenuItem(null,"Aviso de privacidad", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_privacy), MoreItem.PRIVACY),

        )
        profileItemAdapter.setItems(menuItems)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

data class ProfileMenuItem(
    val section: String?,
    val title: String?,
    val subtitle: String?,
    val icon: Drawable?,
    val type: MoreItem?
)