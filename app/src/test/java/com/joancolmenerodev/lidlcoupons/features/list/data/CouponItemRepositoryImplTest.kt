package com.joancolmenerodev.lidlcoupons.features.list.data

import com.joancolmenerodev.lidlcoupons.features.list.domain.exceptions.CouponItemExceptions
import com.joancolmenerodev.lidlcoupons.features.list.domain.repository.CouponItemRepository
import com.joancolmenerodev.persistence.dao.SelectedDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CouponItemRepositoryImplTest {

    private lateinit var couponItemRepository: CouponItemRepository
    private val mockSelectedDao: SelectedDao = mockk()

    @Before
    fun setUp() {
        couponItemRepository =
            CouponItemRepositoryImpl(mockSelectedDao)
    }

    @Test
    fun `given user asks for the selection of a coupon it returns true when is succeed`() {
        //ASSIGN
        coEvery { mockSelectedDao.insert(any()) } answers { Long.MAX_VALUE }

        //ACT
        val result =
            runBlocking { couponItemRepository.setSelected(USER_ID, COUPON_ID) }

        //ASSERT
        coVerify(exactly = 1) {
            mockSelectedDao.insert(any())
        }
        assertTrue(result)

    }

    @Test(expected = CouponItemExceptions.CouponSetSelectedException::class)
    fun `given user asks for the selection of a coupon but it's already selected it returns an exception`() {
        //ASSIGN
        coEvery { mockSelectedDao.insert(any()) } answers { Long.MIN_VALUE }

        //ACT
        runBlocking { couponItemRepository.setSelected(USER_ID, COUPON_ID) }
    }

    @Test(expected = CouponItemExceptions.CouponItemKnownException::class)
    fun `given user asks for the selection of a coupon but there's a problem with the database`() {
        //ASSIGN
        coEvery { mockSelectedDao.insert(any()) } throws Exception()

        //ACT
        runBlocking { couponItemRepository.setSelected(USER_ID, COUPON_ID) }
    }

    @Test
    fun `given user asks for the un-selection of a coupon it returns false when is succeed`() {
        //ASSIGN
        coEvery { mockSelectedDao.removeSelected(any(), any()) } answers { Int.MAX_VALUE }

        //ACT
        val result =
            runBlocking { couponItemRepository.removeSelected(USER_ID, COUPON_ID) }

        //ASSERT
        coVerify(exactly = 1) {
            mockSelectedDao.removeSelected(USER_ID, COUPON_ID)
        }
        assertFalse(result)

    }

    @Test(expected = CouponItemExceptions.CouponUnSelectException::class)
    fun `given user asks for the un-selection of a coupon but it's already selected it returns an exception`() {
        //ASSIGN
        coEvery { mockSelectedDao.removeSelected(any(), any()) } answers { NO_ROWS_DELETED }

        //ACT
        runBlocking { couponItemRepository.removeSelected(USER_ID, COUPON_ID) }
    }

    @Test(expected = CouponItemExceptions.CouponItemKnownException::class)
    fun `given user asks for the un-selection of a coupon but there's a problem with the database`() {
        //ASSIGN
        coEvery { mockSelectedDao.removeSelected(any(), any()) } throws Exception()

        //ACT
        runBlocking { couponItemRepository.removeSelected(USER_ID, COUPON_ID) }
    }


    companion object {
        private const val USER_ID = 1
        private const val COUPON_ID = 1
        private const val NO_ROWS_DELETED = 0
    }
}