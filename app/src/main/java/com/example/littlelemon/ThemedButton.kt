package com.example.littlelemon

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.LittleLemonColors
import com.example.littlelemon.ui.theme.Shapes

@Composable
fun ThemedButton(onClick: () -> Unit, text: String) {
    Button(
        onClick = onClick,
        shape = Shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = LittleLemonColors.Yellow
        ),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = LittleLemonColors.Black
        )
    }
}

@Preview
@Composable
fun ThemedButtonPreview() {
    ThemedButton(onClick = {}, "Button")
}