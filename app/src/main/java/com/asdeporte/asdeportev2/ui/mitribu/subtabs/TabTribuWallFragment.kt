package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.RelativeLayout
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentTabTribuWallBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.mitribu.adapters.EventTribuHorizontalAdapter
import com.asdeporte.asdeportev2.ui.mitribu.adapters.PostsAdapter
import com.asdeporte.asdeportev2.ui.mitribu.post.NewPostPreviewSheet
import com.asdeporte.asdeportev2.ui.mitribu.post.NewPostType
import com.asdeporte.asdeportev2.ui.reusableview.home.EventBottomSheet
import com.asdeporte.asdeportev2.ui.reusableview.tribu.FilterHomePostsSheet
import com.asdeporte.asdeportev2.ui.reusableview.tribu.PostMenuOptionsSheet

class TabTribuWallFragment : Fragment(), PostMenuOptionsSheet.PostMenuOptionsSheetListener, NewPostPreviewSheet.NewPostPreviewSheetListener, FilterHomePostsSheet.FilterHomePostsSheetListener {

    private var _binding: FragmentTabTribuWallBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var postsAdapter: PostsAdapter
    private lateinit var eventsAdapter: EventTribuHorizontalAdapter

    val testEvent = EventData("123",
        "7, 14 y 21K by WomanUp",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTabTribuWallBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.openMainTribu.setOnClickListener {
            //TribuMainFragment
            findNavController().safelyNavigate(R.id.mainTribuAction)
        }

        binding.publishView.setOnClickListener {
            //findNavController().safelyNavigate(R.id.toNewPost)
        }
        binding.publishView.findViewById<RelativeLayout>(R.id.thinking_view).setOnClickListener {
            findNavController().safelyNavigate(R.id.toNewPost)
        }
        binding.publishView.findViewById<LinearLayout>(R.id.activity_view).setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("type", NewPostType.ACTIVITY)
            findNavController().safelyNavigate(R.id.toNewPostPreview, bundle)
        }
        binding.publishView.findViewById<LinearLayout>(R.id.medal_view).setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("type", NewPostType.MEDAL)
            findNavController().safelyNavigate(R.id.toNewPostPreview, bundle)
        }
        binding.publishView.findViewById<LinearLayout>(R.id.media_view).setOnClickListener {
            NewPostPreviewSheet.create(this@TabTribuWallFragment, NewPostType.MEDIA).show(requireActivity().supportFragmentManager, "EventBottomSheet")
        }

        binding.filterPosts.setOnClickListener {
            //showMenu(it, R.menu.home_menu)
            FilterHomePostsSheet.create(this@TabTribuWallFragment).show(requireActivity().supportFragmentManager, "EventBottomSheet")
        }

        Handler(Looper.getMainLooper()).postDelayed({
            initPosts()
        }, 1000)
    }

    fun initPosts() {
        val items = listOf(testEvent, testEvent, testEvent, testEvent)

        // Feed
        postsAdapter = PostsAdapter().apply {
            onItemClick = {
                //EventBottomSheet.create(this@TribuMainFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }
            onMenuClick = {
                PostMenuOptionsSheet.create(this@TabTribuWallFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }
        }

        binding.posts.adapter = postsAdapter
        binding.posts.setHasFixedSize(true)
        binding.posts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        //Handler(Looper.getMainLooper()).postDelayed({
            postsAdapter.setItems(items)
        //}, 1000)

        // Random events
        eventsAdapter = EventTribuHorizontalAdapter().apply {
            onItemClick = {
                //EventBottomSheet.create(this@TribuMainFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }
        }

        binding.events.adapter = eventsAdapter
        binding.events.setHasFixedSize(true)
        binding.events.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        //Handler(Looper.getMainLooper()).postDelayed({
            eventsAdapter.setItems(items)
        //}, 1000)

    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popup.setForceShowIcon(true)
        }
        popup.setOnMenuItemClickListener {
            // Respond to menu item click.
            return@setOnMenuItemClickListener true
        }
        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }
        // Show the popup menu.
        popup.show()
    }

    /*
     Listeners
     */
    override fun onApplyFilters() {
        //TODO("Not yet implemented")
    }
    override fun onMediaPost(item: String?) {

    }
    override fun onOptionSelected(option: String?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}