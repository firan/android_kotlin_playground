package com.example.myapplication.usecase.account

import com.example.myapplication.data.entity.User
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.data.to.UserRequest
import com.example.myapplication.data.to.UserResponse
import com.example.myapplication.data.webservice.APIClient
import com.example.myapplication.data.webservice.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.*
import java.util.concurrent.Executor

class RegisterAccount(
    private val authStateManager: AuthStateManager,
    private val userRepository : UserRepository,
    private val diskIOExecutor: Executor
) {
    companion object {
        const val USER_EXISTS_CODE = 409
    }

    fun register(user: UserRequest, successHandler: () -> Unit, failureHandler: (Throwable?) -> Unit) {
        val apiInterface = APIClient.client.create(APIInterface::class.java)
        val call = apiInterface.register(user)
        call.enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable?) {
                if (t != null) {
                    Timber.e(t, "RegisterWebservice failure")
                }
                failureHandler(t)
            }

            override fun onResponse(call: Call<UserResponse>?, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    Timber.v("Register successfully")
                    val body = response.body() as UserResponse
                    storeAuthState(body)
                    storeAccountInformation(body, user)
                    successHandler()
                } else {
                    if (response.code() == USER_EXISTS_CODE) {
                        Timber.e("Register failed, user with this e-mail exists")
                        failureHandler(UserExistsException())
                        return
                    }
                }
            }

        })
    }

    private fun storeAuthState(response: UserResponse) {
        authStateManager.update(response.token ?: return)
    }

    private fun storeAccountInformation(response: UserResponse, request: UserRequest) {
        val userId = response.userId ?: return
        val user = User(request.userName, request.email, userId, Date())
        diskIOExecutor.execute {
            userRepository.registerUser(user)
        }
    }
}