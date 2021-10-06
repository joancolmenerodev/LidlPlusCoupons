package com.joancolmenerodev.lidlcoupons.features.list.domain.usecase

import com.joancolmenerodev.lidlcoupons.base.domain.Either
import com.joancolmenerodev.lidlcoupons.features.list.domain.model.CouponDomain
import com.joancolmenerodev.lidlcoupons.features.list.domain.repository.CouponsListRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCouponsByUserUseCaseTest {

    private lateinit var useCase: GetCouponsByUserUseCase
    private val repository: CouponsListRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetCouponsByUserUseCase(repository)
    }

    @Test
    fun `given the repository returns the list of coupons then the result is a list of coupons`() =
        runBlocking {
            //ASSIGN
            coEvery { repository.getCouponsByUser(any()) } answers { flowOf(couponListDomainList) }

            //ACT
            val result = useCase.execute()

            //ASSERT
            assertEquals(result.toList()[0], Either.Right(couponListDomainList))

        }

    companion object {

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
            ),
            CouponDomain(
                id = 2,
                name = "Cupon de lechucas2",
                description = "Mejor cupon ever2",
                discountName = "Descuento",
                discount = "50%",
                startDate = 123456,
                endDate = 654321,
                availabilityAndConditions = "Availability and conditions",
                condition = "Conditions",
                conditionDescription = "Conditions and description",
                productName = "productTwo",
                productImage = "image_product",
                productBrandName = "brand name product two",
                productBrandImage = "brand_image",
                isSelected = false,
                productId = 1,
                productDescription = "product description"
            )
        )
    }
}