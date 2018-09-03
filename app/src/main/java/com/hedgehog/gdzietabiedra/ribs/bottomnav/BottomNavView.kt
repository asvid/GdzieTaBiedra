package com.hedgehog.gdzietabiedra.ribs.bottomnav

import android.content.Context
import android.support.design.widget.BottomNavigationView
import android.util.AttributeSet
import com.hedgehog.gdzietabiedra.R
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import timber.log.Timber

/**
 * Top level view for {@link BottomNavBuilder.BottomNavScope}.
 */
class BottomNavView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : BottomNavigationView(context, attrs,
    defStyle), BottomNavInteractor.BottomNavPresenter {

  private val behaviorRelay = BehaviorRelay.createDefault(MenuEvent.LIST)
  private val menuRelay = behaviorRelay.toSerialized()

  override fun onFinishInflate() {
    super.onFinishInflate()

    setOnNavigationItemSelectedListener { item ->

      Timber.d("view nav event: $item")
      when (item.itemId) {

        R.id.navigation_list -> menuRelay.accept(MenuEvent.LIST)
        R.id.navigation_map -> menuRelay.accept(MenuEvent.MAP)
        R.id.navigation_settings -> menuRelay.accept(MenuEvent.SETTINGS)
      }

      true
    }
  }

  override fun menuEvents(): Observable<MenuEvent> {
    return menuRelay.distinctUntilChanged()
  }
}
