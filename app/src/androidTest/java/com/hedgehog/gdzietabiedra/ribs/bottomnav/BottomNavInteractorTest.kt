package com.hedgehog.gdzietabiedra.ribs.bottomnav

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BottomNavInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: BottomNavInteractor.BottomNavPresenter
  @Mock internal lateinit var router: BottomNavRouter

  private var interactor: BottomNavInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestBottomNavInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<BottomNavInteractor.BottomNavPresenter, BottomNavRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}