package com.kanyideveloper.mvidemo.middleware

import com.kanyideveloper.mvidemo.data.LoginRepository
import com.kanyideveloper.mvidemo.redux.Middleware
import com.kanyideveloper.mvidemo.redux.Store
import com.kanyideveloper.mvidemo.ui.login.LoginAction
import com.kanyideveloper.mvidemo.ui.login.LoginState

/**
 * This [Middleware] is responsible for handling [LoginAction]
 */

class LoginNetworkingMiddleware(
    private val loginRepository: LoginRepository
) : Middleware<LoginState, LoginAction> {

    override suspend fun process(
        action: LoginAction,
        currentState: LoginState,
        store: Store<LoginState, LoginAction>
    ) {
        when (action) {
            is LoginAction.SignInButtonClicked -> {

                if (currentState.email.isEmpty()) {
                    store.dispatch(LoginAction.EmptyEmailSubmitted)
                    return
                }

                if (currentState.password.isEmpty()) {
                    store.dispatch(LoginAction.EmptyPasswordSubmitted)
                    return
                }

                loginUser(currentState, store)
            }
        }
    }

    private suspend fun loginUser(
        currentState: LoginState,
        store: Store<LoginState, LoginAction>
    ) {
        store.dispatch(LoginAction.LoginStated)

        val isSuccessful = loginRepository.login(
            email = currentState.email,
            password = currentState.password
        )

        if (isSuccessful) {
            store.dispatch(LoginAction.LoginCompleted)
        } else {
            store.dispatch(LoginAction.LoginFailed(null))
        }
    }
}