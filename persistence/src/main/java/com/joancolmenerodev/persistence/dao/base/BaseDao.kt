package com.joancolmenerodev.persistence.dao.base

import androidx.room.*


@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t: T): Long

    @Update
    suspend fun update(t: T)

    @Delete
    suspend fun delete(t: T)
}