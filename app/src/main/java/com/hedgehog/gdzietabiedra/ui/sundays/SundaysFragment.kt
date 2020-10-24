package com.hedgehog.gdzietabiedra.ui.sundays

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.asvid.biedra.domain.SundayShopping
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import com.hedgehog.gdzietabiedra.R.layout
import kotlinx.android.synthetic.main.fragment_sundays.calendar_view
import kotlinx.android.synthetic.main.fragment_sundays.month_left_arrow
import kotlinx.android.synthetic.main.fragment_sundays.month_right_arrow
import kotlinx.android.synthetic.main.fragment_sundays.month_title
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SundaysFragment : Fragment() {

  private lateinit var sundaysViewModel: SundaysViewModel
  private val dateFormatForMonth = SimpleDateFormat("LLLL - yyyy", Locale.getDefault())

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    sundaysViewModel =
        ViewModelProvider(this).get(SundaysViewModel::class.java)
    return inflater.inflate(layout.fragment_sundays, container, false)
  }

  override fun onResume() {
    month_title.text = dateFormatForMonth.format(calendar_view.firstDayOfCurrentMonth)
    calendar_view.shouldSelectFirstDayOfMonthOnScroll(false)
    calendar_view.setListener(object : CompactCalendarView.CompactCalendarViewListener {
      override fun onDayClick(dateClicked: Date?) {
      }

      override fun onMonthScroll(firstDayOfNewMonth: Date?) {
        month_title.text = dateFormatForMonth.format(firstDayOfNewMonth)
      }
    })
    calendar_view.setUseThreeLetterAbbreviation(true)
    SundayShopping.shoppingSundays.forEach {
      calendar_view.addEvent(Event(Color.GREEN, it.toDate().time))
    }

    month_left_arrow.setOnClickListener { calendar_view.scrollLeft() }
    month_right_arrow.setOnClickListener { calendar_view.scrollRight() }

    super.onResume()
  }
}