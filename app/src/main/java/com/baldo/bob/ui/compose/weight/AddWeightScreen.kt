package com.baldo.bob.ui.compose.weight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.EditCalendar
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.baldo.bob.R
import com.baldo.bob.ui.compose.component.NumberField
import com.baldo.bob.ui.compose.weight.viewModel.AddWeightViewModel
import com.baldo.bob.ui.compose.weight.viewModel.WeightUiState
import kotlinx.coroutines.launch
import java.text.DateFormat

@Composable
fun AddWeightScreen(
    openDialog: MutableState<Boolean>,
    viewModel: AddWeightViewModel = viewModel(factory = AddWeightViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    AddWeightBody(
        weightUiState = viewModel.weightUiState, openDialog = openDialog,
        onValueChange = viewModel::updateUiState,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.saveWeight()
            }
        }
    )
}

@Composable
fun AddWeightBody(
    weightUiState: WeightUiState,
    openDialog: MutableState<Boolean>,
    onValueChange: (WeightUiState) -> Unit = {},
    onSaveClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onDismissRequest.
            openDialog.value = false
        },
        icon = { Icon(Icons.Filled.Add, contentDescription = null) },
        title = {
            Text(text = stringResource(R.string.add_your_weight))
        },
        text = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = DateFormat.getDateInstance(DateFormat.LONG)
                            .format(weightUiState.date)
                    )
                    IconButton(onClick = { openDialog.value = true }) {
                        Icon(
                            imageVector = Icons.Rounded.EditCalendar,
                            contentDescription = stringResource(id = R.string.Edit_date)
                        )
                    }
                }
//                val weight = weightUiState.weight.toString()
//                val weight2 = remember { mutableStateOf("") }
                NumberField(value = weightUiState.weight.let {
                    if (it == 0F) {
                        null
                    } else {
                        it
                    }
                }) {
                    if (it != null) {
                        onValueChange(weightUiState.copy(weight = it.toFloat()))
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onSaveClick()
                    openDialog.value = false
                }
            ) {
                Text(text = stringResource(id = R.string.validate))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text(stringResource(id = R.string.cancel))
            }
        }
    )
}