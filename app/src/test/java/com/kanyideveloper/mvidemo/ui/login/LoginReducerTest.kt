package com.kanyideveloper.mvidemo.ui.login

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LoginReducerTest {


    /**
     * Pattern to follow when writing the tests
     *
     * Arrange
     *
     * Act
     *
     * Assert
     *
     */


    @Test
    fun emailChangedUpdatesEmail() {
        // Given
        val inputState = LoginState()
        val inputAction = LoginAction.EmailChanged(
            "joe@gmail.com"
        )

        val expectedState = inputState.copy(
            email = "joe@gmail.com",
            emailError = null
        )

        // When
        val reducer = LoginReducer()
        val newState = reducer.reduce(inputState, inputAction)

        // Then
        assertThat(newState).isEqualTo(expectedState)
    }

    @Test
    fun passwordChangedUpdatesPassword() {
        val currentState = LoginState()
        val action = LoginAction.PasswordChanged(
            newPassword = "12345678"
        )

        val expectedState = currentState.copy(
            password = "12345678",
            passwordError = null
        )

        val reducer = LoginReducer()
        val newState = reducer.reduce(currentState, action)

        assertThat(newState).isEqualTo(expectedState)
    }

    @Test
    fun loginStartedShowsCircularProgressbar() {
        val currentState = LoginState()
        val action = LoginAction.LoginStated

        val reducer = LoginReducer()
        val newState = reducer.reduce(currentState, action)

        assertThat(newState.isLoading).isTrue()
    }

    @Test
    fun unsupportedLoginActionReturnsOriginalState() {
        val currentState = LoginState()
        val action = LoginAction.SignInButtonClicked

        val reducer = LoginReducer()
        val newState = reducer.reduce(currentState, action)

        assertThat(newState).isEqualTo(currentState)
    }

    @Test
    fun emptyEmailSubmittedReturnsAnError() {
        val currentState = LoginState()
        val action = LoginAction.EmptyEmailSubmitted

        val expectedState = currentState.copy(
            emailError = "Please input an email"
        )

        val reducer = LoginReducer()
        val newState = reducer.reduce(currentState, action)

        assertThat(newState).isEqualTo(expectedState)
    }

    @Test
    fun emptyPasswordSubmittedReturnsAnError() {
        val currentState = LoginState()
        val action = LoginAction.EmptyPasswordSubmitted

        val expectedState = currentState.copy(
            passwordError = "Please input a password"
        )

        val reducer = LoginReducer()
        val newState = reducer.reduce(currentState, action)

        assertThat(newState).isEqualTo(expectedState)
    }

    @Test
    fun onLoginCompletedCircularProgressbarIsHidden() {
        val currentState = LoginState()
        val action = LoginAction.LoginCompleted

        val reducer = LoginReducer()
        val newState = reducer.reduce(currentState, action)

        assertThat(newState.isLoading).isFalse()
    }
}