package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.dao.UserDao
import com.example.myapplication.data.entity.User
import com.example.myapplication.usecase.AppExecutors

interface UserRepository {
    fun registerUser(user: User)
    fun findUserByName(name: String): LiveData<User>
}

class UserRepositoryImpl(private val userDao: UserDao, private val executors: AppExecutors) : UserRepository {
    override fun registerUser(user: User) {
        executors.diskIO().execute {
            userDao.insertAll(user)
        }
    }

    override fun findUserByName(name: String): LiveData<User> {
        return userDao.findByName(name, name)
    }
}