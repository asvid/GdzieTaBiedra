package com.hedgehog.gdzietabiedra.ribs.bottomnav.settings

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * Top level view for {@link SettingsBuilder.SettingsScope}.
 */
class SettingsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
    defStyle: Int = 0) : LinearLayout(context, attrs,
    defStyle), SettingsInteractor.SettingsPresenter
