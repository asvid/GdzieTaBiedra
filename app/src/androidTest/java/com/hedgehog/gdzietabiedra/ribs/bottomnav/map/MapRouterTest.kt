package com.hedgehog.gdzietabiedra.ribs.bottomnav.map

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MapRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: MapBuilder.Component
  @Mock internal lateinit var interactor: MapInteractor
  @Mock internal lateinit var view: MapView

  private var router: MapRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = MapRouter(view, interactor, component)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router!!)
    RouterHelper.detach(router!!)

    throw RuntimeException("Remove this test and add real tests.")
  }

}

