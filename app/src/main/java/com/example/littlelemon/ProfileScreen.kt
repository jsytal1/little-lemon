package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {
    ThemedButton(
        onClick = { navController.navigate(Onboarding.route) },
        text = stringResource(id = R.string.logout)
    )
}
