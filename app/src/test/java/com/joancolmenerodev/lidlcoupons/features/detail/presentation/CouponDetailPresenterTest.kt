package com.joancolmenerodev.lidlcoupons.features.detail.presentation

import com.joancolmenerodev.lidlcoupons.base.CoroutineTestRule
import com.joancolmenerodev.lidlcoupons.base.domain.Either
import com.joancolmenerodev.lidlcoupons.features.detail.domain.usecase.GetCouponByIdUseCase
import com.joancolmenerodev.lidlcoupons.features.list.domain.exceptions.CouponItemExceptions
import com.joancolmenerodev.lidlcoupons.features.list.domain.model.CouponDomain
import com.joancolmenerodev.lidlcoupons.features.list.domain.usecase.UpdateCouponActivationUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CouponDetailPresenterTest {
    private lateinit var presenter: CouponDetailContract.Presenter

    @RelaxedMockK
    private lateinit var mockView: CouponDetailContract.View

    @MockK
    private lateinit var mockGetCouponById: GetCouponByIdUseCase

    @MockK
    private lateinit var mockUseCaseUpdateCoupon: UpdateCouponActivationUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = CouponDetailPresenter(mockGetCouponById, mockUseCaseUpdateCoupon)
    }

    @After
    fun tearDown() {
        presenter.onViewDestroyed()
        unmockkAll()
    }

    @Test
    fun `given user opens the detail it shows the correct coupon`() {
        //ASSIGN
        coEvery { mockGetCouponById.execute(any()) } returns Either.Right(couponDomain)


        //ACT
        presenter.onViewReady(mockView, COUPON_ID)
        val result = couponDomain.mapToCouponDetailView()


        //ASSERT
        coVerify(exactly = 1) {
            mockView.updateToolbar("${result.couponDiscount} ${result.couponDiscountName}")
            mockView.displayBrandProductImage(result.productBrandImage)
            mockView.displayProductImage(result.productImage)
            mockView.displayProductDescription(result.productDescription)
            mockView.displayCouponDiscount(result.couponDiscount)
            mockView.displayCouponDiscountName(result.couponDiscountName)
            mockView.displayCouponSelected(result.isSelected)
            mockView.displayProductBrandName(result.productBrandName)
            mockView.displayProductName(result.productName)
            mockView.displayTimeToExpire(result.couponStatus)
            mockView.displayCouponCondition(result.couponCondition)
            mockView.displayCouponConditionDescription(result.couponConditionDescription)
            mockView.displayProductId(result.productId)
        }
    }

    @Test
    fun `given user update the coupon status and remove the selection`() {
        //ASSIGN
        coEvery { mockUseCaseUpdateCoupon.removeSelection(any()) } returns Either.Right(false)


        //ACT
        presenter.onViewReady(mockView)
        presenter.updateCouponActivation(COUPON_ID, true)


        //ASSERT
        coVerify(exactly = 1) {
            mockView.updateSelectedCoupon(false)
        }
    }

    @Test
    fun `given user update the coupon status and select the coupon`() {
        //ASSIGN
        coEvery { mockUseCaseUpdateCoupon.selectCoupon(any()) } returns Either.Right(true)


        //ACT
        presenter.onViewReady(mockView)
        presenter.updateCouponActivation(COUPON_ID, false)


        //ASSERT
        coVerify(exactly = 1) {
            mockView.updateSelectedCoupon(true)
        }
    }

    @Test
    fun `given user update the coupon but there's a problem with the selection it shows a message`() {
        //ASSIGN
        coEvery { mockUseCaseUpdateCoupon.selectCoupon(any()) } returns Either.Left(
            CouponItemExceptions.CouponSetSelectedException
        )


        //ACT
        presenter.onViewReady(mockView)
        presenter.updateCouponActivation(COUPON_ID, false)


        //ASSERT
        coVerify(exactly = 1) {
            mockView.displayErrorCouponSelection()
        }
    }

    @Test
    fun `given user update the coupon but there's a problem with the remove selection shows a message`() {
        //ASSIGN
        coEvery { mockUseCaseUpdateCoupon.removeSelection(any()) } returns Either.Left(
            CouponItemExceptions.CouponUnSelectException
        )


        //ACT
        presenter.onViewReady(mockView)
        presenter.updateCouponActivation(COUPON_ID, true)


        //ASSERT
        coVerify(exactly = 1) {
            mockView.displayErrorCouponSelection()
        }
    }

    companion object {
        private const val COUPON_ID = 1
        private val couponDomain = CouponDomain(
            id = 1,
            name = "Cupon de lechucas",
            description = "Mejor cupon ever",
            discountName = "Descuento",
            discount = "50%",
            startDate = 123456,
            endDate = 654321,
            availabilityAndConditions = "Availability and conditions",
            condition = "Conditions",
            conditionDescription = "Conditions and description",
            productName = "productOne",
            productImage = "image_product",
            productBrandName = "brand name product one",
            productBrandImage = "brand_image",
            isSelected = true,
            productId = 1,
            productDescription = "description product one"
        )
    }
}