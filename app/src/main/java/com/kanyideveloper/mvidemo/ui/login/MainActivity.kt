package com.kanyideveloper.mvidemo.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kanyideveloper.mvidemo.ui.theme.MVIDemoTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVIDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel: LoginViewModel by viewModels()

                    val viewState = viewModel.viewState.collectAsState()

                    val keyboardController = LocalSoftwareKeyboardController.current

                    Box(modifier = Modifier.fillMaxSize()) {

                        if (viewState.value.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            /**
                             * Textfield for Email
                             */
                            Column(modifier = Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = viewState.value.email,
                                    onValueChange = {
                                        viewModel.emailChanged(it)
                                    },
                                    placeholder = {
                                        Text(text = "Email")
                                    },
                                    isError = viewState.value.emailError != null
                                )
                                if (viewState.value.emailError != null) {
                                    Text(
                                        text = viewState.value.emailError
                                            ?: "Field cannot be empty",
                                        color = MaterialTheme.colors.error,
                                        fontSize = 10.sp
                                    )
                                }
                            }

                            /**
                             * Textfield for Password
                             */
                            Column(modifier = Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = viewState.value.password,
                                    onValueChange = {
                                        viewModel.passwordChanged(it)
                                    },
                                    placeholder = {
                                        Text(text = "Password")
                                    },
                                    isError = viewState.value.passwordError != null
                                )
                                if (viewState.value.passwordError != null) {
                                    Text(
                                        text = viewState.value.passwordError
                                            ?: "Field cannot be empty",
                                        color = MaterialTheme.colors.error,
                                        fontSize = 10.sp
                                    )
                                }
                            }

                            /**
                             * Submit Button
                             */
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    keyboardController?.hide()
                                    viewModel.signInButtonClicked()
                                }) {
                                Text(
                                    modifier = Modifier.padding(8.dp),
                                    text = "Sign In"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}