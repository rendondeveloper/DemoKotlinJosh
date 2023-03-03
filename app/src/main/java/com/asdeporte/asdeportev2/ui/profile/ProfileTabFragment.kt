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
import com.asdeporte.asdeportev2.ui.SuscriptionActivity
import com.asdeporte.asdeportev2.ui.profile.adapters.ProfileItemHomeAdapter

enum class MoreItem {
    EXPERIENCES,
    CHALLENGES,
    MYPLACES,
    NUTRITION,
    BLOG,
    MYCAUSE,
    FAQ,
    CONFIRMATIONLETTER,
    CONTACT,
    // Details
    HISTORIC,
    PERSONALDATA,
    MYDEPENDENTS,
    BENEFITS,
    MYGOALS,
    WALLET,
    SUBSCRIPTIONS,
    BILLING,
    SHOPPING,
    SIGNOFF
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

        binding.subscribeButton.setOnClickListener {
            startActivity(Intent(requireActivity(), SuscriptionActivity::class.java))
        }

        binding.profileView.setOnClickListener {
            findNavController().navigate(R.id.detailProfileAction)
        }

        setupAdapter()

    }

    private fun setupAdapter() {
        profileItemAdapter = ProfileItemHomeAdapter().apply {
            onItemClick = {
                //EventBottomSheet.create(this@ProfileTabFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
                when (it.type) {
                    //MoreItem.HISTORIC -> { findNavController().navigate(R.id.to_personal_history) }
                    //MoreItem.PERSONALDATA -> { findNavController().navigate(R.id.to_personal_data) }
                    else -> {
                        println("do nothing")
                    }
                }
            }
        }

        binding.itemList.adapter = profileItemAdapter
        binding.itemList.setHasFixedSize(true)
        binding.itemList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        val menuItems = listOf(
            ProfileMenuItem("Experiencias", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_tent), MoreItem.EXPERIENCES),
            ProfileMenuItem("Retos", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_challenge), MoreItem.CHALLENGES),
            ProfileMenuItem("Mis lugares", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_place), MoreItem.MYPLACES),
            ProfileMenuItem("Nutrición", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_apple), MoreItem.NUTRITION),
            ProfileMenuItem("Blog", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_blog), MoreItem.BLOG),
            ProfileMenuItem("Mi causa", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_cause), MoreItem.MYCAUSE),
            ProfileMenuItem("Preguntas frecuentes", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_question), MoreItem.FAQ),
            ProfileMenuItem("Recupera tu carta confirmación", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_letter), MoreItem.CONFIRMATIONLETTER),
            ProfileMenuItem("Contacto", null, ContextCompat.getDrawable(requireContext(), R.drawable.menu_phone), MoreItem.CONTACT),
        )
        profileItemAdapter.setItems(menuItems)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

data class ProfileMenuItem(
    val title: String,
    val subtitle: String?,
    val icon: Drawable?,
    val type: MoreItem?
)