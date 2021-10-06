package com.joancolmenerodev.persistence.dao

import androidx.room.Dao
import com.joancolmenerodev.persistence.dao.base.BaseDao
import com.joancolmenerodev.persistence.entities.User

@Dao
interface UserDao : BaseDao<User>