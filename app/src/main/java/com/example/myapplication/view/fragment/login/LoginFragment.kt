package com.example.myapplication.view.fragment.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.entity.User
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonWelcomeMe.setOnClickListener{
            val name = enterName.text.toString()
            if(name.isEmpty()) {
                enterName.error = "Please enter a name"
            } else {
                loginViewModel.registerUser(User(firstName = name, lastName = name))
                val action =
                    LoginFragmentDirections.actionLoginFragmentToStartFragment(
                        name
                    )
                findNavController().navigate(action)
            }
        }
    }
}
