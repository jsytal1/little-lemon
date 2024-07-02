package com.example.littlelemon

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.LittleLemonColors
import com.example.littlelemon.ui.theme.Shapes
import com.example.littlelemon.ui.theme.Typography

@Composable
fun ThemedTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                style = Typography.bodyMedium,
                color = LittleLemonColors.Black
            )
        },
        keyboardOptions = keyboardOptions,
        textStyle = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ThemedTextFieldPreview() {
    ThemedTextField(value = "Value", label = "Label", onValueChange = {})
}