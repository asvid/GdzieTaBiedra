package com.hedgehog.gdzietabiedra.ribs.splash

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.rib_splashscreen.view.splash_permissions_button

/**
 * Top level view for {@link SplashBuilder.SplashScope}.
 */
class SplashView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), SplashInteractor.SplashPresenter {

  private val buttonSubject = PublishSubject.create<Any>()

  override fun buttonClick() = buttonSubject

  override fun showPermissionButton() {
    splash_permissions_button.visibility = View.VISIBLE
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    splash_permissions_button.clicks().subscribe(buttonSubject)
  }
}
