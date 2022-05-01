package com.example.test1.domain.usecase

import com.example.test1.domain.models.UserName
import com.example.test1.domain.repository.UserRepository

class GetUserNameUseCase(private val userRepository: UserRepository) {

    fun execute(): UserName {
        return userRepository.getName()
    }

}