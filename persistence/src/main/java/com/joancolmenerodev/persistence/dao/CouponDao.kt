package com.joancolmenerodev.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.joancolmenerodev.persistence.dao.base.BaseDao
import com.joancolmenerodev.persistence.entities.Coupon
import com.joancolmenerodev.persistence.entities.CouponsByUser
import kotlinx.coroutines.flow.Flow

@Dao
interface CouponDao : BaseDao<Coupon> {

    /**
     * Query to find all the coupons available and creating a field to know if the user that is asking for the coupons have marked any coupon as a selected.
     * returns a List<CouponsByUser>
     *     Coupon
     *     Product
     *     Boolean - isSelected
     */

    @Query(
        "SELECT *, " +
                "CASE WHEN TB_SELECTED.selected_user_id is null THEN 0 else 1 END as selected " +
                "FROM TB_COUPON \n" +
                "INNER JOIN TB_PRODUCT ON TB_COUPON.coupon_product_id = TB_PRODUCT.product_id \n" +
                "LEFT JOIN TB_SELECTED ON TB_COUPON.coupon_id = TB_SELECTED.selected_coupon_id \n" +
                "AND TB_SELECTED.selected_user_id = :userId"
    )
    fun findAllCouponByUser(userId: Int): Flow<List<CouponsByUser>>

    @Query(
        "SELECT *, " +
                "CASE WHEN TB_SELECTED.selected_user_id is null THEN 0 else 1 END as selected " +
                "FROM TB_COUPON \n" +
                "INNER JOIN TB_PRODUCT ON TB_COUPON.coupon_product_id = TB_PRODUCT.product_id \n" +
                "LEFT JOIN TB_SELECTED ON TB_COUPON.coupon_id = TB_SELECTED.selected_coupon_id \n" +
                "AND TB_SELECTED.selected_user_id = :userId WHERE TB_COUPON.coupon_id = :couponId"
    )
    fun findCouponByUserAndCoupon(userId: Int, couponId: Int): CouponsByUser
}