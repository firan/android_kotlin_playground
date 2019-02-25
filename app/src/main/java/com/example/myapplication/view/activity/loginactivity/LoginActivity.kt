package com.example.myapplication.view.activity.loginactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.view.activity.registeractivity.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonRegister.setOnClickListener {
            val activityIntent = Intent(this, RegisterActivity::class.java)
            startActivity(activityIntent)
        }
    }
}
