package com.hedgehog.gdzietabiedra.ribs.bottomnav.sundays

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Top level view for {@link SundaysBuilder.SundaysScope}.
 */
class SundaysView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle), SundaysInteractor.SundaysPresenter
