package com.jluqgon214.hogarmate.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.jluqgon214.hogarmate.ui.theme.GreenPrimary
import com.jluqgon214.hogarmate.ui.theme.GreenSecondary

@Composable
fun CustomButton(onClick: () -> Unit, text: String) {
    Button(
        onClick = { onClick },
        colors = ButtonDefaults.buttonColors(
            containerColor = GreenPrimary,
            contentColor = Color.White
        )
    ){
        Text(text = text)
    }
}