package com.example.peoplecounter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.peoplecounter.databinding.ActivityMainBinding

const val SAVED_TOTAL_COUNTER = "savedTotalCounter"
const val SAVED_PEOPLE_COUNTER = "savedPeopleCounter"
const val SAVED_PEOPLE_COUNTER_COLOR = "overCapacityIndicator"
const val SAVED_SUB_BUTTON_VISIBILITY = "savedSubButtonVisibility"


class MainActivity : AppCompatActivity() {

//    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java)
    private lateinit var viewModel: MainViewModel

    private lateinit var ui: ActivityMainBinding

    // SharedPreferences
    private val sharedPrefs = "com.example.peoplecounter"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        ui.btnAdd.setOnClickListener { onIncrementClicked() }
        ui.btnSubtract.setOnClickListener { onDecrementClicked() }
        ui.btnReset.setOnClickListener { onResetClicked() }
        updatePeopleCounterText()
        updateTotalCounterText()

        val view = ui.root
        setContentView(view)

//        btnAdd = findViewById(R.id.btn_add)
//        btnSubtract = findViewById(R.id.btn_subtract)
//        btnSubtract.visibility = View.INVISIBLE
//        btnReset = findViewById(R.id.btn_reset)
//        tvPeopleCounter = findViewById(R.id.tv_people_counter)
//        tvTotalCounter = findViewById(R.id.tv_total_counter)
//
    }

    private fun onIncrementClicked() {
        viewModel.onIncrementClicked()
        updatePeopleCounterText()
        updateTotalCounterText()
    }

    private fun onDecrementClicked() {
        viewModel.onDecrementClicked()
        updatePeopleCounterText()
    }

    private fun onResetClicked() {
        viewModel.onResetClicked()
        updatePeopleCounterText()
        updateTotalCounterText()
    }

    private fun updatePeopleCounterText() {
        ui.tvPeopleCounter.text = getString(R.string.people_count, viewModel.peopleCounter)
        ui.tvPeopleCounter.setTextColor(ContextCompat.getColor(this, viewModel.peopleCounterColor))
    }

    private fun updateTotalCounterText() {
        ui.tvTotalCounter.text = getString(R.string.total_count, viewModel.totalCounter)
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        // Save counter values and UI configuration
//        outState.putInt(SAVED_TOTAL_COUNTER, totalCounter)
//        outState.putInt(SAVED_PEOPLE_COUNTER, peopleCounter)
//        outState.putInt(OVER_CAPACITY_INDICATOR, tvPeopleCounter.currentTextColor)
//        outState.putInt(SAVED_SUB_BUTTON_VISIBILITY, btnSubtract.visibility)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        // Restore total counter
//        totalCounter = savedInstanceState.getInt(SAVED_TOTAL_COUNTER, 0)
//        tvTotalCounter.text = getString(R.string.total_count, totalCounter)
//
//        // Restore people counter
//        peopleCounter = savedInstanceState.getInt(SAVED_PEOPLE_COUNTER, 0)
//        tvPeopleCounter.text = getString(R.string.people_count, peopleCounter)
//        tvPeopleCounter.setTextColor(savedInstanceState.getInt(OVER_CAPACITY_INDICATOR))
//
//        // Restore subtract button visibility
//        btnSubtract.visibility = savedInstanceState.getInt(SAVED_SUB_BUTTON_VISIBILITY)
//
//    }

    override fun onResume() {
        super.onResume()
        val pref = getSharedPreferences(sharedPrefs, Context.MODE_PRIVATE)
        // Restore total counter
        viewModel.totalCounter = pref.getInt(SAVED_TOTAL_COUNTER, 0)
        updateTotalCounterText()

        // Restore people counter
        viewModel.peopleCounter = pref.getInt(SAVED_PEOPLE_COUNTER, 0)
        updatePeopleCounterText()

        // Restore subtract button visibility
        ui.btnSubtract.visibility = pref.getInt(SAVED_SUB_BUTTON_VISIBILITY, ui.btnSubtract.visibility)
    }

    override fun onPause() {
        super.onPause()
        val pref = getSharedPreferences(sharedPrefs, Context.MODE_PRIVATE)
        val editor = pref.edit()

        editor.putInt(SAVED_TOTAL_COUNTER, viewModel.totalCounter)
        editor.putInt(SAVED_PEOPLE_COUNTER, viewModel.peopleCounter)
        editor.putInt(SAVED_PEOPLE_COUNTER_COLOR, viewModel.peopleCounterColor)
        editor.putBoolean(SAVED_SUB_BUTTON_VISIBILITY, viewModel.btnSubtractVisibility)

        editor.apply()
    }

}