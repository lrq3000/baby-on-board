package com.baldo.bob.ui.compose.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun NumberField(
    modifier: Modifier = Modifier,
    value: Float?,
    onNumberChange: (Float?) -> Unit,
) {
    val number = remember { mutableStateOf(value) }
    val textValue = remember(value != number.value) {
        number.value = value
        mutableStateOf(value?.let {
            if (it % 1.0 == 0.0) {
                it.toInt().toString()
            } else {
                it.toString()
            }
        } ?: "")
    }

    val numberRegex = remember { "\\d{1,2}(\\.\\d?)?".toRegex() }
    // for no negative numbers use "[\d]*[.]?[\d]*"

    OutlinedTextField(
        modifier = modifier,
        value = textValue.value,
        onValueChange = {
            if (numberRegex.matches(it)) {
                textValue.value = it
                number.value = it.toFloat()
                onNumberChange(number.value)
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}