package com.example.test1.domain.repository

import com.example.test1.domain.models.SaveUserNameParam
import com.example.test1.domain.models.UserName

interface UserRepository {

    fun saveName(saveParam: SaveUserNameParam): Boolean

    fun getName(): UserName
}