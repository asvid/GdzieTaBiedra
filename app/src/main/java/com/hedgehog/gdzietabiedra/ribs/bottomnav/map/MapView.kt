package com.hedgehog.gdzietabiedra.ribs.bottomnav.map

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.map_rib.view.*
import timber.log.Timber

/**
 * Top level view for {@link MapBuilder.MapScope}.
 */
class MapView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), MapInteractor.MapPresenter {

    override fun initView() {
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        map_view.onCreate(null)
        map_view.getMapAsync {
            Timber.d("map is loaded")
            map_view.onResume()
            it.setMyLocationEnabled(true)
        }
    }
}
