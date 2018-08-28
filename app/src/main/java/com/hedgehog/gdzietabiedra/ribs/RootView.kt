package com.hedgehog.gdzietabiedra.ribs

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.root_rib.view.content
import kotlinx.android.synthetic.main.root_rib.view.contentLayout

/**
 * Top level view for {@link RootBuilder.RootScope}.
 */
class RootView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle), RootInteractor.RootPresenter {

  fun viewContent(): ViewGroup {
    return content
  }

  fun viewNavigationLayout(): ViewGroup {
    return contentLayout
  }
}
