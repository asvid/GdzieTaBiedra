package com.hedgehog.gdzietabiedra.ui.views

import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import android.text.format.DateFormat
import android.util.AttributeSet
import android.view.View
import android.widget.TimePicker
import androidx.preference.DialogPreference
import androidx.preference.PreferenceDialogFragmentCompat
import java.util.*

class TimePreference: DialogPreference {

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
        return DateFormat.getTimeFormat(context).format(Date(getPersistedLong(System.currentTimeMillis())))
    }

    fun save(timeInMillis: Long) {
        persistLong(timeInMillis)
        summary = summary
        notifyChanged()
    }
}

class TimePreferenceDialog : PreferenceDialogFragmentCompat() {
    private val calendar: Calendar = Calendar.getInstance()
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
        picker?.hour = calendar.get(Calendar.HOUR_OF_DAY)
        picker?.minute = calendar.get(Calendar.MINUTE)
    }

    override fun onDialogClosed(positiveResult: Boolean) {
        if (positiveResult) {
            val date = Date()
            calendar.time = date
            calendar.set(Calendar.HOUR_OF_DAY, picker?.currentHour ?: 0)
            calendar.set(Calendar.MINUTE, picker?.currentMinute ?: 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)

            (preference as TimePreference).save(calendar.timeInMillis)
        }
    }
}