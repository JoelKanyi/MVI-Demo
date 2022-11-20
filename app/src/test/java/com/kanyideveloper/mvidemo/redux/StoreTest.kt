package com.kanyideveloper.mvidemo.redux

import com.google.common.truth.Truth.assertThat
import com.kanyideveloper.mvidemo.ActionCaptureMiddleware
import kotlinx.coroutines.runBlocking
import org.junit.Test

class StoreTest {
    @Test
    fun dispatchSendsActionsToReducerAndMiddlewares() {
        // Given
        val inputState = TestState()
        val inputAction = TestAction
        val reducer = TestReducer()
        val middleware = ActionCaptureMiddleware<State, Action>()

        val store = Store(
            inputState,
            reducer,
            listOf(middleware)
        )

        // When
        runBlocking {
            store.dispatch(inputAction)
        }

        // Then
        middleware.assertActionProcessed(inputAction)
        reducer.assertActionProcessed(inputAction)
    }
}


data class TestState(
    val isCompleted: Boolean = false,
) : State

object TestAction : Action


class TestReducer : Reducer<State, Action> {
    private val actionHistory: MutableList<Action> = mutableListOf()
    override fun reduce(currentState: State, action: Action): State {
        actionHistory.add(action)
        return currentState
    }

    fun assertActionProcessed(expectedAction: Action) {
        assertThat(actionHistory).contains(expectedAction)
    }
}