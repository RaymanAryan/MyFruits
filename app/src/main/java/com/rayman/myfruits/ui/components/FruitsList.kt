package com.rayman.myfruits.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.rayman.myfruits.data.model.FruitviceResponse

@Composable
fun FruitsList(fruits: FruitviceResponse) {
    LazyColumn {
        items(fruits.results){
            FruitCard(fruit = it)
        }
    }
}