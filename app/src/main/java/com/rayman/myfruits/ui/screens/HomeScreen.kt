package com.rayman.myfruits.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rayman.myfruits.data.model.ApiResponse
import com.rayman.myfruits.data.model.Attribute
import com.rayman.myfruits.data.model.FruitviceResponse
import com.rayman.myfruits.data.model.Nutrients
import com.rayman.myfruits.ui.components.AppBar
import com.rayman.myfruits.ui.components.ErrorDialog
import com.rayman.myfruits.ui.components.FruitSelectionDialog
import com.rayman.myfruits.ui.components.FruitsList
import com.rayman.myfruits.ui.components.LoadingPage
import com.rayman.myfruits.ui.viewmodel.FruitViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(fruitViewModel: FruitViewModel = hiltViewModel()) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var showErrorDialog by rememberSaveable { mutableStateOf(true) }
    val fruitsList = fruitViewModel.fruits.collectAsState(ApiResponse.Loading).value

    Scaffold(
        topBar = {
            AppBar(
                onClick = { showDialog = true },
                fruitViewModel = fruitViewModel)
        }
    ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding()),
                contentAlignment = Alignment.Center
            ) {
                when (fruitsList) {
                    is FruitviceResponse -> {
                        FruitsList(fruitsList)
                    }

                    is ApiResponse.Error -> {
                        LaunchedEffect(fruitsList) {
                            if (showDialog != true) {
                                showErrorDialog = true
                            }
                        }
                        ErrorDialog(dialogVisible = showErrorDialog,
                            title = "Error",
                            message = fruitsList.exception.message ?: "An unknown error occurred",
                            confirmButtonText = "OK",
                            onDismiss = {
                                showErrorDialog = false
                                showDialog = true
                            }
                        )
                    }

                    is ApiResponse.Loading -> {
                        LoadingPage()
                    }
                }
            }
            if (showDialog) {
                FruitSelectionDialog(onDismiss = { showDialog = false }, onConfirm = { attributeType, attributeValue, nutrient, min, max ->
                    if (attributeType == Attribute.Empty && (nutrient == Nutrients.Empty) || (min == null && max == null)) {
                        fruitViewModel.getAllFruits()
                    }
                    if(nutrient != Nutrients.Empty && (min != null || max != null)) {
                        fruitViewModel.getFruitByNutrition(nutrient,min = min , max = max )
                    } else {
                        fruitViewModel.getFruitByAttribute(attributeType,attributeValue)
                    }
                })
        }
            BackHandler {
                showDialog = false
            }
    }
}


