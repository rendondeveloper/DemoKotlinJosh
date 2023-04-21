package com.asdeporte.asdeportev2.ui.profile.adapters.collectionsimport android.os.Bundleimport android.view.LayoutInflaterimport android.view.Viewimport android.view.ViewGroupimport android.widget.ImageViewimport android.widget.LinearLayoutimport android.widget.TextViewimport androidx.core.content.ContextCompatimport androidx.fragment.app.Fragmentimport androidx.viewpager2.adapter.FragmentStateAdapterimport com.asdeporte.asdeportev2.Rimport com.asdeporte.asdeportev2.ui.profile.adapters.badget.BadgeModelimport com.asdeporte.asdeportev2.ui.profile.adapters.bottomSheet.BadgeShareSheetclass BadgePagerCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {    val listEvents = listOf(            BadgeModel(                    year = "2022",                    nameShort = "Medio Maratón por el Amor y la Paz 2023",                    state = "CIUDAD DE MÉXICO",                    date = "12/MARZO/2022",                    fullName = "Medio Maratón por el Amor y la Paz 2023",                    position = "1 de 1200",                    time = "00:18:00",                    imageResource = R.drawable.badge_next_dummy            ),            BadgeModel(                    year = "2022",                    nameShort = "Medio Maratón por el Amor y la Paz 2023",                    state = "CIUDAD DE MÉXICO",                    date = "12/MARZO/2022",                    fullName = "Medio Maratón por el Amor y la Paz 2023",                    position = "1 de 1200",                    time = "00:18:00",                    imageResource = R.drawable.badge_next_dummy            ),            BadgeModel(                    year = "2022",                    nameShort = "Medio Maratón por el Amor y la Paz 2023",                    state = "CIUDAD DE MÉXICO",                    date = "12/MARZO/2022",                    fullName = "Medio Maratón por el Amor y la Paz 2023",                    position = "1 de 1200",                    time = "00:18:00",                    imageResource = R.drawable.badge_next_dummy            ),    )    override fun getItemCount(): Int = listEvents.size    override fun createFragment(position: Int): Fragment {        val fragment = BadgeObjectFragment()        val item = listEvents[position]        fragment.arguments = Bundle().apply {            putInt("image", item.imageResource ?: 0)            putString("state", item.state)            putString("date", item.date)            putString("fullName", item.fullName)            putString("position", item.position)            putString("time", item.time)        }        return fragment    }}class BadgeObjectFragment : Fragment(), BadgeShareSheet.EventBottomSheetListenerBadgeShare{    override fun onCreateView(            inflater: LayoutInflater,            container: ViewGroup?,            savedInstanceState: Bundle?    ): View {        return inflater.inflate(R.layout.badge_pager_info_item, container, false)    }    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {        val imageView: ImageView = view.findViewById(R.id.badge_image)        imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(),arguments?.getInt("image") ?: 0))        view.findViewById<TextView?>(R.id.textCity).apply {            text = arguments?.getString("state")        }        view.findViewById<TextView?>(R.id.textDate).apply {            text = arguments?.getString("date")        }        view.findViewById<TextView?>(R.id.textEvent).apply {            text = arguments?.getString("fullName")        }        view.findViewById<TextView?>(R.id.textViewPositionData).apply {            text = arguments?.getString("position")        }        view.findViewById<TextView?>(R.id.textViewTimeData).apply {            text = arguments?.getString("time")        }        view.findViewById<LinearLayout>(R.id.linearLayout12).apply {            setOnClickListener{                BadgeShareSheet.create(this@BadgeObjectFragment).show(requireActivity().supportFragmentManager, "EventBottomSheet")            }        }    }    override fun onOpenEventShare(event: String) {    }}