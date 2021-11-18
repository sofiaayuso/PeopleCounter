package com.example.peoplecounter

import androidx.lifecycle.ViewModel
import java.util.function.BooleanSupplier

class MainViewModel: ViewModel() {

    // Counter variables
    var totalCounter = 0
    var peopleCounter = 0

    // Color
    var peopleCounterColor = R.color.purple_700

    // Visibility
    var btnSubtractVisibility: Boolean = false

    init{
        resetCounters()
    }

    /** Methods for buttons presses **/

    fun onIncrementClicked() {
        peopleCounter++
        totalCounter++
        updatePeopleCounterColor()
        updateBtnSubtractVisibility()
    }

    fun onDecrementClicked() {
        peopleCounter--
        updatePeopleCounterColor()
        updateBtnSubtractVisibility()
    }

    fun onResetClicked() {
        resetCounters()
        updatePeopleCounterColor()
        updateBtnSubtractVisibility()
    }

    private fun resetCounters() {
        totalCounter = 0
        peopleCounter = 0
    }

    private fun updatePeopleCounterColor() {
        peopleCounterColor = if (peopleCounter <= 15) R.color.purple_700 else android.graphics.Color.RED
    }

    private fun updateBtnSubtractVisibility() {
        btnSubtractVisibility = peopleCounter != 0
    }


}