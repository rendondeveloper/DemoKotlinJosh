package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentTabTribuPrivacyBinding

class TabTribuPrivacyFragment: Fragment() {
    private var _binding: FragmentTabTribuPrivacyBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTabTribuPrivacyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewInfoProfile.setOnClickListener {
            binding.homeLayout.visibility = View.GONE
            binding.infoProfileLayout.visibility = View.VISIBLE
            binding.visibilityLayout.visibility = View.GONE
            binding.findYouLayout.visibility = View.GONE
            binding.messageRequestLayout.visibility = View.GONE
            binding.blockLayout.visibility = View.GONE
        }
        binding.toolbarProfile.setOnClickListener {
            resetVisibility()
        }

        binding.viewVisibility.setOnClickListener {
            binding.homeLayout.visibility = View.GONE
            binding.infoProfileLayout.visibility = View.GONE
            binding.visibilityLayout.visibility = View.VISIBLE
            binding.findYouLayout.visibility = View.GONE
            binding.messageRequestLayout.visibility = View.GONE
            binding.blockLayout.visibility = View.GONE
        }
        binding.toolbarVisibility.setOnClickListener {
            resetVisibility()
        }

        binding.viewFindYou.setOnClickListener {
            binding.homeLayout.visibility = View.GONE
            binding.infoProfileLayout.visibility = View.GONE
            binding.visibilityLayout.visibility = View.GONE
            binding.findYouLayout.visibility = View.VISIBLE
            binding.messageRequestLayout.visibility = View.GONE
            binding.blockLayout.visibility = View.GONE
        }
        binding.toolbarFindYou.setOnClickListener {
            resetVisibility()
        }

        binding.viewMessageRequest.setOnClickListener {
            binding.homeLayout.visibility = View.GONE
            binding.infoProfileLayout.visibility = View.GONE
            binding.visibilityLayout.visibility = View.GONE
            binding.findYouLayout.visibility = View.GONE
            binding.messageRequestLayout.visibility = View.VISIBLE
            binding.blockLayout.visibility = View.GONE
        }
        binding.toolbarMessageRequest.setOnClickListener {
            resetVisibility()
        }

        binding.viewBlock.setOnClickListener {
            binding.homeLayout.visibility = View.GONE
            binding.infoProfileLayout.visibility = View.GONE
            binding.visibilityLayout.visibility = View.GONE
            binding.findYouLayout.visibility = View.GONE
            binding.messageRequestLayout.visibility = View.GONE
            binding.blockLayout.visibility = View.VISIBLE
        }
        binding.toolbarBlock.setOnClickListener {
            resetVisibility()
        }

        resetVisibility()
        clickOptions()
    }

    private fun resetVisibility() {
        binding.homeLayout.visibility = View.VISIBLE
        binding.infoProfileLayout.visibility = View.GONE
        binding.visibilityLayout.visibility = View.GONE
        binding.findYouLayout.visibility = View.GONE
        binding.messageRequestLayout.visibility = View.GONE
        binding.blockLayout.visibility = View.GONE
    }

    private fun clickOptions() {
        binding.chooseUser.setOnClickListener {
            showMenu(binding.chooseUser, R.menu.privacy_main_menu)
        }
        binding.chooseEmail.setOnClickListener {
            showMenu(binding.chooseEmail, R.menu.privacy_main_menu)
        }
        binding.choosePhone.setOnClickListener {
            showMenu(binding.choosePhone, R.menu.privacy_main_menu)
        }
        binding.chooseBirth.setOnClickListener {
            showMenu(binding.chooseBirth, R.menu.privacy_main_menu)
        }
        binding.chooseCity.setOnClickListener {
            showMenu(binding.chooseCity, R.menu.privacy_main_menu)
        }

    }
    private fun showMenu(v: TextView, @MenuRes menuRes: Int) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popup.setForceShowIcon(true)
        }
        popup.setOnMenuItemClickListener {
            // Respond to menu item click.
            v.text = it.title
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}