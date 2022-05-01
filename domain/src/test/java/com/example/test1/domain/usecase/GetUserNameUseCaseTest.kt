package com.example.test1.domain.usecase

import com.example.test1.domain.models.UserName
import com.example.test1.domain.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetUserNameUseCaseTest {

    private val userRepository = mock<UserRepository>()

    @Test
    fun `should the same data as in repository`() {

        val testUserName = UserName(firstName = "test first name", lastName = "test last name")
        Mockito.`when`(userRepository.getName()).thenReturn(testUserName)

        val useCase = GetUserNameUseCase(userRepository = userRepository)
        val actual = useCase.execute()
        val expected = UserName(firstName = "test first name", lastName = "test last name")

        Assertions.assertEquals(expected, actual)
    }
}