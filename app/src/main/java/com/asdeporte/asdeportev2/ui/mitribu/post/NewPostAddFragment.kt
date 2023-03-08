package com.asdeporte.asdeportev2.ui.mitribu.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentNewPostAddBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.adapters.NewPostActivityAdapter
import com.asdeporte.asdeportev2.ui.mitribu.adapters.NewPostMedalAdapter


enum class NewPostType {
    TEXT,
    ACTIVITY,
    MEDAL,
    MEDIA
}

class NewPostAddFragment : Fragment(), NewPostPreviewSheet.NewPostPreviewSheetListener {

    private lateinit var activityAdapter: NewPostActivityAdapter
    private lateinit var medalAdapter: NewPostMedalAdapter

    val testEvent = EventData("123",
        "7, 14 y 21K by WomanUp",
        "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
        "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80")

    private lateinit var binding: FragmentNewPostAddBinding

    private var fromNewPost: Boolean = false
    lateinit var type: NewPostType

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNewPostAddBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()

        binding.backImage.setOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }

        fromNewPost = arguments?.getBoolean("fromNewPost") ?: false
        type = arguments?.getSerializable("type") as NewPostType

        initPosts()
    }

    fun initPosts() {
        val items = listOf(testEvent, testEvent, testEvent, testEvent, testEvent, testEvent)

        when (type) {
            NewPostType.TEXT -> {

            }
            NewPostType.ACTIVITY -> {
                binding.titleToolbar.text = "Tus actividades recientes"
                activityAdapter = NewPostActivityAdapter().apply {
                    onPreviewClick = {
                        NewPostPreviewSheet.create(this@NewPostAddFragment, type).show(requireActivity().supportFragmentManager, "EventBottomSheet")
                    }
                    onPostClick = {
                        (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
                        if (fromNewPost) {
                            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
                        }
                    }
                }

                binding.activitys.adapter = activityAdapter
                binding.activitys.setHasFixedSize(true)
                binding.activitys.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

                activityAdapter.setItems(items)
            }
            NewPostType.MEDAL -> {
                binding.titleToolbar.text = "Tus medallas recientes"
                medalAdapter = NewPostMedalAdapter().apply {
                    onPreviewClick = {
                        NewPostPreviewSheet.create(this@NewPostAddFragment, type).show(requireActivity().supportFragmentManager, "EventBottomSheet")
                    }
                    onPostClick = {
                        (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
                        if (fromNewPost) {
                            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
                        }
                    }
                }

                binding.activitys.adapter = medalAdapter
                binding.activitys.setHasFixedSize(true)
                binding.activitys.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

                medalAdapter.setItems(items)
            }
            NewPostType.MEDIA -> {
                binding.titleToolbar.text = ""
            }
        }


    }

    override fun onMediaPost(item: String?) {

    }

}