package com.joancolmenerodev.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.joancolmenerodev.persistence.dao.base.BaseDao
import com.joancolmenerodev.persistence.entities.Selected

@Dao
interface SelectedDao : BaseDao<Selected> {

    /**
     * Method to mark a coupon as a selected for an user
     */

    @Query("INSERT INTO TB_SELECTED VALUES (0,:userId, :couponId)")
    suspend fun setSelected(userId: Int, couponId: Int): Long

    /**
     * Method to remove the selected coupon from an user
     */

    @Query("DELETE FROM TB_SELECTED WHERE selected_user_id=:userId AND selected_coupon_id=:couponId")
    suspend fun removeSelected(userId: Int, couponId: Int): Int
}