package com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ShopsListRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: ShopsListBuilder.Component
  @Mock internal lateinit var interactor: ShopsListInteractor
  @Mock internal lateinit var view: ShopsListView

  private var router: ShopsListRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = ShopsListRouter(view, interactor, component)
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

