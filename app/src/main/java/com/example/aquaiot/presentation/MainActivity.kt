package com.example.aquaiot.presentation

import Login
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aquaiot.R
import com.example.aquaiot.presentation.screens.Home
import com.example.aquaiot.presentation.screens.Perfil
import com.example.aquaiot.presentation.sign_in.GoogleAuthUiClient
import com.example.aquaiot.presentation.sign_in.SignInViewModel
import com.example.aquaiot.presentation.ui.theme.AquaIoTTheme
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AquaIoTTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "sign_in") {
                        composable("sign_in") {
                            val viewModel = viewModel<SignInViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            LaunchedEffect(key1 = Unit) {
                                if (googleAuthUiClient.getSignedInUser() != null) {
                                    navController.navigate("home")
                                }
                            }

                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if (result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch{
                                            val signInResult = googleAuthUiClient.getSignWithIntent(result.data ?: return@launch)
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(
                                key1 = state.isSignInSuccessful
                            ) {
                                if (state.isSignInSuccessful) {
                                    navController.navigate("home") {
                                        popUpTo("sign_in") {
                                            inclusive = true
                                        }
                                    }
                                }
                            }

                            Login(
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )

                                    }
                                }
                            )


                        }

                        composable("perfil") {
                            Perfil(
                                userData = googleAuthUiClient.getSignedInUser(),
                                onSignOut = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        navController.navigate("sign_in") {
                                            popUpTo("perfil") {
                                                inclusive = true
                                            }
                                        }
                                    }
                                },
                                navbarClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable("home") {
                            Home(
                                userData = googleAuthUiClient.getSignedInUser(),
                                navbarClick = {
                                    navController.navigate("perfil")
                                }
                            )
                        }

                    }
                }
            }
        }
    }
}
