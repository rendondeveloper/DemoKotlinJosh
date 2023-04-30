package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asdeporte.asdeportev2.databinding.FragmentTabTribuPrivacyBinding

class TabTribuPrivacyFragment : Fragment() {
    private var _binding: FragmentTabTribuPrivacyBinding? = null
    private lateinit var privacyMenuBottomSheet: PrivacyMenuBottomSheet
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabTribuPrivacyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        privacyMenuBottomSheet = PrivacyMenuBottomSheet()

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
            privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
        }
        binding.chooseEmail.setOnClickListener {
            privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
        }
        binding.choosePhone.setOnClickListener {
            privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
        }
        binding.chooseBirth.setOnClickListener {
            privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
        }
        binding.chooseCity.setOnClickListener {
            privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
        }

        binding.apply {
            tvYourShares.setOnClickListener {
                privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
            }
            tvYourEvents.setOnClickListener {
                privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
            }
            tvMyResults.setOnClickListener {
                privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
            }
            tvMyGoals.setOnClickListener {
                privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
            }
            tvMyPosts.setOnClickListener {
                privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
            }
            tvMyFuturePosts.setOnClickListener {
                privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
            }

            tvRequestsFriends.setOnClickListener {
                privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
            }
            tvListFriends.setOnClickListener {
                privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
            }
            tvListTribes.setOnClickListener {
                privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
            }
            tvWhoCanFindYou.setOnClickListener {
                privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
            }

            tvFriends.setOnClickListener {
                privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
            }
            tvFriendsTribe.setOnClickListener {
                privacyMenuBottomSheet.show(parentFragmentManager, "MY_BOTTOM_SHEET")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}