package com.hedgehog.gdzietabiedra.ribs.bottomnav.map

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * Top level view for {@link MapBuilder.MapScope}.
 */
class MapView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
    defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), MapInteractor.MapPresenter
