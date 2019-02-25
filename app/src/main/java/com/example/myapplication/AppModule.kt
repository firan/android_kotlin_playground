package com.example.myapplication

import androidx.room.Room
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.data.repository.UserRepositoryImpl
import com.example.myapplication.usecase.account.AuthStateManager
import com.example.myapplication.usecase.account.RegisterAccount
import com.example.myapplication.usecase.account.SecureSharedPrefsStorage
import com.example.myapplication.usecase.account.SecureStorage
import com.example.myapplication.view.activity.registeractivity.RegisterActivityViewModel
import com.example.myapplication.view.fragment.start.StartViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import java.util.concurrent.Executor
import java.util.concurrent.Executors

const val NETWORK_IO_EXECUTOR = "network_io_executor"
const val DISK_IO_EXECUTOR = "disk_io_executor"
val AppModule = module {

    single<Executor>(NETWORK_IO_EXECUTOR) { Executors.newFixedThreadPool(4) }
    single<Executor>(DISK_IO_EXECUTOR) { Executors.newSingleThreadExecutor() }

    // Room Database
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "application-db")
            .build()
    }

    // Dao
    single { get<AppDatabase>().userDao() }

    // Repositories
    single<UserRepository> { UserRepositoryImpl(get(), get(DISK_IO_EXECUTOR)) }

    // View Models
    viewModel { StartViewModel(get()) }
    viewModel { RegisterActivityViewModel(get()) }

    // Usecases
    factory { RegisterAccount(get(), get(), get(DISK_IO_EXECUTOR)) }
    factory<SecureStorage> { SecureSharedPrefsStorage(get()) }
    factory { AuthStateManager(get()) }
}