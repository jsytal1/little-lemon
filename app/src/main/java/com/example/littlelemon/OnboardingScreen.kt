package com.example.littlelemon

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.LittleLemonColors
import com.example.littlelemon.ui.theme.Typography

@Composable
fun OnboardingScreen(navController: NavController, sharedPreferences: SharedPreferences) {
    val context = LocalContext.current
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TopBar()
        UpperPanel()
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .background(LittleLemonColors.White)
                .padding(16.dp),
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {

                Text(
                    text = stringResource(id = R.string.onboarding_subtitle),
                    color = LittleLemonColors.Black,
                    style = Typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp, top = 24.dp)
                )
                ThemedTextField(
                    value = firstName,
                    label = stringResource(id = R.string.first_name_label),
                    onValueChange = { firstName = it },
                )
                ThemedTextField(
                    value = lastName,
                    label = stringResource(id = R.string.last_name_label),
                    onValueChange = { lastName = it },
                )
                ThemedTextField(
                    value = email,
                    label = stringResource(id = R.string.email_label),
                    onValueChange = { email = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                )
            }
            ThemedButton(
                onClick = {
                    if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Registration unsuccessful. Please enter all data.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        sharedPreferences.edit()
                            .putString("firstName", firstName)
                            .putString("lastName", lastName)
                            .putString("email", email)
                            .apply()

                        Toast.makeText(
                            context,
                            "Registration successful!",
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.navigate(Home.route)
                    }
                }, text = stringResource(id = R.string.onboarding_register)
            )
        }
    }
}

@Composable
private fun UpperPanel() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(LittleLemonColors.DarkGreen)
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        Text(
            text = stringResource(id = R.string.onboarding_title),
            color = LittleLemonColors.White,
            style = Typography.bodyLarge
        )
    }
}