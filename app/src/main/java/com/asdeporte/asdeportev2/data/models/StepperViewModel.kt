package com.asdeporte.asdeportev2.data.models

import androidx.annotation.ColorInt
import androidx.lifecycle.ViewModel
import com.aceinteract.android.stepper.StepperNavigationView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * View model for managing a stepper activity.
 */
@ExperimentalCoroutinesApi
class StepperViewModel : ViewModel() {

    private val _stepperSettings = MutableStateFlow(StepperSettings())
    /**
     * Public immutable accessor for [_stepperSettings].
     */
    val stepperSettings: StateFlow<StepperSettings> get() = _stepperSettings

    /**
     * Update the settings of the stepper navigation.
     *
     * @param newStepperSettings the settings to update.
     */
    fun updateStepper(newStepperSettings: StepperSettings) {
        _stepperSettings.value = newStepperSettings
    }
}

/**
 * Data class representing settings for a [StepperNavigationView] view
 *
 * @property iconColor the color of the icon in the view.
 * @property textColor the color of the text in the view.
 * @property textSize the size of the text in the view.
 * @property iconSize the size of the icon in the view.
 */
data class StepperSettings(
    @ColorInt var iconColor: Int = -1,
    @ColorInt var textColor: Int = -1,
    var textSize: Int = -1,
    var iconSize: Int = -1
)
