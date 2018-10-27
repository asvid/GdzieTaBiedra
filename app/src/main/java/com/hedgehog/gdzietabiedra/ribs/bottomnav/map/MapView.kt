package com.hedgehog.gdzietabiedra.ribs.bottomnav.map

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.hedgehog.gdzietabiedra.appservice.map.GoogleMapProvider
import com.hedgehog.gdzietabiedra.appservice.map.MapProvider
import com.hedgehog.gdzietabiedra.domain.Shop
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.android.synthetic.main.rib_map.view.map_navigation_button
import kotlinx.android.synthetic.main.rib_map.view.map_view
import timber.log.Timber

/**
 * Top level view for {@link MapBuilder.MapScope}.
 */
class MapView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), MapInteractor.MapPresenter {

  override fun initView(): Single<MapProvider> {
    map_view.onCreate(null)
    return Single.fromPublisher { publisher ->
      map_view.getMapAsync {
        Timber.d("map is loaded")
        publisher.onNext(GoogleMapProvider.create(it, context))
        publisher.onComplete()
        map_view.onResume()
      }
    }
  }

  override fun switchNavigationButton(visible: Boolean) {
    if (visible) {
      map_navigation_button.visibility = VISIBLE
    } else {
      map_navigation_button.visibility = View.GONE
    }
  }

  override fun navigationButtonListener(): Observable<*> = RxView.clicks(map_navigation_button)

  override fun startNavigation(shop: Shop) {
    val intent = Intent(android.content.Intent.ACTION_VIEW)
    intent.data = Uri.parse("geo:" + shop.location.lat + "," +
        shop.location.lng + "?q=" + shop.address)
    context.startActivity(intent)

  }
}
