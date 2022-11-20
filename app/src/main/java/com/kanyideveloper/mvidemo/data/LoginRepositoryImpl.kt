package com.kanyideveloper.mvidemo.data

import kotlinx.coroutines.delay

class LoginRepositoryImpl : LoginRepository {
    override suspend fun login(email: String, password: String): Boolean {
        delay(2000)
        return true
    }
}