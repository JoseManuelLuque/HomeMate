package com.jluqgon214.hogarmate.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.jluqgon214.hogarmate.ui.theme.GreenPrimary
import com.jluqgon214.hogarmate.ui.theme.GreenSecondary
import com.jluqgon214.hogarmate.ui.theme.GreenTertiary

@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        singleLine = true,
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = GreenPrimary,
            unfocusedBorderColor = GreenTertiary,
            cursorColor = GreenPrimary,
            focusedLabelColor = GreenSecondary
        )
    )
}