package com.example.littlelemon

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.LittleLemonColors
import com.example.littlelemon.ui.theme.Typography

@Composable
fun ThemedTextField(
    value: String,
    label: String,
    usePlaceholder: Boolean = false,
    onValueChange: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    if (usePlaceholder) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            textStyle = Typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            placeholder = {
                Text(
                    text = label,
                    style = Typography.bodyMedium,
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = LittleLemonColors.White,
                unfocusedContainerColor = LittleLemonColors.White,
                focusedLabelColor = LittleLemonColors.White,
                unfocusedLabelColor = LittleLemonColors.Black,
            ),
        )
    } else {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            textStyle = Typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            label = {
                Text(
                    text = label,
                    style = Typography.bodyMedium,
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = LittleLemonColors.White,
                unfocusedContainerColor = LittleLemonColors.White,
            ),
        )
    }
}

@Preview
@Composable
fun ThemedTextFieldPreview() {
    ThemedTextField(
        value = "Value",
        label = "Label",
        onValueChange = {},
    )
}