package com.asdeporte.asdeportev2.ui.mitribu.subtabs.tribus

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentTribuMainBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.adapters.PostsAdapter
import com.asdeporte.asdeportev2.ui.mitribu.subtabs.AdminTribuSheet
import com.asdeporte.asdeportev2.ui.reusableview.tribu.FilterHomePostsSheet

class TribuMainFragment : Fragment(), AdminTribuSheet.AdminTribuSheetListener, FilterHomePostsSheet.FilterHomePostsSheetListener {
    private var _binding: FragmentTribuMainBinding? = null

    private val binding get() = _binding!!

    private lateinit var postsAdapter: PostsAdapter

    val testEvent = EventData("123",
        "7, 14 y 21K by WomanUp",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTribuMainBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }

        binding.detailsTribuButton.setOnClickListener {
            findNavController().safelyNavigate(R.id.detailsTribuAction)
        }
        binding.tribuAdminButton.setOnClickListener {
            AdminTribuSheet.create(this@TribuMainFragment).show(requireActivity().supportFragmentManager, "TribuMainFragment")
        }

        binding.filterPosts.setOnClickListener {
            findNavController().safelyNavigate(R.id.action_navigation_tribu_main_to_tribeFiltersFragment)
            //FilterHomePostsSheet.create(this@TribuMainFragment).show(requireActivity().supportFragmentManager, "EventBottomSheet")
        }

        initPosts()
    }

    fun initPosts() {
        val items = listOf(testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent, testEvent)

        // Top events
        postsAdapter = PostsAdapter().apply {
            /*onItemClick = {
                EventBottomSheet.create(this@TribuMainFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }*/
        }

        binding.posts.adapter = postsAdapter
        binding.posts.setHasFixedSize(true)
        binding.posts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        Handler(Looper.getMainLooper()).postDelayed({
                postsAdapter.setItems(items)
            }, 1000 // value in milliseconds
        )
        //
    }

    /*
    Listeners
     */
    override fun onApplyFilters() {
        //TODO("Not yet implemented")
    }
    override fun onChangeAdmin() {
        //TODO("Not yet implemented")
    }
    override fun onAbandom() {
        //TODO("Not yet implemented")
    }
    override fun onDelete() {
        //TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}