package com.kanyideveloper.mvidemo

import com.kanyideveloper.mvidemo.data.FakeLoginRepository
import com.kanyideveloper.mvidemo.middleware.LoginNetworkingMiddleware
import com.kanyideveloper.mvidemo.redux.Store
import com.kanyideveloper.mvidemo.ui.login.LoginAction
import com.kanyideveloper.mvidemo.ui.login.LoginReducer
import com.kanyideveloper.mvidemo.ui.login.LoginState
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LoginNetworkingMiddlewareTest {

    @Test
    fun submitInvalidEmail() {
        // Givens
        val fakeLoginRepository = FakeLoginRepository()
        fakeLoginRepository.shouldMockSuccess = true

        val inputState = LoginState(email = "")
        val action = LoginAction.SignInButtonClicked

        val middlewareUnderTest = LoginNetworkingMiddleware(fakeLoginRepository)
        val actionCaptureMiddleware = ActionCaptureMiddleware<LoginState, LoginAction>()

        val loginStore = Store(
            inputState,
            LoginReducer(),
            listOf(actionCaptureMiddleware)
        )

        // When
        runBlocking {
            middlewareUnderTest.process(action, inputState, loginStore)
        }

        // Then
        actionCaptureMiddleware.assertActionProcessed(LoginAction.EmptyEmailSubmitted)
    }


    @Test
    fun loginSuccess() {
        // Givens
        val fakeLoginRepository = FakeLoginRepository()
        fakeLoginRepository.shouldMockSuccess = true

        val inputState = LoginState(
            email = "joe@gmail.com",
            password = "12345678"
        )
        val action = LoginAction.SignInButtonClicked

        val middlewareUnderTest = LoginNetworkingMiddleware(fakeLoginRepository)
        val actionCaptureMiddleware = ActionCaptureMiddleware<LoginState, LoginAction>()

        val loginStore = Store(
            inputState,
            LoginReducer(),
            listOf(actionCaptureMiddleware)
        )

        // When
        runBlocking {
            middlewareUnderTest.process(action, inputState, loginStore)
        }

        // Then
        actionCaptureMiddleware.assertActionProcessed(LoginAction.LoginStated)
        actionCaptureMiddleware.assertActionProcessed(LoginAction.LoginCompleted)
    }


    @Test
    fun loginFailure() {
        // Givens
        val fakeLoginRepository = FakeLoginRepository()
        fakeLoginRepository.shouldMockSuccess = false

        val inputState = LoginState(
            email = "joe@gmail.com",
            password = "12345678"
        )
        val action = LoginAction.SignInButtonClicked

        val middlewareUnderTest = LoginNetworkingMiddleware(fakeLoginRepository)
        val actionCaptureMiddleware = ActionCaptureMiddleware<LoginState, LoginAction>()

        val loginStore = Store(
            inputState,
            LoginReducer(),
            listOf(actionCaptureMiddleware)
        )

        // When
        runBlocking {
            middlewareUnderTest.process(action, inputState, loginStore)
        }

        // Then
        actionCaptureMiddleware.assertActionProcessed(LoginAction.LoginStated)
        actionCaptureMiddleware.assertActionProcessed(LoginAction.LoginFailed(null))
    }

}