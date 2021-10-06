package com.joancolmenerodev.persistence.dao

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.joancolmenerodev.persistence.PersistenceModelsTest
import com.joancolmenerodev.persistence.base.BasePersistenceTest
import com.joancolmenerodev.persistence.base.flattenToList
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4ClassRunner::class)
class SelectedDaoTest : BasePersistenceTest() {

    @Test
    fun insertSelectedByUser() = runBlocking {
        //ASSIGN
        userDao.insert(PersistenceModelsTest.user1)
        productDao.insert(PersistenceModelsTest.product1)
        couponDao.insert(PersistenceModelsTest.couponProduct1)

        //ACT
        selectedDao.setSelected(
            PersistenceModelsTest.user1.id,
            PersistenceModelsTest.couponProduct1.id
        )
        val result =
            couponDao.findAllCouponByUser(PersistenceModelsTest.user1.id).take(1).flattenToList()


        //ASSERT
        assertTrue { result.first().selected }
    }

    @Test
    fun removeByUser() = runBlocking {
        //ASSIGN
        userDao.insert(PersistenceModelsTest.user1)
        productDao.insert(PersistenceModelsTest.product1)
        couponDao.insert(PersistenceModelsTest.couponProduct1)
        selectedDao.insert(PersistenceModelsTest.selectedUser1Coupon1)

        //ACT
        selectedDao.removeSelected(
            PersistenceModelsTest.user1.id,
            PersistenceModelsTest.couponProduct1.id
        )
        val result =
            couponDao.findAllCouponByUser(PersistenceModelsTest.user1.id).take(1).flattenToList()


        //ASSERT
        assertFalse { result.first().selected }
    }
}

