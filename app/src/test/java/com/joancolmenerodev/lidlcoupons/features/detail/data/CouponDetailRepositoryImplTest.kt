package com.joancolmenerodev.lidlcoupons.features.detail.data

import com.joancolmenerodev.lidlcoupons.features.detail.domain.CouponDetailExceptions
import com.joancolmenerodev.lidlcoupons.features.list.domain.model.CouponDomain
import com.joancolmenerodev.persistence.dao.CouponDao
import com.joancolmenerodev.persistence.entities.Coupon
import com.joancolmenerodev.persistence.entities.CouponsByUser
import com.joancolmenerodev.persistence.entities.Product
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CouponDetailRepositoryImplTest {
    private lateinit var couponDetailRepository: CouponDetailRepositoryImpl
    private val mockCouponDao: CouponDao = mockk()

    @Before
    fun setUp() {
        couponDetailRepository =
            CouponDetailRepositoryImpl(mockCouponDao)
    }

    @Test
    fun `given user asks for all the coupons but there's a problem with room then it returns an exception`() {
        //ASSIGN
        coEvery {
            mockCouponDao.findCouponByUserAndCoupon(
                any(),
                any()
            )
        } answers { couponsByUser }

        //ACT
        val result = runBlocking { couponDetailRepository.getCouponDetail(USER_ID, COUPON_ID) }

        coVerify {
            mockCouponDao.findCouponByUserAndCoupon(any(), any())
        }
        assertTrue(result == couponDomain)

    }

    @Test(expected = CouponDetailExceptions.CouponNotFound::class)
    fun `given user clicks a coupon but then it doesn't find that coupon on the database it returns an error`() {
        //ASSIGN
        coEvery {
            mockCouponDao.findCouponByUserAndCoupon(
                any(),
                any()
            )
        } answers { couponsByUser }

        //ACT
        val result = runBlocking { couponDetailRepository.getCouponDetail(USER_ID, COUPON_ID) }

        coVerify {
            mockCouponDao.findCouponByUserAndCoupon(any(), any())
        }
        assertTrue(result == couponDomain)
    }


    companion object {

        private const val USER_ID = 1
        private const val COUPON_ID = 1
        val couponDomain =
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
                productDescription = "description product one"
            )

        val couponsByUser = CouponsByUser(
            Coupon(
                1,
                1,
                "Cupon de lechucas",
                "Mejor cupon ever",
                "50%",
                "Descuento",
                123456,
                654321,
                "Availability and conditions",
                "Conditions",
                "Conditions and description"
            ),
            Product(
                1,
                "productOne",
                "description product one",
                "image_product",
                "brand name product one",
                "brand_image",
                "40€"
            ),
            true
        )
    }


}