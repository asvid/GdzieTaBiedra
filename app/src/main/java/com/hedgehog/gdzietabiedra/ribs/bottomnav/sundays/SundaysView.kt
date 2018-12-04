package com.hedgehog.gdzietabiedra.ribs.bottomnav.sundays

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import com.github.asvid.biedra.domain.SundayShopping
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import kotlinx.android.synthetic.main.rib_sundays.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Top level view for {@link SundaysBuilder.SundaysScope}.
 */
class SundaysView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle), SundaysInteractor.SundaysPresenter {

  private val dateFormatForMonth = SimpleDateFormat("LLLL - yyyy", Locale.getDefault())

  override fun onFinishInflate() {
    super.onFinishInflate()

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
  }
}
