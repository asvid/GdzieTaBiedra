package com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * Top level view for {@link ShopsListBuilder.ShopsListScope}.
 */
class ShopsListView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
    defStyle: Int = 0) : LinearLayout(context, attrs,
    defStyle), ShopsListInteractor.ShopsListPresenter
