package com.joancolmenerodev.persistence

import com.joancolmenerodev.persistence.entities.Coupon
import com.joancolmenerodev.persistence.entities.Product
import com.joancolmenerodev.persistence.entities.Selected
import com.joancolmenerodev.persistence.entities.User

object PersistenceModelsTest {

    /**
     * User
     */
    val user1 = User(1, "Joan")
    val user2 = User(2, "Paco")

    /**
     * Product
     */
    val product1 = Product(
        1,
        "productOne",
        "description product one",
        "image_product",
        "brand name product one",
        "brand_image",
        "40€"
    )
    val product2 = Product(
        2,
        "productTwo",
        "description product two",
        "image_product2",
        "brand name product two",
        "brand_image2",
        "20€"
    )

    /**
     * Coupon
     */

    val couponProduct1 = Coupon(
        1,
        product1.id,
        "Name coupon one",
        "Description coupon one",
        "50%",
        "Descuento",
        123456,
        654321,
        "Available and condition",
        "condition only",
        "condition description"
    )
    val couponProduct2 = Coupon(
        2,
        product2.id,
        "Name coupon two",
        "Description coupon two",
        "20%",
        "Descuento",
        123456,
        654321,
        "Available and condition 2",
        "condition only 2",
        "condition description 2"
    )

    /**
     * Selected
     */
    val selectedUser1Coupon1 = Selected(0, user1.id, couponProduct1.id)
    val selectedUser1Coupon2 = Selected(0, user1.id, couponProduct2.id)
}