package com.joancolmenerodev.persistence.dao

import androidx.room.Dao
import com.joancolmenerodev.persistence.dao.base.BaseDao
import com.joancolmenerodev.persistence.entities.Product

@Dao
interface ProductDao : BaseDao<Product>