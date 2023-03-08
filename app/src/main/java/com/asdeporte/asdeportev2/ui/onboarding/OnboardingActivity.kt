package com.asdeporte.asdeportev2.ui.onboarding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.ActivityOnboarding2Binding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.access.LoginActivity
import com.google.android.material.snackbar.Snackbar
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

        val logged = false
        binding.content.visibility = View.VISIBLE
        if (logged) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            binding.content.visibility = View.VISIBLE
        }

        binding.previus.setOnClickListener {
            if (viewPager.currentItem == 1) {
                binding.previus.visibility = View.GONE
            }
            viewPager.currentItem = viewPager.currentItem-1
        }

        binding.skip.setOnClickListener {
            this.finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.fab.setOnClickListener {
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            binding.previus.visibility = View.VISIBLE
            if (viewPager.currentItem == 2) {
                this.finish()
                startActivity(Intent(this, LoginActivity::class.java))
            }
            viewPager.currentItem = viewPager.currentItem+1
        }

        demoCollectionAdapter = OnboardingCollectionAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = demoCollectionAdapter

        dotsIndicator = binding.dotsIndicator
        dotsIndicator.selectedDotColor = ContextCompat.getColor(this, R.color.asd_pink)
        dotsIndicator.attachTo(viewPager)
    }

}

class OnboardingCollectionAdapter(fragment: OnboardingActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    var images = listOf(R.drawable.onboarding1, R.drawable.onboarding2, R.drawable.onboarding3)
    var titles = listOf(fragment.getString(R.string.onboarding1),
        fragment.getString(R.string.onboarding2),
        fragment.getString(R.string.onboarding3))

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = DemoObjectFragment()

        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt("Image", images[position])
            putString("Title", titles[position])
        }
        return fragment
    }
}

// Instances of this class are fragments representing a single
// object in our collection.
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
            imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(), getInt("Image")))

            val textView: TextView = view.findViewById(R.id.text1)
            textView.text = getString("Title").toString().uppercase()
        }
    }
}