package com.kanyideveloper.mvidemo.redux

interface Reducer<S : State, A : Action> {

    /**
     * Given some [currentState] and some [action] that the user took, produce a new [State]
     */
    fun reduce(currentState: S, action: A): S
}