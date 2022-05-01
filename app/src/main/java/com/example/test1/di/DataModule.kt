package com.example.test1.di

import com.example.test1.data.repository.UserRepositoryImpl
import com.example.test1.data.storage.UserStorage
import com.example.test1.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.test1.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {

    single<UserStorage> {
        SharedPrefUserStorage(context = get())
    }

    single<UserRepository> {
        UserRepositoryImpl(userStorage = get())
    }

}