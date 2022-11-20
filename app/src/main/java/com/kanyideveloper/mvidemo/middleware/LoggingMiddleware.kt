package com.kanyideveloper.mvidemo.middleware

import android.util.Log
import com.kanyideveloper.mvidemo.redux.Action
import com.kanyideveloper.mvidemo.redux.Middleware
import com.kanyideveloper.mvidemo.redux.State
import com.kanyideveloper.mvidemo.redux.Store

/**
 * This [Middleware] is responsible for logging every [Action] that is processed to the Logcat
 * so that we can see the flow of our data for logging purposes
 */

class LoggingMiddleware<S : State, A : Action> : Middleware<S, A> {
    override suspend fun process(action: A, currentState: S, store: Store<S, A>) {
        Log.v(
            "LoggingMiddleware",
            "Processing action: $action ; Current state: $currentState"
        )
    }
}