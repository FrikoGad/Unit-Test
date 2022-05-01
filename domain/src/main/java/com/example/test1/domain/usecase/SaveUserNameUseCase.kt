package com.example.test1.domain.usecase

import com.example.test1.domain.models.SaveUserNameParam
import com.example.test1.domain.repository.UserRepository

class SaveUserNameUseCase(private val userRepository: UserRepository) {

    fun execute(param: SaveUserNameParam): Boolean {

        val oldUserName = userRepository.getName()
        if (oldUserName.firstName == param.name) {
            return true
        }
        return userRepository.saveName(saveParam = param)
    }
}