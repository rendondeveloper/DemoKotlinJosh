package com.asdeporte.asdeportev2.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Dimension
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentEventDetailBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.SuscriptionActivity
import com.asdeporte.asdeportev2.ui.home.adapters.EventsHorizontalAdapter
import com.asdeporte.asdeportev2.ui.home.adapters.EventsHorizontalBigAdapter
import com.asdeporte.asdeportev2.ui.inscription.InscriptionActivity
import com.asdeporte.asdeportev2.ui.notifications.adapter.NotificationsTopStatusView
import com.asdeporte.asdeportev2.ui.reusableview.convocatory.BenefitEventView
import com.asdeporte.asdeportev2.ui.reusableview.convocatory.ServicesInEventView
import com.asdeporte.asdeportev2.ui.reusableview.convocatory.TitleAndDescripctionView
import com.asdeporte.asdeportev2.ui.reusableview.home.SearchTribuView
import com.asdeporte.asdeportev2.ui.reusableview.profile.BenefitsView
import com.asdeporte.asdeportev2.utils.dpToPx
import com.asdeporte.asdeportev2.utils.pxToDp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.ExperimentalCoroutinesApi

class EventDetailFragment : Fragment(), SearchTribuView.SearchTribuViewListener {
    private var _binding: FragmentEventDetailBinding? = null
    private val binding get() = _binding!!

    val testEvent = EventData("123",
        "7, 14 y 21K by WomanUp",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEventDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()

        Glide.with(this)
            .load("https://picsum.photos/600/300")
            .centerCrop()
            .into(binding.bannerEvent)

        var requestOptions = RequestOptions()
            .placeholder(ContextCompat.getDrawable(requireContext(), R.drawable.placeholder_img))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(dpToPx(5)))
        Glide.with(this)
            .load("https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2022%2F5%2Fimg_1655144427045_TUP-Marquesa-22-logo-nvo-jun-13.JPG")
            .centerCrop()
            .apply(requestOptions)
            .into(binding.eventLogo)

        val titles = listOf("Distancias y Categorías", "Inscripciones y Precios", "Beneficios Plus", "Ruta", "Programa del Evento", "Entrega de Kit", "Hospedaje y Turismo")
        binding.tabView.setTabs(titles)

        binding.inscriptionButton.setOnClickListener {
            startActivity(Intent(requireContext(), InscriptionActivity::class.java))
        }

        setupContent()
        binding.footerView.setData("Notas importantes", "• La edad mínima para participar es de 15 años cumplidos al 31 de diciembre del 2022.\n" +
                "• La categoría será determinada por la edad que tenga el competidor al 31 de diciembre del 2022.\n" +
                "• Se premiará con trofeo a los 3 primeros lugares INDIVIDUALES de la carrera PRESENCIAL, en cada distancia, como GANADORES ABSOLUTOS, en las ramas femenil y varonil, de acuerdo al TIEMPO OFICIAL de competencia.\n" +
                "• Esto es, los 3 primeros hombres y las 3 primeras mujeres que crucen la meta en cada distancia y de manera INDIVIDUAL.\n" +
                "• La carrera outdoor no tendrá premiación.")
    }

    private fun setupContent() {
        binding.contentView.removeAllViews()

        val view1 = TitleAndDescripctionView(requireContext())
        view1.setData("¡La noche es tuya!", "Corre en esta increíble carrera nocturna en Santa Fe, donde pondrás a prueba tus límites disfrutando de la vista entre el equilibrio de la naturaleza y la bella arquitectura de gigantes edificios.")
        binding.contentView.addView(view1)

        val view2 = BenefitEventView(requireContext())
        binding.contentView.addView(view2)
        val view3 = BenefitEventView(requireContext())
        binding.contentView.addView(view3)

        val view4 = ServicesInEventView(requireContext())
        view4.setData("", "")
        binding.contentView.addView(view4)

        val view5 = TitleAndDescripctionView(requireContext())
        view5.setData("¡La noche es tuya!", "Corre en esta increíble carrera nocturna en Santa Fe, donde pondrás a prueba tus límites disfrutando de la vista entre el equilibrio de la naturaleza y la bella arquitectura de gigantes edificios.")
        binding.contentView.addView(view5)

        //val item2 = BenefitsView(requireContext())
        //binding.contentView.addView(item2)

    }
    /*
     Listeners
     */

    override fun onSearch() {
        println("onSearch")
    }

    override fun onFilters() {
        println("onFilters")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}