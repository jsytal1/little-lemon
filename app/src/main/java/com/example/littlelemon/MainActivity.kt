package com.example.littlelemon

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                AppScreen(sharedPreferences)
            }
        }
    }
}

@Composable
private fun AppScreen(sharedPreferences: SharedPreferences) {
    Scaffold() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            MyNavigation(sharedPreferences)
        }
    }
}

@Composable
fun MyNavigation(sharedPreferences: SharedPreferences) {
    val firstName = sharedPreferences.getString("firstName", "") ?: ""
    val lastName = sharedPreferences.getString("lastName", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""

    val startDestination = if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
        Onboarding.route
    } else {
        Home.route
    }
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable(Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Onboarding.route) {
            OnboardingScreen(navController = navController, sharedPreferences = sharedPreferences)
        }
        composable(Profile.route) {
            ProfileScreen(navController = navController, sharedPreferences)
        }
    }
}
