package com.kanyideveloper.mvidemo.data

interface LoginRepository {
    suspend fun login(email: String, password: String): Boolean
}