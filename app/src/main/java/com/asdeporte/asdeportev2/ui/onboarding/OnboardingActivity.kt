package com.asdeporte.asdeportev2.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.ActivityOnboarding2Binding
import com.asdeporte.asdeportev2.ui.access.LoginActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboarding2Binding
    private lateinit var demoCollectionAdapter: OnboardingCollectionAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var dotsIndicator: DotsIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboarding2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.skip.setOnClickListener {
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.fab.setOnClickListener {
            if (viewPager.currentItem == 2) {
                finish()
                startActivity(Intent(this, LoginActivity::class.java))
            }
            viewPager.currentItem = viewPager.currentItem+1
        }
        demoCollectionAdapter = OnboardingCollectionAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = demoCollectionAdapter
        dotsIndicator = binding.dotsIndicator
        dotsIndicator.attachTo(viewPager)
    }

}

class OnboardingCollectionAdapter(activity: OnboardingActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    private val images = listOf(R.drawable.onboarding1, R.drawable.onboarding2, R.drawable.onboarding3)
    private val titles = listOf(activity.getString(R.string.onboarding1),
        activity.getString(R.string.onboarding2),
        activity.getString(R.string.onboarding3))

    override fun createFragment(position: Int): Fragment {
        val fragment = DemoObjectFragment()
        fragment.arguments = Bundle().apply {
            putInt("Image", images[position])
            putString("Title", titles[position])
        }
        return fragment
    }
}

class DemoObjectFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_collection_object, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey("Title") }?.apply {
            val imageView: ImageView = view.findViewById(R.id.imageBack)
            Glide.with(requireContext())
                .load(getInt("Image"))
                .apply(RequestOptions())
                .into(imageView)
            val textView: TextView = view.findViewById(R.id.text1)
            textView.text = getString("Title").toString().uppercase()
        }
    }
}