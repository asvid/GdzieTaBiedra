package com.hedgehog.gdzietabiedra.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.hedgehog.gdzietabiedra.Di
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.utils.Const
import com.hedgehog.gdzietabiedra.utils.NotificationService
import com.rey.material.widget.Button
import com.rey.material.widget.CheckBox
import com.rey.material.widget.Slider
import kotlin.properties.Delegates

class Settings : AppCompatActivity() {

    internal var notificationCheckbox: CheckBox by Delegates.notNull()
    internal var radiusSlider: Slider by Delegates.notNull()
    internal var radiusValue: TextView by Delegates.notNull()
    internal var saveBtn: Button by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        notificationCheckbox = findViewById(R.id.notificationsCheck) as CheckBox
        radiusSlider = findViewById(R.id.radiusSlider) as Slider
        radiusValue = findViewById(R.id.radiusValue) as TextView
        saveBtn = findViewById(R.id.saveBtn) as Button

        //set saved values or default ones
        radiusSlider.setValue(Integer.parseInt(Di.storage
                                                       .readFromPreferences("radiusValue",
                                                                            Const.radiusDefault)).toFloat(),
                              true)
        notificationCheckbox.isChecked = Di.storage
                .readBoolean("radar", Const.radiusCheckDefault)

        radiusValue.text = Di.storage
                .readFromPreferences("radiusValue",
                                     radiusSlider.value.toString() + "")

        radiusSlider.setOnPositionChangeListener { slider, b, v, v1, i, i1 -> radiusValue.text = slider.value.toString() + " m" }
        saveBtn.setOnClickListener { saveSettings() }
    }

    private fun saveSettings() {
        Di.storage
                .saveBoolean("radar", notificationCheckbox.isChecked)
        Di.storage
                .saveToPreferences("radiusValue", radiusSlider.value.toString() + "")
        if (notificationCheckbox.isChecked) {
            startService(Intent(this, NotificationService::class.java))
        } else {
            stopService(Intent(this, NotificationService::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
