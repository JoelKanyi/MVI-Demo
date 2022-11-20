package com.kanyideveloper.mvidemo.ui.login

import com.kanyideveloper.mvidemo.redux.Reducer

/**
 * This reducer is responsible for handling any [LoginAction], and using that to create a new [LoginState]
 */
class LoginReducer : Reducer<LoginState, LoginAction> {

    override fun reduce(currentState: LoginState, action: LoginAction): LoginState {
        return when (action) {
            is LoginAction.EmailChanged -> {
                stateWithNewEmail(currentState, action)
            }
            is LoginAction.PasswordChanged -> {
                stateWithNewPassword(currentState, action)
            }
            LoginAction.LoginStated -> {
                stateAfterLoginStated(currentState)
            }
            LoginAction.LoginCompleted -> {
                stateAfterLoginCompleted(currentState)
            }
            is LoginAction.LoginFailed -> {
                stateAfterLoginFailed(currentState)
            }
            LoginAction.EmptyEmailSubmitted -> {
                stateWithInvalidEmail(currentState)
            }
            LoginAction.EmptyPasswordSubmitted -> {
                stateWithInvalidPassword(currentState)
            }
            else -> currentState
        }
    }

    private fun stateWithInvalidPassword(currentState: LoginState) =
        currentState.copy(
            passwordError = "Please input a password"
        )

    private fun stateWithInvalidEmail(currentState: LoginState) =
        currentState.copy(
            emailError = "Please input an email"
        )

    private fun stateAfterLoginCompleted(currentState: LoginState) =
        currentState.copy(
            isLoading = false
        )

    private fun stateAfterLoginFailed(currentState: LoginState) =
        currentState.copy(
            isLoading = false
        )

    private fun stateAfterLoginStated(currentState: LoginState) =
        currentState.copy(
            isLoading = true
        )

    private fun stateWithNewPassword(
        currentState: LoginState,
        action: LoginAction.PasswordChanged
    ) = currentState.copy(
        password = action.newPassword,
        passwordError = null
    )

    private fun stateWithNewEmail(
        currentState: LoginState,
        action: LoginAction.EmailChanged
    ) = currentState.copy(
        email = action.newEmail,
        emailError = null
    )
}