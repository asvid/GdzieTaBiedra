package com.hedgehog.gdzietabiedra.ribs.bottomnav.settings

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.rib_settings.view.notification_range_seekbar
import kotlinx.android.synthetic.main.rib_settings.view.notification_range_value
import kotlinx.android.synthetic.main.rib_settings.view.notification_switch

/**
 * Top level view for {@link SettingsBuilder.SettingsScope}.
 */
class SettingsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
    defStyle: Int = 0) : LinearLayout(context, attrs,
    defStyle), SettingsInteractor.SettingsPresenter {

  override fun rangeSet(): Observable<Int> {
    return rangeSetSubject
  }

  override fun setRangeTest(range: String) {
    notification_range_value.text = range
  }

  val rangeSetSubject: PublishSubject<Int> = PublishSubject.create()
  val rangeChangesSubject: PublishSubject<Int> = PublishSubject.create()

  override fun rangeChanges(): Observable<Int> {
    return rangeChangesSubject
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    notification_switch.setOnCheckedChangeListener { _, isChecked ->
      notification_range_seekbar.isEnabled = isChecked
    }
    notification_range_seekbar.isEnabled = notification_switch.isChecked
    notification_range_seekbar.notification_range_seekbar.setOnSeekBarChangeListener(object :
        OnSeekBarChangeListener {
      override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        rangeChangesSubject.onNext(progress)
      }

      override fun onStartTrackingTouch(seekBar: SeekBar) {

      }

      override fun onStopTrackingTouch(seekBar: SeekBar) {
        rangeSetSubject.onNext(seekBar.progress)
      }
    })

  }
}
