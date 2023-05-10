package com.asdeporte.asdeportev2.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.allViews
import androidx.fragment.app.Fragment
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentEventDetailBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategoryMainNotesModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategoryMainSectionModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategoryModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategoryNotesModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategorySectionModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.plusMembership.PlusMembershipBenefitModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.plusMembership.PlusMembershipModel
import com.asdeporte.asdeportev2.ui.inscription.InscriptionActivity
import com.asdeporte.asdeportev2.ui.reusableview.convocatory.TitleAndDescripctionView
import com.asdeporte.asdeportev2.ui.reusableview.home.SearchTribuView
import com.asdeporte.asdeportev2.utils.dpToPx
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.ExperimentalCoroutinesApi

class EventDetailFragment : Fragment(), SearchTribuView.SearchTribuViewListener {
    private var _binding: FragmentEventDetailBinding? = null
    private val binding get() = _binding!!

    private val plusMembershipData = listOf(
            PlusMembershipModel(
                    R.drawable.ic_clock,
                    "FILA EXPRESS",
                    "Viernes 16 de septiembre",
                    "Quedan 10 reservas",
                    "Reserver",
                    listOf(
                            PlusMembershipBenefitModel("No hagas filas"),
                            PlusMembershipBenefitModel("Tendremos tu talla de playera asegurada"),
                            PlusMembershipBenefitModel("Recoge tu kit en un módulo exclusivo para miembros Plus"),
                    )
            ),
            PlusMembershipModel(
                    R.drawable.ic_clock,
                    "mismo día",
                    "Sábado 17 de septiembre",
                    "",
                    "Reserver",
                    listOf(
                            PlusMembershipBenefitModel("Tendremos tu talla de playera asegurada"),
                    )
            ),
            PlusMembershipModel(
                    R.drawable.ic_clock,
                    "recupera el costo de tu inscripción",
                    "",
                    "",
                    "Solicitar abono a Wallet",
                    listOf(
                            PlusMembershipBenefitModel("Sin costo por servicio"),
                            PlusMembershipBenefitModel("Hasta 7 días antes de la entrega de paquetes"),
                    )
            )
    )

    private val distanceAndCategoryData = DistanceAndCategoryModel(
            sections = listOf(
                    DistanceAndCategoryMainSectionModel(
                            title = "DISTANCIA",
                            list = listOf(
                                    DistanceAndCategorySectionModel(
                                            title = "Presencial",
                                            description = "Corre en Santa Fe, Cuajimalpa, Ciudad de México, el sábado 17 de septiembre en la noche 19:00 hr. La carrera PRESENCIAL las podrás hacer de manera INDIVIDUAL o en RELEVOS.",
                                            showTable = true,
                                    ),
                                    DistanceAndCategorySectionModel(
                                            title = "Virtual outdoor",
                                            description = "Corre en Santa Fe, Cuajimalpa, Ciudad de México, el sábado 17 de septiembre en la noche 19:00 hr. La carrera PRESENCIAL las podrás hacer de manera INDIVIDUAL o en RELEVOS.",
                                            showTable = false,
                                    ))
                    ),
                    DistanceAndCategoryMainSectionModel(
                            title = "CATEGORÍAS",
                            list = listOf(
                                    DistanceAndCategorySectionModel(
                                            title = "Presencial",
                                            description = "Corre en Santa Fe, Cuajimalpa, Ciudad de México, el sábado 17 de septiembre en la noche 19:00 hr. La carrera PRESENCIAL las podrás hacer de manera INDIVIDUAL o en RELEVOS.",
                                            showTable = true,
                                    ),
                                    DistanceAndCategorySectionModel(
                                            title = "Virtual outdoor",
                                            description = "Corre en Santa Fe, Cuajimalpa, Ciudad de México, el sábado 17 de septiembre en la noche 19:00 hr. La carrera PRESENCIAL las podrás hacer de manera INDIVIDUAL o en RELEVOS.",
                                            showTable = false,
                                    ))
                    )
            ),
            notes = listOf(
                    DistanceAndCategoryMainNotesModel(
                            title = "Notas importantes",
                            list = listOf(
                                    DistanceAndCategoryNotesModel(
                                            "La edad mínima para participar es de 15 años cumplidos al 31 de diciembre del 2022."
                                    ),
                                    DistanceAndCategoryNotesModel(
                                            " La categoría será determinada por la edad que tenga el competidor al 31 de diciembre del 2022."
                                    ),
                                    DistanceAndCategoryNotesModel(
                                            "Se premiará con trofeo a los 3 primeros lugares INDIVIDUALES de la carrera PRESENCIAL, en cada distancia, como GANADORES ABSOLUTOS, en las ramas femenil y varonil, de acuerdo al TIEMPO OFICIAL de competencia."
                                    ),
                                    DistanceAndCategoryNotesModel(
                                            "Esto es, los 3 primeros hombres y las 3 primeras mujeres que crucen la meta en cada distancia y de manera INDIVIDUAL."
                                    ),
                                    DistanceAndCategoryNotesModel(
                                            "La carrera outdoor no tendrá premiación."
                                    )
                            )
                    )
            ),

            )

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

        val titles = listOf("Distancias y Categorías", "Inscripciones y Precios", "Beneficios Plus", "Hospedaje y Turismo", "Ruta", "Programa del Evento", "Entrega de Kit")
        binding.tabViews.setTabs(titles)
        binding.tabViews.setCallback { tab ->
            binding.contentView.visibility = View.GONE
            binding.plusMembership.visibility = View.GONE
            binding.eventProgram.visibility = View.GONE
            binding.kitDelivery.visibility = View.GONE
            binding.route.visibility = View.GONE
            binding.distanceAndCategory.visibility = View.GONE
            when (tab) {
                "Beneficios Plus" -> {
                    binding.plusMembership.setData(plusMembershipData)
                    binding.plusMembership.visibility = View.VISIBLE
                }

                "Programa del Evento" -> {
                    binding.eventProgram.visibility = View.VISIBLE
                }

                "Ruta" -> {
                    binding.route.visibility = View.VISIBLE
                    binding.route.setDataGraphic()
                }

                "Entrega de Kit" -> {
                    binding.kitDelivery.visibility = View.VISIBLE
                    binding.kitDelivery.setData(plusMembershipData)
                }

                "Distancias y Categorías" -> {
                    binding.distanceAndCategory.visibility = View.VISIBLE
                    binding.distanceAndCategory.setData(distanceAndCategoryData)
                }

                else -> {
                    binding.contentView.visibility = View.VISIBLE
                }
            }
        }

        binding.inscriptionButton.setOnClickListener {
            startActivity(Intent(requireContext(), InscriptionActivity::class.java))
        }

        binding.tabViews.setFirstStep()

        //setupContent()
        /*
        binding.footerView.setData("Notas importantes", "• La edad mínima para participar es de 15 años cumplidos al 31 de diciembre del 2022.\n" +
                "• La categoría será determinada por la edad que tenga el competidor al 31 de diciembre del 2022.\n" +
                "• Se premiará con trofeo a los 3 primeros lugares INDIVIDUALES de la carrera PRESENCIAL, en cada distancia, como GANADORES ABSOLUTOS, en las ramas femenil y varonil, de acuerdo al TIEMPO OFICIAL de competencia.\n" +
                "• Esto es, los 3 primeros hombres y las 3 primeras mujeres que crucen la meta en cada distancia y de manera INDIVIDUAL.\n" +
                "• La carrera outdoor no tendrá premiación.")

         */
    }

    private fun setupContent() {
        binding.contentView.removeAllViews()

        val view1 = TitleAndDescripctionView(requireContext())
        view1.setData("¡La noche es tuya!", "Corre en esta increíble carrera nocturna en Santa Fe, donde pondrás a prueba tus límites disfrutando de la vista entre el equilibrio de la naturaleza y la bella arquitectura de gigantes edificios.")
        binding.contentView.addView(view1)

        val tabview = binding.tabViews.allViews.find { it == it.findViewById(R.id.tab_view) }
        /*
        tabview?.allViews?.let { views ->
            for(view in views) {
                val tabContainer = view.allViews.find { it == it.findViewById(R.id.tab_container) }
                tabContainer?.setOnClickListener{
                    if(it.tag == 1){
                        binding.contentView.removeAllViews()
                        val view2 = BenefitEventView(requireContext())
                        binding.contentView.addView(view2)
                        val view3 = BenefitEventView(requireContext())
                        binding.contentView.addView(view3)
                    }
                }
            }
        }
         */


        /*
        val currentTab = tabview?.allViews?.find { it.tag == binding.tabViews.currentTab}
        currentTab?.setOnClickListener{
            if(it.tag == 2){
                binding.contentView.removeAllViews()
                val view2 = BenefitEventView(requireContext())
                binding.contentView.addView(view2)
                val view3 = BenefitEventView(requireContext())
                binding.contentView.addView(view3)
            }
        }
         */
        /*
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
         */
        //val item2 = BenefitsView(requireContext())
        //binding.contentView.addView(item2)

    }
    /*
     Listeners
     */

    override fun onSearch() {
    }

    override fun onFilters() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}