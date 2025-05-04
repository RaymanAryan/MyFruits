package com.rayman.myfruits.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rayman.myfruits.data.model.Attribute
import com.rayman.myfruits.data.model.Nutrients

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitSelectionDialog(
    onDismiss: () -> Unit,
    onConfirm: (attributeType: Attribute, attributeValue: String, nutrient: Nutrients, min: Double?, max: Double?) -> Unit
) {
    var selectedAttribute by remember { mutableStateOf<Attribute>(Attribute.Empty) }
    var selectedNutrient by remember { mutableStateOf<Nutrients>(Nutrients.Empty) }

    var expanded by remember { mutableStateOf(false) }
    var expandedNutrients by remember { mutableStateOf(false) }

    var attributeValue by remember { mutableStateOf("") }
    var minValue by remember { mutableStateOf("") }
    var maxValue by remember { mutableStateOf("") }

    val optionsOfAttribute = listOf(Attribute.Empty, Attribute.Family, Attribute.Genus, Attribute.Order)
    val optionsOfNutrients = listOf(Nutrients.Empty,
        Nutrients.Calories,
        Nutrients.Fat,
        Nutrients.Sugar,
        Nutrients.Carbohydrates,
        Nutrients.Protein)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Fruit Options") },
        text = {
            Column {
                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                    TextField(
                        value = selectedAttribute.name,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Attribute") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                        modifier = Modifier.menuAnchor()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        optionsOfAttribute.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option.name) },
                                onClick = {
                                    selectedAttribute = option
                                    expanded = false
                                }
                            )
                        }
                    }
                }


                OutlinedTextField(
                    value = attributeValue,
                    onValueChange = {attributeValue = it},
                    label = { Text("Attribute Value(Start with capital!)") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                ExposedDropdownMenuBox(expanded = expandedNutrients, onExpandedChange = { expandedNutrients = !expandedNutrients }) {
                    TextField(
                        value = selectedNutrient.name,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Attribute") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expandedNutrients) },
                        modifier = Modifier.menuAnchor(),
                        enabled = selectedAttribute == Attribute.Empty
                    )

                    ExposedDropdownMenu(
                        expanded = if (selectedAttribute == Attribute.Empty) expandedNutrients else false,
                        onDismissRequest = { expandedNutrients = false }
                    ) {
                        optionsOfNutrients.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option.name) },
                                onClick = {
                                    selectedNutrient = option
                                    expandedNutrients = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = if(selectedAttribute != Attribute.Empty) "" else minValue,
                    onValueChange = { minValue = it },
                    label = { Text("Min Nutrition") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    enabled = selectedAttribute == Attribute.Empty
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = if(selectedAttribute != Attribute.Empty) "" else maxValue,
                    onValueChange = { maxValue = it },
                    label = { Text("Max Nutrition") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    enabled = selectedAttribute == Attribute.Empty
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(
                        selectedAttribute,
                        attributeValue,
                        selectedNutrient,
                        minValue.toDoubleOrNull(),
                        maxValue.toDoubleOrNull()
                    )
                    onDismiss()
                }
            ) {
                Text("Search")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


