package com.joancolmenerodev.lidlcoupons.features.list.domain.model

import com.joancolmenerodev.persistence.entities.Coupon
import com.joancolmenerodev.persistence.entities.CouponsByUser
import com.joancolmenerodev.persistence.entities.Product
import junit.framework.Assert.assertEquals
import org.junit.Test

class CouponDomainMapperTest {

    @Test
    fun `couponListDomainMapperTest`() {
        //ASSIGN

        //ACT
        val result = couponsByUser.map()


        //ASSERT
        assertEquals(result, couponListDomainList)

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
                productDescription = "description product one"
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
                productId = 2,
                productDescription = "description product two"
            )
        )
        private val couponsByUser = listOf(
            CouponsByUser(
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
            ),
            CouponsByUser(
                Coupon(
                    2,
                    2,
                    "Cupon de lechucas2",
                    "Mejor cupon ever2",
                    "50%",
                    "Descuento",
                    123456,
                    654321,
                    "Availability and conditions",
                    "Conditions",
                    "Conditions and description"
                ),
                Product(
                    2,
                    "productTwo",
                    "description product two",
                    "image_product",
                    "brand name product two",
                    "brand_image",
                    "40€"
                ),
                false
            )
        )
    }
}