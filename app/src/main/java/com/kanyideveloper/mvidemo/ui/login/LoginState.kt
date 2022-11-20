package com.kanyideveloper.mvidemo.ui.login

import com.kanyideveloper.mvidemo.redux.State

/**
 * An Implementation of [State] that describes the configuration of the login screen at a given time
 */
data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null
) : State
