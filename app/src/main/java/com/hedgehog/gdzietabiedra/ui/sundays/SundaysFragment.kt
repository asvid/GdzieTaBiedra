package com.hedgehog.gdzietabiedra.ui.sundays

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.asvid.biedra.domain.shops.SundayShopping
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import com.hedgehog.gdzietabiedra.R.layout
import kotlinx.android.synthetic.main.fragment_sundays.*
import org.koin.androidx.viewmodel.compat.ViewModelCompat
import java.text.SimpleDateFormat
import java.util.*

class SundaysFragment : Fragment() {

    private val vm: SundaysViewModel by ViewModelCompat.viewModel(this, SundaysViewModel::class.java)
    private val dateFormatForMonth = SimpleDateFormat("LLLL - yyyy", Locale.getDefault())

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout.fragment_sundays, container, false)
    }

    override fun onResume() {
        month_title.text = dateFormatForMonth.format(calendar_view.firstDayOfCurrentMonth)
        calendar_view.shouldSelectFirstDayOfMonthOnScroll(false)
        calendar_view.setListener(object : CompactCalendarView.CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date?) {
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date?) {
                firstDayOfNewMonth?.let {
                    month_title.text = dateFormatForMonth.format(it)
                }
            }
        })
        calendar_view.setUseThreeLetterAbbreviation(true)
        SundayShopping.businessDays.forEach {
            calendar_view.addEvent(Event(Color.GREEN, it.toDate().time))
        }

        month_left_arrow.setOnClickListener { calendar_view.scrollLeft() }
        month_right_arrow.setOnClickListener { calendar_view.scrollRight() }
        activity?.actionBar?.hide()
        super.onResume()
    }
}