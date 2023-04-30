package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentTabTribuWallBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.mitribu.adapters.EventTribuHorizontalAdapter
import com.asdeporte.asdeportev2.ui.mitribu.adapters.PostsAdapter
import com.asdeporte.asdeportev2.ui.mitribu.adapters.PostsAdapterView
import com.asdeporte.asdeportev2.ui.mitribu.post.NewPostPreviewSheet
import com.asdeporte.asdeportev2.ui.mitribu.post.NewPostType
import com.asdeporte.asdeportev2.ui.reusableview.tribu.FilterHomePostsSheet
import com.asdeporte.asdeportev2.ui.reusableview.tribu.PostMenuOptionsSheet
import com.asdeporte.hermes.adapters.ViewWrapper

class TabTribuWallFragment : Fragment(), PostMenuOptionsSheet.PostMenuOptionsSheetListener,
    NewPostPreviewSheet.NewPostPreviewSheetListener,
    FilterHomePostsSheet.FilterHomePostsSheetListener {

    private var binding: FragmentTabTribuWallBinding? = null
    private lateinit var postsAdapter: PostsAdapter
    private lateinit var eventsAdapter: EventTribuHorizontalAdapter

    val testEvent = EventData(
        "123",
        "7, 14 y 21K by WomanUp",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80"
    )
    val testVideoEvent = EventData(
        "123",
        "7, 14 y 21K by WomanUp",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80",
        isVideo = true
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabTribuWallBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            openMainTribu.setOnClickListener {
                findNavController().safelyNavigate(R.id.mainTribuAction)
            }

            publishView.setOnClickListener {
                //findNavController().safelyNavigate(R.id.toNewPost)
            }
            publishView.findViewById<RelativeLayout>(R.id.thinking_view)?.setOnClickListener {
                findNavController().safelyNavigate(R.id.toNewPost)
            }
            publishView.findViewById<LinearLayout>(R.id.activity_view)?.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("type", NewPostType.ACTIVITY)
                findNavController().safelyNavigate(R.id.toNewPostPreview, bundle)
            }
            publishView.findViewById<LinearLayout>(R.id.medal_view)?.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("type", NewPostType.MEDAL)
                findNavController().safelyNavigate(R.id.toNewPostPreview, bundle)
            }
            publishView.findViewById<LinearLayout>(R.id.media_view)?.setOnClickListener {
                findNavController().safelyNavigate(R.id.action_navigation_tribu_to_newPostMediaFragment)
            }
            filterPosts.setOnClickListener {
                findNavController().safelyNavigate(R.id.action_navigation_tribu_to_tribeFiltersFragment)
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            initPosts()
        }, 1000)
    }

    private fun initPosts() {
        val items = listOf(
            testEvent,
            testEvent,
            testEvent,
            testEvent,
            testEvent,
            testEvent,
            testEvent
        )

        // Feed
        postsAdapter = PostsAdapter().apply {
            onItemClick = {
                //EventBottomSheet.create(this@TribuMainFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }
            onMenuClick = {
                PostMenuOptionsSheet.create(this@TabTribuWallFragment, it)
                    .show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }
        }


        binding?.posts?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    for (i in items.indices step 1) {
                        (recyclerView.findViewHolderForAdapterPosition(i) as  ViewWrapper<PostsAdapterView>).view.pause()
                    }
                }
            }

        })
        binding?.nsvT?.isSmoothScrollingEnabled

        binding?.posts?.adapter = postsAdapter
        binding?.posts?.setHasFixedSize(true)
        binding?.posts?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        //Handler(Looper.getMainLooper()).postDelayed({
        postsAdapter.setItems(items)
        //}, 1000)

        // Random events
        eventsAdapter = EventTribuHorizontalAdapter().apply {
            onItemClick = {
                //EventBottomSheet.create(this@TribuMainFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }
        }

        binding?.events?.adapter = eventsAdapter
        binding?.events?.setHasFixedSize(true)
        binding?.events?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        eventsAdapter.setItems(items)

    }

    override fun onApplyFilters() {
        //TODO("Not yet implemented")
    }

    override fun onMediaPost(item: String?) {

    }

    override fun onOptionSelected(option: String?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}