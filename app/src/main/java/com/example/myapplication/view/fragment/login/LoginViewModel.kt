package com.example.myapplication.view.fragment.login

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.entity.User
import com.example.myapplication.data.repository.UserRepository

class LoginViewModel(private val userRepository : UserRepository) : ViewModel() {
    fun registerUser(user: User) {
        userRepository.registerUser(user = user)
    }
}