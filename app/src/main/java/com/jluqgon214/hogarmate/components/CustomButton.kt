// CustomButton.kt
    package com.jluqgon214.hogarmate.components

    import androidx.compose.foundation.layout.size
    import androidx.compose.material3.Button
    import androidx.compose.material3.ButtonDefaults
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.unit.dp
    import com.jluqgon214.hogarmate.ui.theme.GreenPrimary

    @Composable
    fun CustomButton(onClick: () -> Unit, content: @Composable () -> Unit) {
        Button(
            modifier = Modifier.size(150.dp, 60.dp),
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenPrimary,
                contentColor = Color.White
            )
        ) {
            content()
        }
    }