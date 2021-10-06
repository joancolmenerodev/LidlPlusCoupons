package com.joancolmenerodev.lidlcoupons.features.list.adapter

import com.joancolmenerodev.lidlcoupons.base.CoroutineTestRule
import com.joancolmenerodev.lidlcoupons.base.domain.Either
import com.joancolmenerodev.lidlcoupons.features.list.domain.exceptions.CouponItemExceptions
import com.joancolmenerodev.lidlcoupons.features.list.domain.usecase.UpdateCouponActivationUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CouponItemPresenterTest {

    private lateinit var presenter: CouponItemContract.Presenter

    @RelaxedMockK
    private lateinit var mockView: CouponItemContract.View

    @MockK
    private lateinit var mockUseCase: UpdateCouponActivationUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = CouponItemPresenter(mockUseCase)
        presenter.onViewReady(mockView)
    }

    @After
    fun tearDown() {
        presenter.onViewDestroyed()
        unmockkAll()
    }

    @Test
    fun `given user select a coupon then it selected`() {

        //ASSIGN
        coEvery { mockUseCase.selectCoupon(any()) } answers { Either.Right(true) }

        //ACT
        presenter.onCouponClicked(USER_ID, true)

        //ASSERT
        coVerify { mockView wasNot called }
    }

    @Test
    fun `given user tries to select a coupon that is already selected it returns an error`() {

        //ASSIGN
        val exception = CouponItemExceptions.CouponSetSelectedException
        coEvery { mockUseCase.selectCoupon(any()) } answers { Either.Left(exception) }

        //ACT
        presenter.onCouponClicked(USER_ID, false)

        //ASSERT
        coVerify(exactly = 1) {
            mockView.showSelectError()
        }
    }

    @Test
    fun `given user tries to un-select a coupon that is already un-selected it returns an error`() {

        //ASSIGN
        val exception = CouponItemExceptions.CouponUnSelectException
        coEvery { mockUseCase.removeSelection(any()) } answers { Either.Left(exception) }

        //ACT
        presenter.onCouponClicked(USER_ID, true)

        //ASSERT
        coVerify(exactly = 1) {
            mockView.showUnselectError()
        }
    }

    @Test
    fun `given user tries to do an action but there's a fail on room it returns an unknwonError`() {

        //ASSIGN
        val exception = CouponItemExceptions.CouponItemKnownException
        coEvery { mockUseCase.selectCoupon(any()) } answers { Either.Left(exception) }

        //ACT
        presenter.onCouponClicked(USER_ID, false)

        //ASSERT
        coVerify(exactly = 1) {
            mockView.showUnknownError()
        }
    }

    @Test
    fun `given user un-select a coupon then it returns error`() {

        //ASSIGN
        coEvery { mockUseCase.removeSelection(any()) } answers { Either.Right(true) }

        //ACT
        presenter.onCouponClicked(USER_ID, true)

        //ASSERT
        coVerify { mockView wasNot called }
    }

    companion object {
        private const val USER_ID = 1
    }

}