package com.example.myapplication.view.fragment.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_start.*
import org.koin.android.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {

    private val startViewModel: StartViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments ?: return
        val args = StartFragmentArgs.fromBundle(bundle)

        startViewModel.loadUserData(args.userName)
        startViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            welcomeWithNameTv.text = user.firstName
        })
    }
}
