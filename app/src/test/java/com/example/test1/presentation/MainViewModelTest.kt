package com.example.test1.presentation

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.example.test1.domain.models.SaveUserNameParam
import com.example.test1.domain.models.UserName
import com.example.test1.domain.usecase.GetUserNameUseCase
import com.example.test1.domain.usecase.SaveUserNameUseCase
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import org.mockito.kotlin.mock

class MainViewModelTest {

    val getUserNameUseCase = mock<GetUserNameUseCase>()
    val saveUserNameUseCase = mock<SaveUserNameUseCase>()

    @BeforeEach
    fun beforeEach() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }
        })
    }

    @AfterEach
    fun afterEach() {
        ArchTaskExecutor.getInstance().setDelegate(null)
        Mockito.reset(getUserNameUseCase)
        Mockito.reset(saveUserNameUseCase)
    }

    @Test
    fun `should save username and return true`() {
        val saveResult = true
        val testSaveText = "Test user name"
        val testParams = SaveUserNameParam(name = testSaveText)

        Mockito.`when`(saveUserNameUseCase.execute(param = testParams))
            .thenReturn(saveResult)

        val viewModel = MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        )

        viewModel.save(text = testSaveText)

        val expected = "Save result = true"
        val actual = viewModel.resultLive.value

        Mockito.verify(saveUserNameUseCase, Mockito.times(1))
            .execute(param = testParams)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `should save username and return false`() {
        val saveResult = false
        val testSaveText = "Test user name"
        val testParams = SaveUserNameParam(name = testSaveText)

        Mockito.`when`(saveUserNameUseCase.execute(param = testParams))
            .thenReturn(saveResult)

        val viewModel = MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        )

        viewModel.save(text = testSaveText)

        val expected = "Save result = false"
        val actual = viewModel.resultLive.value

        Mockito.verify(saveUserNameUseCase, Mockito.times(1))
            .execute(param = testParams)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `should load username`() {
        val testUserName = UserName(
            firstName = "Test first name",
            lastName = "Test last name"
        )
        Mockito.`when`(getUserNameUseCase.execute()).thenReturn(testUserName)

        val viewModel = MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        )

        viewModel.load()
        val expected = "${testUserName.firstName} ${testUserName.lastName}"
        val actual = viewModel.resultLive.value

        Mockito.verify(getUserNameUseCase, Mockito.times(1))
            .execute()
        Assertions.assertEquals(expected, actual)
    }
}