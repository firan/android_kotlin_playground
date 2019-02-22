package com.example.myapplication.view.fragment.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.entity.User
import com.example.myapplication.data.repository.UserRepository

class StartViewModel(private val userRepository : UserRepository) : ViewModel() {
    lateinit var user: LiveData<User>

    fun loadUserData(userName: String) {
        user = userRepository.findUserByName(name = userName)
    }
}