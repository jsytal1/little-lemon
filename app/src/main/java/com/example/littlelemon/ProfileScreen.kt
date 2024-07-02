package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController, sharedPreferences: SharedPreferences) {
    val firstName = sharedPreferences.getString("firstName", "") ?: ""
    val lastName = sharedPreferences.getString("lastName", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""

    Column {
        TopBar()

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Profile(firstName, lastName, email)
            ThemedButton(
                onClick = {
                    sharedPreferences.edit().clear().apply()
                    navController.navigate(Onboarding.route)
                }, text = stringResource(id = R.string.logout)
            )
        }
    }
}

@Composable
fun Profile(firstName: String, lastName: String, email: String) {
    Column {
        Text(
            text = stringResource(id = R.string.profile_information),
            modifier = Modifier.padding(bottom = 24.dp)
        )
        ThemedTextField(
            value = firstName,
            label = stringResource(id = R.string.first_name_label),
        )
        ThemedTextField(
            value = lastName,
            label = stringResource(id = R.string.last_name_label),
        )
        ThemedTextField(
            value = email,
            label = stringResource(id = R.string.email_label),
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile("firstname", "lastname", "email")
}


@Composable
fun TopBar( ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .fillMaxWidth(.5f)
                .align(Alignment.CenterVertically)
                .height(48.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

