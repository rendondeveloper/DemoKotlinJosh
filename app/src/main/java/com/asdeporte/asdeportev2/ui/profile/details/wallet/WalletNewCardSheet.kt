package com.asdeporte.asdeportev2.ui.profile.details.wallet

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.app.AlertDialog
import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.SheetWalletNewCardBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WalletNewCardSheet : BottomSheetDialogFragment() {

    private var _binding: SheetWalletNewCardBinding? = null
    private val binding get() = _binding!!

    lateinit var frontAnim:AnimatorSet
    lateinit var backAnim: AnimatorSet
    var isFront =true

    interface NewCardSheetListener {
        fun onCardCreated()
    }

    lateinit var listener: NewCardSheetListener

    companion object {
        fun create(listener: NewCardSheetListener) = WalletNewCardSheet().apply {
            this.listener = listener
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = SheetWalletNewCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCard()

        binding.nameTextInput.setOnFocusChangeListener { v, hasFocus ->
            println("year $hasFocus")
            if (hasFocus && !isFront) {
                toggleCard()
            }
        }
        binding.numberTextInput.setOnFocusChangeListener { v, hasFocus ->
            println("year $hasFocus")
            if (hasFocus && !isFront) {
                toggleCard()
            }
        }
        binding.monthTextInput.setOnFocusChangeListener { v, hasFocus ->
            println("year $hasFocus")
            if (hasFocus && !isFront) {
                toggleCard()
            }
        }
        binding.yearTextInput.setOnFocusChangeListener { v, hasFocus ->
            println("year $hasFocus")
            if (hasFocus && !isFront) {
                toggleCard()
            }
        }
        binding.cvvTextInput.setOnFocusChangeListener { v, hasFocus ->
            println("vcc $hasFocus")
            if (hasFocus && isFront) {
                toggleCard()
            }
        }
    }

    private fun setupCard() {
        val scale = Resources.getSystem().displayMetrics.density

        val front = binding.frontCard
        val back = binding.backCard

        front.cameraDistance = 8000 * scale
        back.cameraDistance = 8000 * scale

        // Now we will set the front animation
        frontAnim = AnimatorInflater.loadAnimator(context, R.animator.front_card_animator) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(context, R.animator.back_card_animator) as AnimatorSet

    }
    private fun toggleCard() {
        if (isFront) {
            frontAnim.setTarget(binding.frontCard)
            backAnim.setTarget(binding.backCard)
            frontAnim.start()
            backAnim.start()
            isFront = false
        } else {
            frontAnim.setTarget(binding.backCard)
            backAnim.setTarget(binding.frontCard)
            backAnim.start()
            frontAnim.start()
            isFront = true
        }
    }

    /*
     Listeners
     */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}