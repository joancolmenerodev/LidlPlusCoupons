package com.joancolmenerodev.lidlcoupons.features.detail.domain.usecase

import com.joancolmenerodev.lidlcoupons.base.domain.Either
import com.joancolmenerodev.lidlcoupons.features.detail.domain.CouponDetailExceptions
import com.joancolmenerodev.lidlcoupons.features.detail.domain.CouponDetailRepository
import com.joancolmenerodev.lidlcoupons.features.list.domain.model.CouponDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetCouponByIdUseCaseTest {
    private lateinit var useCase: GetCouponByIdUseCase
    private val repository: CouponDetailRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetCouponByIdUseCase(repository)
    }

    @Test
    fun `given user opens a coupon it shows the detail of the coupon`() {
        //ASSIGN
        coEvery { repository.getCouponDetail(any(), any()) } answers {
            couponDomain
        }

        //ACT
        val result =
            runBlocking { useCase.execute(COUPON_ID) }

        //ASSERT
        Assert.assertEquals(
            result,
            Either.Right(couponDomain)
        )
    }

    @Test
    fun `given user opens a coupon it shows coupon not found`() {
        //ASSIGN
        val exception = CouponDetailExceptions.CouponNotFound
        coEvery { repository.getCouponDetail(any(), any()) } throws exception

        //ACT
        val result =
            runBlocking { useCase.execute(COUPON_ID) }

        //ASSERT
        Assert.assertEquals(
            result,
            Either.Left(exception)
        )
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