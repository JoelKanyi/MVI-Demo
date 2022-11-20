package com.kanyideveloper.mvidemo

import com.google.common.truth.Truth.assertThat
import com.kanyideveloper.mvidemo.redux.Action
import com.kanyideveloper.mvidemo.redux.Middleware
import com.kanyideveloper.mvidemo.redux.State
import com.kanyideveloper.mvidemo.redux.Store

class ActionCaptureMiddleware<S : State, A : Action> : Middleware<S, A> {

    private val actionHistory: MutableList<A> = mutableListOf()

    override suspend fun process(action: A, currentState: S, store: Store<S, A>) {
        actionHistory.add(action)
    }

    fun assertActionProcessed(expectedAction: Action) {
        assertThat(actionHistory).contains(expectedAction)
    }
}