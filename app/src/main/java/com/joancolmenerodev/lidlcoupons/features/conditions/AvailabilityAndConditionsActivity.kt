package com.joancolmenerodev.lidlcoupons.features.conditions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.joancolmenerodev.lidlcoupons.R
import kotlinx.android.synthetic.main.content_main.*

class AvailabilityAndConditionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.availability_and_conditions_activity)
        setupToolbar()
        tv_availability_and_conditions.text = getAvailabilityAndConditionsFromIntent()

    }

    private fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getAvailabilityAndConditionsFromIntent() = intent.getStringExtra(
        KEY_AVAILABILITY_AND_CONDITIONS
    )

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val KEY_AVAILABILITY_AND_CONDITIONS = "availability_and_conditions"

        fun getAvailabilityAndConditionsIntent(
            context: Context,
            availabilityAndConditions: String
        ): Intent =
            Intent(context, AvailabilityAndConditionsActivity::class.java)
                .apply {
                    putExtra(KEY_AVAILABILITY_AND_CONDITIONS, availabilityAndConditions)
                }
    }
}