package com.example.peoplecounter

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

const val SAVED_TOTAL_COUNTER = "savedTotalCounter"
const val SAVED_PEOPLE_COUNTER = "savedPeopleCounter"
const val OVER_CAPACITY_INDICATOR = "overCapacityIndicator"
const val SAVED_SUB_BUTTON_VISIBILITY = "savedSubButtonVisibility"


class MainActivity : AppCompatActivity() {

    // Views
    private lateinit var btnAdd : Button
    private lateinit var btnSubtract: Button
    private lateinit var btnReset: Button
    private lateinit var tvPeopleCounter: TextView
    private lateinit var tvTotalCounter: TextView

    // Counter variables
    private var totalCounter = 0
    private var peopleCounter = 0

    // SharedPreferences
    private val sharedPrefs = "com.example.peoplecounter"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btn_add)
        btnSubtract = findViewById(R.id.btn_subtract)
        btnSubtract.visibility = View.INVISIBLE
        btnReset = findViewById(R.id.btn_reset)
        tvPeopleCounter = findViewById(R.id.tv_people_counter)
        tvTotalCounter = findViewById(R.id.tv_total_counter)

        tvTotalCounter.text = getString(R.string.total_count, totalCounter)
        tvPeopleCounter.text = getString(R.string.people_count, peopleCounter)

        btnAdd.setOnClickListener {
            btnSubtract.visibility = View.VISIBLE
            totalCounter += 1
            peopleCounter += 1
            tvTotalCounter.text = getString(R.string.total_count, totalCounter)
            if (peopleCounter > 15) {
                tvPeopleCounter.setTextColor(android.graphics.Color.RED)
            }
            tvPeopleCounter.text = getString(R.string.people_count, peopleCounter)

        }

        btnSubtract.setOnClickListener {
            peopleCounter -= 1
            if (peopleCounter <= 15) {
                tvPeopleCounter.setTextColor(ContextCompat.getColor(this, R.color.purple_700))
            }
            if (peopleCounter == 0) {
                btnSubtract.visibility = View.INVISIBLE
            }
            tvPeopleCounter.text = getString(R.string.people_count, peopleCounter)
        }

        btnReset.setOnClickListener {
            totalCounter = 0
            peopleCounter = 0
            btnSubtract.visibility = View.INVISIBLE
            tvPeopleCounter.setTextColor(ContextCompat.getColor(this, R.color.purple_700))
            tvTotalCounter.text = getString(R.string.total_count, totalCounter)
            tvPeopleCounter.text = getString(R.string.people_count, peopleCounter)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save counter values and UI configuration
        outState.putInt(SAVED_TOTAL_COUNTER, totalCounter)
        outState.putInt(SAVED_PEOPLE_COUNTER, peopleCounter)
        outState.putInt(OVER_CAPACITY_INDICATOR, tvPeopleCounter.currentTextColor)
        outState.putInt(SAVED_SUB_BUTTON_VISIBILITY, btnSubtract.visibility)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Restore total counter
        totalCounter = savedInstanceState.getInt(SAVED_TOTAL_COUNTER, 0)
        tvTotalCounter.text = getString(R.string.total_count, totalCounter)

        // Restore people counter
        peopleCounter = savedInstanceState.getInt(SAVED_PEOPLE_COUNTER, 0)
        tvPeopleCounter.text = getString(R.string.people_count, peopleCounter)
        tvPeopleCounter.setTextColor(savedInstanceState.getInt(OVER_CAPACITY_INDICATOR))

        // Restore subtract button visibility
        btnSubtract.visibility = savedInstanceState.getInt(SAVED_SUB_BUTTON_VISIBILITY)

    }

    override fun onResume() {
        super.onResume()
        val pref = getSharedPreferences(sharedPrefs, Context.MODE_PRIVATE)
        // Restore total counter
        totalCounter = pref.getInt(SAVED_TOTAL_COUNTER, 0)
        tvTotalCounter.text = getString(R.string.total_count, totalCounter)

        // Restore people counter
        peopleCounter = pref.getInt(SAVED_PEOPLE_COUNTER, 0)
        tvPeopleCounter.text = getString(R.string.people_count, peopleCounter)
        tvPeopleCounter.setTextColor(pref.getInt(OVER_CAPACITY_INDICATOR, tvPeopleCounter.currentTextColor))

        // Restore subtract button visibility
        btnSubtract.visibility = pref.getInt(SAVED_SUB_BUTTON_VISIBILITY, btnSubtract.visibility)
    }

    override fun onPause() {
        super.onPause()
        val pref = getSharedPreferences(sharedPrefs, Context.MODE_PRIVATE)
        val editor = pref.edit()

        editor.putInt(SAVED_TOTAL_COUNTER, totalCounter)
        editor.putInt(SAVED_PEOPLE_COUNTER, peopleCounter)
        editor.putInt(OVER_CAPACITY_INDICATOR, tvPeopleCounter.currentTextColor)
        editor.putInt(SAVED_SUB_BUTTON_VISIBILITY, btnSubtract.visibility)

        editor.apply()
    }

}