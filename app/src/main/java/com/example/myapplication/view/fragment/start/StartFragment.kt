package com.example.myapplication.view.fragment.start

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.usecase.account.Logout
import com.example.myapplication.view.activity.loginactivity.LoginActivity
import kotlinx.android.synthetic.main.fragment_start.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {

    private val startViewModel: StartViewModel by viewModel()
    private val logout: Logout by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startViewModel.loadUserData()
        startViewModel.userAccounts.observe(viewLifecycleOwner, Observer { users ->
            if(!users.isNullOrEmpty())
                welcomeWithNameTv.text = users.first().email
        })

        buttonLogout.setOnClickListener {
            logout.perform {
                val intent = Intent(this@StartFragment.context, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }
}
