package com.example.myapplication

import androidx.room.Room
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.data.repository.UserRepositoryImpl
import com.example.myapplication.data.webservice.RegisterWebservice
import com.example.myapplication.data.webservice.RegisterWebserviceImpl
import com.example.myapplication.usecase.AppExecutors
import com.example.myapplication.view.fragment.login.LoginViewModel
import com.example.myapplication.view.fragment.start.StartViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val AppModule = module {

    // Room Database
    bean {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "application-db")
            .build()
    }

    // Dao
    bean { get<AppDatabase>().userDao() }

    // Repositories
    bean<UserRepository> { UserRepositoryImpl(get(), get()) }

    bean<RegisterWebservice> { RegisterWebserviceImpl() }

    bean { AppExecutors() }

    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { StartViewModel(get()) }
}