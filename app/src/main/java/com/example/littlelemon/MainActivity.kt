package com.example.littlelemon

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                AppScreen(sharedPreferences, database)
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                val menuItemsNetwork = fetchMenu()
                saveMenuToDatabase(menuItemsNetwork)
                runOnUiThread {
                    Log.d("menuItemsNetwork", menuItemsNetwork.toString())
                }
            }
        }
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        return httpClient
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetwork>()
            .menu
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }

}

@Composable
private fun AppScreen(sharedPreferences: SharedPreferences, database: AppDatabase) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            MyNavigation(sharedPreferences, database)
        }
    }
}

@Composable
fun MyNavigation(sharedPreferences: SharedPreferences, database: AppDatabase) {
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
            HomeScreen(navController = navController, database = database)
        }
        composable(Onboarding.route) {
            OnboardingScreen(navController = navController, sharedPreferences = sharedPreferences)
        }
        composable(Profile.route) {
            ProfileScreen(navController = navController, sharedPreferences)
        }
    }
}
