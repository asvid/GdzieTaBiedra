package com.hedgehog.gdzietabiedra.ribs.bottomnav.sundays

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SundaysRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: SundaysBuilder.Component
  @Mock internal lateinit var interactor: SundaysInteractor
  @Mock internal lateinit var view: SundaysView

  private var router: SundaysRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = SundaysRouter(view, interactor, component)
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

