package com.hedgehog.gdzietabiedra.ui.settings

import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import android.text.format.DateFormat
import android.util.AttributeSet
import android.view.View
import android.widget.TimePicker
import androidx.preference.DialogPreference
import androidx.preference.PreferenceDialogFragmentCompat
import com.hedgehog.gdzietabiedra.data.repository.DEFAULT_NOTIFICATION_HOUR
import com.hedgehog.gdzietabiedra.data.repository.DEFAULT_NOTIFICATION_MINUTE
import com.hedgehog.gdzietabiedra.utils.toLocalFormat
import com.hedgehog.gdzietabiedra.utils.toLocalTime
import java.time.LocalTime

class TimePreference : DialogPreference {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    override fun onGetDefaultValue(a: TypedArray, index: Int): String? {
        return a.getString(index)
    }

    override fun onSetInitialValue(restoreValue: Boolean, defaultValue: Any?) {
        summary = summary
        notifyChanged()
    }

    override fun getSummary(): CharSequence? {
        return getPersistedString(null)?.toLocalTime()?.toLocalFormat()
                ?: LocalTime.of(DEFAULT_NOTIFICATION_HOUR, DEFAULT_NOTIFICATION_MINUTE).toLocalFormat()
    }

    fun save(selectedTime: String) {
        persistString(selectedTime)
        summary = summary
        notifyChanged()
        callChangeListener(selectedTime)
    }
}

class TimePreferenceDialog : PreferenceDialogFragmentCompat() {
    private var picker: TimePicker? = null

    companion object {
        fun newInstance(key: String): TimePreferenceDialog {
            val fragment = TimePreferenceDialog()
            val bundle = Bundle(1)
            bundle.putString(ARG_KEY, key)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateDialogView(context: Context?): View {
        picker = TimePicker(context)
        picker?.setIs24HourView(DateFormat.is24HourFormat(context))
        return picker as TimePicker
    }

    override fun onBindDialogView(v: View) {
        super.onBindDialogView(v)
        val currentTime = LocalTime.now()
        picker?.hour = currentTime.hour
        picker?.minute = currentTime.minute
    }

    override fun onDialogClosed(positiveResult: Boolean) {
        if (positiveResult) {
            val selectedTime = "${String.format("%02d", picker?.hour ?: 0)}:${String.format("%02d", picker?.minute ?: 0)}"
            (preference as TimePreference).save(selectedTime)
        }
    }
}