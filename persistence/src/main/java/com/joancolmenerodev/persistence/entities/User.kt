package com.joancolmenerodev.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(value = ["user_id", "user_name"])],
    tableName = "TB_USER"
)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id: Int,

    @ColumnInfo(name = "user_name")
    val name: String
)