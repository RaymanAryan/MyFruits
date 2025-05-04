package com.rayman.myfruits.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


@Composable
fun ErrorDialog(
    dialogVisible: Boolean = true,
    title: String = "Error",
    message: String,
    confirmButtonText: String = "OK",
    onDismiss: () -> Unit
) {
    if(dialogVisible) {
        AlertDialog(
            icon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Error Icon",
                    tint = MaterialTheme.colorScheme.error
                )
            },
            onDismissRequest = onDismiss,
            title = { Text(text = title, color = MaterialTheme.colorScheme.error) },
            text = { Text(text = message) },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text(confirmButtonText)
                }
            }
        )
    }
}

