package com.rayman.myfruits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rayman.myfruits.ui.screens.HomeScreen
import com.rayman.myfruits.ui.theme.MyFruitsTheme
import com.rayman.myfruits.ui.viewmodel.FruitViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFruitsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        MyFruitApp()
                    }
                }
            }
        }
    }
}

@Composable
fun MyFruitApp(fruitViewModel: FruitViewModel = hiltViewModel()){
    HomeScreen(fruitViewModel = fruitViewModel)
}