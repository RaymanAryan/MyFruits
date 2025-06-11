package com.rayman.myfruits.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rayman.myfruits.data.model.Fruit

@Composable
fun FruitCard(fruit: Fruit, modifier: Modifier = Modifier) {
    var isClicked = rememberSaveable { mutableStateOf(false) }
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = { isClicked.value = !isClicked.value })
            .animateContentSize(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = fruit.name ?: "", style = MaterialTheme.typography.titleLarge)
            Text(text = "Genus: ${fruit.genus ?: ""}")
            Text(text = "Family: ${fruit.family ?: ""}")
            Text(text = "Order: ${fruit.order ?: ""}")
            if(isClicked.value) {
                HorizontalDivider(modifier = Modifier.height(8.dp))
                Text(text = "Nutrition:", style = MaterialTheme.typography.titleMedium)
                Text(text = "Calories: ${fruit.nutritions.calories ?: ""}")
                Text(text = "Fat: ${fruit.nutritions.fat ?: ""}")
                Text(text = "Sugar: ${fruit.nutritions.sugar ?: ""}")
                Text(text = "Carbohydrates: ${fruit.nutritions.carbohydrates ?: ""}")
                Text(text = "Protein: ${fruit.nutritions.protein ?: ""}")
            }
        }
    }
}

