package com.joancolmenerodev.lidlcoupons.features.list.domain.usecase

import com.joancolmenerodev.lidlcoupons.base.domain.Either
import com.joancolmenerodev.lidlcoupons.features.list.domain.repository.CouponItemRepository
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UpdateCouponActivationUseCaseTest {
    private lateinit var useCase: UpdateCouponActivationUseCase
    private val repository: CouponItemRepository = mockk()

    @Before
    fun setUp() {
        useCase = UpdateCouponActivationUseCase(repository)
    }

    @Test
    fun `given the repository select a coupon then it returns an either right true `() {

        //ASSIGN
        coEvery { repository.setSelected(any(), any()) } answers {
            true
        }

        //ACT
        val result = runBlocking { useCase.selectCoupon(COUPON_ID) }

        //ASSERT
        Assert.assertEquals(
            result,
            Either.Right(true)
        )
    }

    @Test
    fun `given the repository un-select a coupon then it returns an either right false `() {

        //ASSIGN
        coEvery { repository.removeSelected(any(), any()) } answers {
            false
        }

        //ACT
        val result = runBlocking { useCase.removeSelection(COUPON_ID) }

        //ASSERT
        Assert.assertEquals(
            result,
            Either.Right(false)
        )
    }

    companion object {
        private const val COUPON_ID = 0
    }
}