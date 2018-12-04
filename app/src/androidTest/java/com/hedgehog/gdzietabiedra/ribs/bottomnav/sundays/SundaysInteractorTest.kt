package com.hedgehog.gdzietabiedra.ribs.bottomnav.sundays

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SundaysInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: SundaysInteractor.SundaysPresenter
  @Mock internal lateinit var router: SundaysRouter

  private var interactor: SundaysInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestSundaysInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<SundaysInteractor.SundaysPresenter, SundaysRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}