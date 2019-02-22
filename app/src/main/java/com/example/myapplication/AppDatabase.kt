package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.dao.UserDao
import com.example.myapplication.data.entity.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
