package com.asdeporte.asdeportev2.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentCreateGroupBinding
import com.asdeporte.asdeportev2.databinding.FragmentNotificationsBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.notifications.adapter.*
import com.asdeporte.asdeportev2.ui.profile.adapters.dialog.BadgeDetailDialog
import com.asdeporte.asdeportev2.ui.reusableview.home.EventBottomSheet
import com.asdeporte.asdeportev2.ui.reusableview.home.SearchTribuView
import com.google.android.material.tabs.TabLayout

class NotificationsFragment : Fragment(), EventBottomSheet.EventBottomSheetListener, SearchTribuView.SearchTribuViewListener {

    private var binding: FragmentNotificationsBinding? = null
    val testEvent = EventData("123",
        "7, 14 y 21K by WomanUp",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding?.toolbar?.title = "Notificaciones"
        binding?.toolbar?.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.navigation_back)
        binding?.toolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding?.updatesScroll?.visibility = View.VISIBLE
        binding?.messagesScroll?.visibility = View.GONE

        binding?.tabView?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) {
                    binding?.updatesScroll?.visibility = View.VISIBLE
                    binding?.messagesScroll?.visibility = View.GONE
                } else {
                    binding?.updatesScroll?.visibility = View.GONE
                    binding?.messagesScroll?.visibility = View.VISIBLE
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
        /*
        binding.tabView.getTabAt(0)?.orCreateBadge?.number = 5
        binding.tabView.getTabAt(0)?.orCreateBadge?.backgroundColor = ContextCompat.getColor(requireContext(), R.color.orange_as_light)
        binding.tabView.getTabAt(1)?.orCreateBadge?.number = 2
        binding.tabView.getTabAt(1)?.orCreateBadge?.backgroundColor = ContextCompat.getColor(requireContext(), R.color.orange_as_light)
         */
        val typefaceSemiBold = ResourcesCompat.getFont(requireContext(), R.font.kanit_semibold)
        val typefaceRegular = ResourcesCompat.getFont(requireContext(), R.font.kanit_regular)
        binding?.singleButton?.setTextColor(resources.getColor(R.color.orange_as_light))
        binding?.singleButton?.setOnClickListener {
            binding?.singleButton?.setTextColor(resources.getColor(R.color.orange_as_light))
            binding?.groupButton?.setTextColor(resources.getColor(R.color.black))
            binding?.singleButton?.typeface = typefaceSemiBold
            binding?.groupButton?.typeface = typefaceRegular
            binding?.createGroupButton?.visibility = View.GONE
            binding?.messagesView?.visibility = View.VISIBLE
            binding?.groupView?.visibility = View.GONE
            binding?.groupViewSuggested?.visibility = View.GONE
            binding?.txtTitle?.visibility = View.GONE
            binding?.divider?.visibility = View.GONE
        }
        binding?.groupButton?.setOnClickListener {
            binding?.singleButton?.setTextColor(resources.getColor(R.color.black))
            binding?.groupButton?.setTextColor(resources.getColor(R.color.orange_as_light))
            binding?.singleButton?.typeface = typefaceRegular
            binding?.groupButton?.typeface = typefaceSemiBold
            binding?.createGroupButton?.visibility = View.VISIBLE
            binding?.messagesView?.visibility = View.GONE
            binding?.groupView?.visibility = View.VISIBLE
            binding?.groupViewSuggested?.visibility = View.VISIBLE
            binding?.txtTitle?.visibility = View.VISIBLE
            binding?.divider?.visibility = View.VISIBLE
        }
        binding?.createGroupButton?.setOnClickListener{
            findNavController().safelyNavigate(R.id.createGroupFragment)
        }
        setupAdapters()
    }

    private fun setupAdapters() {
        val items = listOf(testEvent, testEvent,testEvent,testEvent,testEvent,testEvent,testEvent)
        val itemsSuggested = listOf(testEvent, testEvent)

        for ((index, item) in items.withIndex()) {
            when (index) {
                0 -> {
                    val holder = NotificationsTopStatusView(requireContext())
                    holder.bind(item)
                    binding?.messagesLinear?.addView(holder)
                }
                3 -> {
                    val holder = UpdateUserRequestView(requireContext())
                    holder.bind(item)
                    binding?.messagesLinear?.addView(holder)
                }
                4 -> {
                    val holder = NotificationsTopStatusView(requireContext())
                    holder.bind(item, true)
                    binding?.messagesLinear?.addView(holder)
                }
                else -> {
                    val holder = UpdateUserView(requireContext())
                    holder.bind(item)
                    holder.setOnClickListener {
                        findNavController().safelyNavigate(R.id.toChat)
                    }
                    binding?.messagesLinear?.addView(holder)
                }
            }
        }

        for ((index, item) in itemsSuggested.withIndex()) {
            val holder = MessageCellView(requireContext())
            holder.bind(item)
            holder.setOnClickListener {
                findNavController().safelyNavigate(R.id.toChat)
            }
            binding?.messagesView?.addView(holder)
        }

        for ((index, item) in itemsSuggested.withIndex()) {
            val holder = MessageGroupCellView(requireContext(),false)
            holder.bind(item)
            holder.setOnClickListener {
                //findNavController().safelyNavigate(R.id.toChat)
            }
            binding?.groupView?.addView(holder)
        }

        for ((index, item) in itemsSuggested.withIndex()) {
            val holder = MessageGroupCellView(requireContext())
            holder.bind(item)
            binding?.groupViewSuggested?.addView(holder)
        }

    }

    fun filterAlert() {
        val alert = BadgeDetailDialog().apply {
            onConfirmClick = {

            }
        }
        alert.show(requireActivity().supportFragmentManager, this.tag)
    }

    /*
     Listeners
     */

    override fun onOpenEvent(event: String) {
        // open video
    }

    override fun onOpenInscription(event: String) {
        //TODO("Not yet implemented")
    }

    override fun onSearch() {
        //TODO("Not yet implemented")
    }

    override fun onFilters() {
        //TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}