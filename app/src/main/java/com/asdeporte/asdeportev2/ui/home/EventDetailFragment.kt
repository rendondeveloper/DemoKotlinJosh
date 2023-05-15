package com.asdeporte.asdeportev2.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.allViews
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentEventDetailBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategoryMainNotesModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategoryMainSectionModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategoryModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategoryNotesModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.distanceAndCategory.DistanceAndCategorySectionModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.EventServicesItemModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.EventServicesModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.InscriptionAndPriceModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.InscriptionWayItemModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.InscriptionWayModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.KitModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.PayItemModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.PayModel
import com.asdeporte.asdeportev2.ui.home.models.tabs.inscriptionAndPrice.PriceInscriptionModel
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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.ExperimentalCoroutinesApi


class EventDetailFragment : Fragment(), SearchTribuView.SearchTribuViewListener, OnMapReadyCallback {
    private var _binding: FragmentEventDetailBinding? = null
    private val binding get() = _binding!!


    private val inscriptionAndPriceModel = InscriptionAndPriceModel(
            kit = listOf(
                    KitModel(
                            R.drawable.ic_dummy_t_shirt,
                            "Playera"
                    ),
                    KitModel(
                            R.drawable.ic_dummy_bag,
                            "Morral"
                    ),
                    KitModel(
                            R.drawable.ic_dummy_numbers,
                            "Número de competidor"
                    ),
                    KitModel(
                            R.drawable.ic_dummy_clock,
                            "Cronometraje con chip"
                    )
            ),
            eventServices = EventServicesModel(
                    footer = R.string.event_services_footer,
                    list = listOf(
                            EventServicesItemModel(
                                    title = "Hidratación",
                                    subTitle = "Durante la ruta",
                                    image = R.drawable.ic_dummy_bottle
                            ),
                            EventServicesItemModel(
                                    title = "Zona de recuperación",
                                    subTitle = "Fruta e hidratación",
                                    image = R.drawable.ic_dummy_avatar
                            ),
                            EventServicesItemModel(
                                    title = "Guardaropa",
                                    subTitle = "Día de la competencia",
                                    image = R.drawable.ic_dummy_lockers
                            ),
                            EventServicesItemModel(
                                    title = "Fotos",
                                    subTitle = "Sin costo extra",
                                    image = R.drawable.ic_dummy_camera
                            ),
                            EventServicesItemModel(
                                    title = "Livetracking",
                                    subTitle = "Seguimiento de tu competencia",
                                    image = R.drawable.ic_dummy_location
                            ))
            ),
            inscriptionPrice = listOf(
                    PriceInscriptionModel(
                            title = R.string.inscription_price_text_one
                    ),
                    PriceInscriptionModel(
                            title = R.string.inscription_price_text_one
                    ),
                    PriceInscriptionModel(
                            title = R.string.inscription_price_text_one
                    ),
                    PriceInscriptionModel(
                            title = R.string.inscription_price_text_one
                    )),
            inscriptionWay = InscriptionWayModel(
                    footer = R.string.inscription_way_footer,
                    list = listOf(InscriptionWayItemModel(
                            image = R.drawable.ic_dummy_run_individual,
                            title = "Individual",
                            subTitle = "Solo selecciona tu distancia y listo \n" +
                                    "¡Nos veremos en la línea de salida!"),
                            InscriptionWayItemModel(
                                    image = R.drawable.ic_dummy_run_group,
                                    title = "Relevos de 16k o 24k",
                                    subTitle = "Solo debe inscribirse UNA persona por equipo, el costo del equipo dependerá de la cantidad de integrantes (2 o 3)"
                            )
                    )
            ),
            sendHome = listOf(InscriptionWayItemModel(
                    image = R.drawable.ic_dummy_run_individual,
                    suTitleRes = R.string.send_home_description
            )),
            payTwo = PayModel(
                    footer = "*Las inscripciones EN LÍNEA son más costo por servicio.",
                    list = listOf(
                            PayItemModel(
                                    image = R.drawable.ic_dummy_innova
                            ),
                            PayItemModel(
                                    image = R.drawable.ic_dummy_invictus
                            )
                    )
            ),
            payOne = PayModel(
                    list = listOf(
                            PayItemModel(
                                image = R.drawable.i_dummy_credit_card,
                                title = "Tarjeta de crédito o débito"
                            ),
                            PayItemModel(
                                image = R.drawable.ic_dummy_pay_pal
                            ),
                            PayItemModel(
                                image = R.drawable.ic_dummy_kueski_pay
                            ),
                            PayItemModel(
                                image = R.drawable.id_dummy_transfer,
                                title = "Transferencia bancaria"
                            ),
                            PayItemModel(
                                image = R.drawable.ic_dummy_oxxo,
                                title = "Tiendas de conveniencia"
                            )
                    )
            ),
            notes = DistanceAndCategoryMainNotesModel(
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
    )


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

    private var viewFrom: String = ""

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

        initArguments()

        binding.route.getMapView().onCreate(savedInstanceState)
        binding.route.getMapView().getMapAsync(this)

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

        binding.inscriptionButton.setOnClickListener {
            startActivity(Intent(requireContext(), InscriptionActivity::class.java))
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        //setupContent()
        /*
        binding.footerView.setData("Notas importantes", "• La edad mínima para participar es de 15 años cumplidos al 31 de diciembre del 2022.\n" +
                "• La categoría será determinada por la edad que tenga el competidor al 31 de diciembre del 2022.\n" +
                "• Se premiará con trofeo a los 3 primeros lugares INDIVIDUALES de la carrera PRESENCIAL, en cada distancia, como GANADORES ABSOLUTOS, en las ramas femenil y varonil, de acuerdo al TIEMPO OFICIAL de competencia.\n" +
                "• Esto es, los 3 primeros hombres y las 3 primeras mujeres que crucen la meta en cada distancia y de manera INDIVIDUAL.\n" +
                "• La carrera outdoor no tendrá premiación.")

         */
    }

    override fun onResume() {
        super.onResume()
        binding.route.getMapView().onResume()
    }

    private fun initArguments() {
        viewFrom = arguments?.getString("viewFrom") ?: ""
        showViewFrom(viewFrom)
    }

    private fun showViewFrom(viewFrom: String) {
        when(viewFrom) {
            "MyNextCompetition" -> {
                binding.llStopwatchCard.visibility = View.VISIBLE
                binding.inscriptionView.visibility = View.GONE
                binding.anotherInscriptionView.visibility = View.VISIBLE
                val titles = listOf("Detalles de inscripción", "Hospedaje y Turismo", "Ruta", "Programa del evento", "Beneficios plus", "Distancias y categorías", "Inscripciones y precios")
                binding.tabViews.setTabs(titles)
                binding.tabViews.setCallback { tab ->
                    binding.contentView.visibility = View.GONE
                    binding.tourism.visibility = View.GONE
                    binding.route.visibility = View.GONE
                    binding.eventProgram.visibility = View.GONE
                    binding.plusMembership.visibility = View.GONE
                    binding.distanceAndCategory.visibility = View.GONE
                    binding.inscriptionAndPrice.visibility = View.GONE
                    binding.inscriptionDetails.visibility = View.GONE
                    when (tab) {
                        "Detalles de inscripción" -> {
                            binding.anotherInscriptionView.visibility = View.VISIBLE
                            binding.inscriptionDetails.visibility = View.VISIBLE
                            binding.inscriptionDetails.setData(plusMembershipData)
                        }
                        "Hospedaje y Turismo" -> {
                            binding.anotherInscriptionView.visibility = View.VISIBLE
                            binding.tourism.visibility = View.VISIBLE
                            binding.tourism.setData()
                        }
                        "Ruta" -> {
                            binding.anotherInscriptionView.visibility = View.VISIBLE
                            binding.route.visibility = View.VISIBLE
                            binding.route.setDataGraphic()
                        }
                        "Programa del evento" -> {
                            binding.anotherInscriptionView.visibility = View.VISIBLE
                            binding.eventProgram.visibility = View.VISIBLE
                        }
                        "Beneficios plus" -> {
                            binding.anotherInscriptionView.visibility = View.VISIBLE
                            binding.plusMembership.setData(plusMembershipData)
                            binding.plusMembership.visibility = View.VISIBLE
                        }
                        "Distancias y categorías" -> {
                            binding.anotherInscriptionView.visibility = View.VISIBLE
                            binding.distanceAndCategory.visibility = View.VISIBLE
                            binding.distanceAndCategory.setData(distanceAndCategoryData)
                        }
                        "Inscripciones y precios" -> {
                            binding.anotherInscriptionView.visibility = View.VISIBLE
                            binding.inscriptionAndPrice.visibility = View.VISIBLE
                            binding.inscriptionAndPrice.setData(inscriptionAndPriceModel)
                        }
                        else -> {
                            binding.anotherInscriptionView.visibility = View.GONE
                        }
                    }
                }
                binding.tabViews.setFirstStep()
            }
            else -> {
                binding.llStopwatchCard.visibility = View.GONE
                binding.inscriptionView.visibility = View.VISIBLE
                binding.anotherInscriptionView.visibility = View.GONE
                val titles = listOf("Distancias y Categorías", "Inscripciones y Precios", "Beneficios Plus", "Hospedaje y Turismo", "Ruta", "Programa del Evento", "Entrega de Kit")
                binding.tabViews.setTabs(titles)
                binding.tabViews.setCallback { tab ->
                    binding.contentView.visibility = View.GONE
                    binding.distanceAndCategory.visibility = View.GONE
                    binding.plusMembership.visibility = View.GONE
                    binding.tourism.visibility = View.GONE
                    binding.route.visibility = View.GONE
                    binding.eventProgram.visibility = View.GONE
                    binding.kitDelivery.visibility = View.GONE
                    binding.distanceAndCategory.visibility = View.GONE
                    binding.inscriptionAndPrice.visibility = View.GONE
                    when (tab) {
                        "Inscripciones y Precios" -> {
                            binding.inscriptionAndPrice.visibility = View.VISIBLE
                            binding.inscriptionAndPrice.setData(inscriptionAndPriceModel)
                        }
                        "Distancias y Categorías" -> {
                            binding.distanceAndCategory.visibility = View.VISIBLE
                            binding.distanceAndCategory.setData(distanceAndCategoryData)
                        }
                        "Beneficios Plus" -> {
                            binding.plusMembership.setData(plusMembershipData)
                            binding.plusMembership.visibility = View.VISIBLE
                        }
                        "Hospedaje y Turismo" -> {
                            binding.tourism.visibility = View.VISIBLE
                            binding.tourism.setData()
                        }
                        "Ruta" -> {
                            binding.route.visibility = View.VISIBLE
                            binding.route.setDataGraphic()
                        }
                        "Programa del Evento" -> {
                            binding.eventProgram.visibility = View.VISIBLE
                        }
                        "Entrega de Kit" -> {
                            binding.kitDelivery.visibility = View.VISIBLE
                            binding.kitDelivery.setData(plusMembershipData)
                        }
                        else -> {
                            binding.contentView.visibility = View.VISIBLE
                        }
                    }
                }
                binding.tabViews.setFirstStep()
            }
        }
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

    override fun onMapReady(p0: GoogleMap) {
        p0.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-34.0, 151.0), 10f))
    }
}