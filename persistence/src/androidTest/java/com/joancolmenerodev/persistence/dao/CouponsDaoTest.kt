package com.joancolmenerodev.persistence.dao

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.joancolmenerodev.persistence.PersistenceModelsTest
import com.joancolmenerodev.persistence.base.BasePersistenceTest
import com.joancolmenerodev.persistence.base.flattenToList
import com.joancolmenerodev.persistence.entities.CouponsByUser
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4ClassRunner::class)
class CouponsDaoTest : BasePersistenceTest() {


    @Test
    fun findAllCouponsByUser() = runBlocking {
        //ASSIGN
        userDao.insert(PersistenceModelsTest.user1)
        productDao.insert(PersistenceModelsTest.product1)
        productDao.insert(PersistenceModelsTest.product2)
        couponDao.insert(PersistenceModelsTest.couponProduct1)
        couponDao.insert(PersistenceModelsTest.couponProduct2)
        selectedDao.insert(PersistenceModelsTest.selectedUser1Coupon1)


        //ACT
        val result =
            couponDao.findAllCouponByUser(PersistenceModelsTest.user1.id).take(1).flattenToList()

        //ASSERT
        assertTrue { result.first().selected }
        assertTrue { !result.last().selected }
    }

    @Test
    fun findCouponsByUserWhenSendingCouponId() = runBlocking {
        //ASSIGN
        val expectedResult = CouponsByUser(
            PersistenceModelsTest.couponProduct1,
            PersistenceModelsTest.product1,
            false
        )
        userDao.insert(PersistenceModelsTest.user1)
        productDao.insert(PersistenceModelsTest.product1)
        couponDao.insert(PersistenceModelsTest.couponProduct1)

        //ACT
        val result =
            couponDao.findCouponByUserAndCoupon(
                PersistenceModelsTest.user1.id,
                PersistenceModelsTest.couponProduct1.id
            )

        //ASSERT
        assertTrue { result == expectedResult }
    }

}