package com.example.myapplication.data.webservice

import com.example.myapplication.data.to.UserRequest
import android.widget.Toast
import android.content.Context
import com.example.myapplication.data.to.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


interface RegisterWebservice {
    fun register(user: UserRequest, context: Context)
}

class RegisterWebserviceImpl() : RegisterWebservice {

    override fun register(user: UserRequest, context: Context) {
        val apiInterface = APIClient.client.create(APIInterface::class.java)
        val call = apiInterface.register(user)
        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Toast.makeText(context, t!!.message.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                Toast.makeText(context, response?.code().toString(), Toast.LENGTH_LONG).show()
            }

        })
    }
}