package com.joancolmenerodev.lidlcoupons.features.list.presentation

import com.joancolmenerodev.lidlcoupons.base.CoroutineTestRule
import com.joancolmenerodev.lidlcoupons.base.domain.Either
import com.joancolmenerodev.lidlcoupons.features.list.domain.exceptions.CouponsListExceptions
import com.joancolmenerodev.lidlcoupons.features.list.domain.model.CouponDomain
import com.joancolmenerodev.lidlcoupons.features.list.domain.usecase.GetCouponsByUserUseCase
import com.joancolmenerodev.lidlcoupons.features.list.presentation.mapper.mapToCouponListView
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CouponsListPresenterTest {
    private lateinit var presenter: CouponsListContract.Presenter

    @RelaxedMockK
    private lateinit var mockView: CouponsListContract.View

    @MockK
    private lateinit var mockUseCase: GetCouponsByUserUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = CouponsListPresenter(mockUseCase)
    }

    @After
    fun tearDown() {
        presenter.onViewDestroyed()
        unmockkAll()
    }

    @Test
    fun `given the user enters to the app the the list of coupons are visible`() {
        //ASSIGN
        coEvery { mockUseCase.execute() } answers { flowOf(Either.Right(couponListDomainList)) }


        //ACT
        presenter.onViewReady(mockView)


        //ASSERT
        verify(exactly = 1) {
            mockView.showProgressBar(true)
            mockView.fillList(couponListDomainList.mapToCouponListView())
            mockView.showProgressBar(false)
        }
    }

    @Test
    fun `given user tries to click on a locked coupon it shows a dialog`() {
        //ASSIGN

        //ACT
        presenter.onViewReady(mockView)
        presenter.showCouponLocked(FAKE_DAYS)


        //ASSERT
        verify(exactly = 1) {
            mockView.displayLockedCoupon(FAKE_DAYS)
        }

    }

    @Test
    fun `given user wnats to see the detail of a coupon it navigates to the detail coupon`() {
        //ASSIGN

        //ACT
        presenter.onViewReady(mockView)
        presenter.showCouponDetail(FAKE_COUPON_ID)


        //ASSERT
        verify(exactly = 1) {
            mockView.navigateToDetailCoupon(FAKE_COUPON_ID)
        }
    }

    @Test
    fun `given user asks for the coupons it notifies also the number of coupons to the view`() {
        //ASSIGN
        val activeCoupons = couponListDomainList.count { it.isSelected }
        coEvery { mockUseCase.execute() } answers { flowOf(Either.Right(couponListDomainList)) }


        //ACT
        presenter.onViewReady(mockView)


        //ASSERT
        verify(exactly = 1) {
            mockView.showProgressBar(true)
            mockView.fillList(couponListDomainList.mapToCouponListView())
            mockView.showProgressBar(false)
            mockView.showOneOrMoreActiveCoupons(activeCoupons)
        }
    }

    @Test
    fun `given user asks for the coupons it notifies also the number of coupons to the view when there are not`() {
        //ASSIGN
        val activeCoupons = couponListDomainNoActiveCouponsList.count { it.isSelected }
        coEvery { mockUseCase.execute() } answers {
            flowOf(
                Either.Right(
                    couponListDomainNoActiveCouponsList
                )
            )
        }


        //ACT
        presenter.onViewReady(mockView)


        //ASSERT
        verify(exactly = 1) {
            mockView.showProgressBar(true)
            mockView.fillList(couponListDomainNoActiveCouponsList.mapToCouponListView())
            mockView.showProgressBar(false)
            mockView.showNoActiveCoupons(activeCoupons)
        }
    }

    @Test
    fun `given user receives an empty list it shows the correct layout`() {
        //ASSIGN
        coEvery { mockUseCase.execute() } answers {
            flowOf(
                Either.Left(
                    CouponsListExceptions.CouponsListEmpty
                )
            )
        }


        //ACT
        presenter.onViewReady(mockView)


        //ASSERT
        verify(exactly = 1) {
            mockView.showProgressBar(true)
            mockView.showEmptyLayout()
            mockView.showProgressBar(false)
        }
    }

    @Test
    fun `given user receives an error from database it shows the correct layout`() {
        //ASSIGN
        coEvery { mockUseCase.execute() } answers {
            flowOf(
                Either.Left(
                    CouponsListExceptions.ErrorLoadingList
                )
            )
        }


        //ACT
        presenter.onViewReady(mockView)


        //ASSERT
        verify(exactly = 1) {
            mockView.showProgressBar(true)
            mockView.showErrorLayout()
            mockView.showProgressBar(false)
        }
    }

    companion object {
        private const val FAKE_DAYS = "2"
        private const val FAKE_COUPON_ID = 1
        private val couponListDomainList = listOf(
            CouponDomain(
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
                productDescription = "product description"
            )
        )
        private val couponListDomainNoActiveCouponsList = listOf(
            CouponDomain(
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
                isSelected = false,
                productId = 1,
                productDescription = "product description"
            )
        )
    }
}