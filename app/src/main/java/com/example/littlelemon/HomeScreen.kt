package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    ThemedButton(
        onClick = { navController.navigate(Profile.route) },
        text = stringResource(id = R.string.profile_button)
    )
}
