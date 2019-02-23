package com.example.myapplication.view.fragment.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.entity.User
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.data.to.UserRequest
import com.example.myapplication.data.webservice.RegisterWebservice

class LoginViewModel(private val userRepository : UserRepository, private val registerWebservice: RegisterWebservice, private val context: Context) : ViewModel() {
    fun registerUser(user: User) {
        registerWebservice.register(UserRequest(user.firstName, "password", null, null), context)
        userRepository.registerUser(user = user)
    }
}