package com.asdeporte.asdeportev2.ui.profile.details.wallet

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.SheetWalletNewCardBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WalletNewCardSheet(private val isEdit: Boolean = false) : BottomSheetDialogFragment() {

    private var _binding: SheetWalletNewCardBinding? = null
    private val binding get() = _binding!!
    private lateinit var frontAnim:AnimatorSet
    private lateinit var backAnim: AnimatorSet
    private var isFront =true

    interface NewCardSheetListener {
        fun onCardCreated()
    }

    lateinit var listener: NewCardSheetListener

    companion object {
        fun create(listener: NewCardSheetListener) = WalletNewCardSheet().apply {
            this.listener = listener
        }
    }

    override fun onStart() {
        super.onStart()

        dialog?.let {
            val bottomSheet: View? = it.findViewById(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
            val view: View? = view
            view?.post {
                val parent: View = view.parent as View
                val params: CoordinatorLayout.LayoutParams = parent.layoutParams as CoordinatorLayout.LayoutParams
                val behavior: CoordinatorLayout.Behavior<*>? = params.behavior
                val bottomSheetBehavior: BottomSheetBehavior<*>? = behavior as? BottomSheetBehavior<*>
                bottomSheetBehavior?.peekHeight = view.measuredHeight
                (bottomSheet?.parent as? View)?.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = SheetWalletNewCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(isEdit){
            setupEditCard()
        }

        setupCard()

        binding.nameTextInput.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus && !isFront) {
                toggleCard()
            }
        }
        binding.numberTextInput.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus && !isFront) {
                toggleCard()
            }
        }
        binding.dateExpirationTextField.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus && !isFront) {
                toggleCard()
            }
        }
        binding.cvvTextInput.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus && isFront) {
                toggleCard()
            }
        }
        binding.cancelButton.setOnClickListener{
            this.dismiss()
        }
        binding.confirmButton.setOnClickListener{
            this.dismiss()
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
        isFront = if (isFront) {
            frontAnim.setTarget(binding.frontCard)
            backAnim.setTarget(binding.backCard)
            frontAnim.start()
            backAnim.start()
            false
        } else {
            frontAnim.setTarget(binding.backCard)
            backAnim.setTarget(binding.frontCard)
            backAnim.start()
            frontAnim.start()
            true
        }
    }

    private fun setupEditCard(){
        binding.txtTitle.text = "Editar tarjeta"
        binding.nameTextInput.setText("Fernando Velázquez")
        binding.numberTextInput.setText("4039 2903 2019 2394")
        binding.dateExpirationInput.setText( "12/25")
        binding.cvvTextInput.setText("***")
        binding.confirmButton.text = "Guardar"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}