package com.asdeporte.asdeportev2.ui.mitribu

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentTribuProfileBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.adapters.PostsAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class TribuProfileFragment : Fragment() {

    private var _binding: FragmentTribuProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var postsAdapter: PostsAdapter
    val testEvent = EventData("123",
        "7, 14 y 21K by WomanUp",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTribuProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        Glide.with(this)
            .load("https://picsum.photos/600/300")
            .centerCrop()
            .apply(
                RequestOptions()
                    //.placeholder(R.drawable.placeholder)
                    //.error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true))
            .into(binding.bannerImage)


        Glide.with(this)
            .load("https://picsum.photos/150/150")
            .centerCrop()
            .apply(
                RequestOptions()
                    //.placeholder(R.drawable.placeholder)
                    //.error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true))
            .into(binding.profileImage)
        */
        initRecent()
        initPosts()
    }

    private fun initRecent() {
        binding.tribuHor1.bind(testEvent)
        binding.tribuHor2.bind(testEvent)
        binding.tribuHor3.bind(testEvent)
    }
    private fun initPosts() {
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
        }, 1000)
    }

}