package com.kanyideveloper.mvidemo.data

/**
 * Custom implementation of [LoginRepository] that allows the developer in the unit tests
 * to determine if a login should behave as a success or failure scenario, by setting the [shouldMockSuccess]
 * boolean accordingly.
 */
class FakeLoginRepository : LoginRepository {

    var shouldMockSuccess: Boolean = true

    override suspend fun login(email: String, password: String): Boolean {
        return shouldMockSuccess
    }
}