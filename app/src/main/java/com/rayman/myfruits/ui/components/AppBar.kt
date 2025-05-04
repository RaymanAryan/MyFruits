package com.rayman.myfruits.ui.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.rayman.myfruits.ui.viewmodel.FruitViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onClick: () -> Unit, fruitViewModel: FruitViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val isRotating = rememberSaveable { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition()

    val rotation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween<Float>(
                durationMillis = 2000,
                easing = FastOutLinearInEasing
            )
        )
    )

    TopAppBar(
        modifier = Modifier,
        title = {
                Text(
                    text = "My Fruits"
                )
        },
        actions = {
            IconButton(onClick = {
                coroutineScope.launch {
                    isRotating.value = true
                    runBlocking {
                        fruitViewModel.getAllFruits()
                    }
                    delay(2000)
                    isRotating.value = false
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh icon",
                    modifier = Modifier.rotate(if (isRotating.value) rotation.value else 0f)
                )
            }
            IconButton(onClick = { onClick() }) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = "Customisations"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}