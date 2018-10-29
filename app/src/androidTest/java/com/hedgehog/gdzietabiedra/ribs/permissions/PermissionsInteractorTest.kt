package com.hedgehog.gdzietabiedra.ribs.permissions

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PermissionsInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: PermissionsInteractor.PermissionsPresenter
  @Mock internal lateinit var router: PermissionsRouter

  private var interactor: PermissionsInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestPermissionsInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<PermissionsInteractor.PermissionsPresenter, PermissionsRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}