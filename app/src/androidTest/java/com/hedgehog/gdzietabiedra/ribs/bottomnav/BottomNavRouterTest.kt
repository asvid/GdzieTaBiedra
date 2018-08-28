package com.hedgehog.gdzietabiedra.ribs.bottomnav

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BottomNavRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: BottomNavBuilder.Component
  @Mock internal lateinit var interactor: BottomNavInteractor
  @Mock internal lateinit var view: BottomNavView

  private var router: BottomNavRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = BottomNavRouter(view, interactor, component)
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

