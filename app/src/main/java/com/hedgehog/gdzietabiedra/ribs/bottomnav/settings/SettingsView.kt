package com.hedgehog.gdzietabiedra.ribs.bottomnav.settings

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.rib_settings.view.notification_range_seekbar
import kotlinx.android.synthetic.main.rib_settings.view.notification_range_value
import kotlinx.android.synthetic.main.rib_settings.view.notification_switch
import kotlinx.android.synthetic.main.rib_settings.view.settings_email_button
import kotlinx.android.synthetic.main.rib_settings.view.settings_star_button
import kotlinx.android.synthetic.main.rib_settings.view.settings_version_label


/**
 * Top level view for {@link SettingsBuilder.SettingsScope}.
 */
class SettingsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
    defStyle: Int = 0) : LinearLayout(context, attrs,
    defStyle), SettingsInteractor.SettingsPresenter {

  private val rangeSetSubject: PublishSubject<Int> = PublishSubject.create()
  private val rangeChangesSubject: PublishSubject<Int> = PublishSubject.create()
  private val emailButtonSubject: PublishSubject<Any> = PublishSubject.create()
  private val shareStarsButtonSubject: PublishSubject<Any> = PublishSubject.create()

  override fun rangeSet() = rangeSetSubject

  override fun setRangeTest(range: String) {
    notification_range_value.text = range
  }

  override fun emailButtonSubject() = emailButtonSubject

  override fun starsButtonSubject() = shareStarsButtonSubject

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
    settings_email_button.clicks().subscribe(emailButtonSubject)
    settings_star_button.clicks().subscribe(shareStarsButtonSubject)
  }

  override fun openGooglePlay() {
    val uri = Uri.parse("market://details?id=" + context.packageName)
    val goToMarket = Intent(Intent.ACTION_VIEW, uri)
    // To count with Play market backstack, After pressing back button,
    // to taken back to our application, we need to add following flags to intent.
    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
        Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
        Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
    try {
      context.startActivity(goToMarket)
    } catch (e: ActivityNotFoundException) {
      context.startActivity(Intent(Intent.ACTION_VIEW,
          Uri.parse("http://play.google.com/store/apps/details?id=" + context.packageName)))
    }
  }

  override fun openEmail() {
    val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
        "mailto", "adam.swiderski89@gmail.com", null))
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "from GdzieTaBiedra")
    emailIntent.putExtra(Intent.EXTRA_TEXT, "I love your app but,")
    context.startActivity(Intent.createChooser(emailIntent, "Send email..."))
  }

  override fun setVersionName(versionName: String) {
    settings_version_label.text = versionName
  }
}
