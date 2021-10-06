package com.joancolmenerodev.lidlcoupons.features.list.data

import com.joancolmenerodev.lidlcoupons.base.flattenToList
import com.joancolmenerodev.lidlcoupons.features.list.domain.exceptions.CouponsListExceptions
import com.joancolmenerodev.lidlcoupons.features.list.domain.model.CouponDomain
import com.joancolmenerodev.lidlcoupons.features.list.domain.repository.CouponsListRepository
import com.joancolmenerodev.persistence.dao.CouponDao
import com.joancolmenerodev.persistence.entities.Coupon
import com.joancolmenerodev.persistence.entities.CouponsByUser
import com.joancolmenerodev.persistence.entities.Product
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CouponsListRepositoryImplTest {

    private lateinit var couponListRepository: CouponsListRepository
    private val mockCouponDao: CouponDao = mockk()

    @Before
    fun setUp() {
        couponListRepository =
            CouponsListRepositoryImpl(mockCouponDao)
    }

    @Test
    fun `given user asks for all the coupons filtered by user`() = runBlocking {
        //ASSIGN
        val listFlow = flowOf(listOf(couponsByUser))
        coEvery { mockCouponDao.findAllCouponByUser(any()) } answers { listFlow }

        //ACT
        val result = couponListRepository.getCouponsByUser(USER_ID).take(1).flattenToList()

        //ASSERT
        assertTrue(result == couponListDomainList)
    }

    @Test(expected = CouponsListExceptions.CouponsListEmpty::class)
    fun `given user asks for all the coupons but the list is empty then it returns an exception`() =
        runBlocking {
            //ASSIGN
            coEvery { mockCouponDao.findAllCouponByUser(any()) } throws CouponsListExceptions.CouponsListEmpty

            //ACT
            couponListRepository.getCouponsByUser(USER_ID)

            coVerify {
                mockCouponDao.findAllCouponByUser(any())
            }

        }

    @Test(expected = CouponsListExceptions.ErrorLoadingList::class)
    fun `given user asks for all the coupons but there's a problem with room then it returns an exception`() =
        runBlocking {
            //ASSIGN
            coEvery { mockCouponDao.findAllCouponByUser(any()) } throws CouponsListExceptions.ErrorLoadingList

            //ACT
            couponListRepository.getCouponsByUser(USER_ID)

            coVerify {
                mockCouponDao.findAllCouponByUser(any())
            }

        }


    companion object {

        private const val USER_ID = 1
        val couponListDomainList = listOf(
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