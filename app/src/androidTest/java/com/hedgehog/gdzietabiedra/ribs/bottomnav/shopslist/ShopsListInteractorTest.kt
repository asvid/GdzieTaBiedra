package com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ShopsListInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: ShopsListInteractor.ShopsListPresenter
  @Mock internal lateinit var router: ShopsListRouter

  private var interactor: ShopsListInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestShopsListInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<ShopsListInteractor.ShopsListPresenter, ShopsListRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}