package com.baldo.bob.ui.compose.weight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EditCalendar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.baldo.bob.R
import com.baldo.bob.data.Weight
import com.baldo.bob.ui.compose.component.NumberField
import com.baldo.bob.ui.compose.weight.viewModel.AddWeightViewModel
import com.baldo.bob.ui.compose.weight.viewModel.WeightUiState
import com.baldo.bob.ui.compose.weight.viewModel.toWeightUiState
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.Date

@Composable
fun AddWeightScreen(
    viewModel: AddWeightViewModel = viewModel(factory = AddWeightViewModel.Factory),
    navigateBack: () -> Unit,
    editingWeight: Boolean = false
) {
    val coroutineScope = rememberCoroutineScope()
    AddWeightBody(
        editingWeight = editingWeight,
        weightUiState = viewModel.weightUiState,
        onValueChange = viewModel::updateUiState,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.saveWeight()
                navigateBack()
            }
        },
        onCancelButton = navigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun AddWeightBody(
    editingWeight : Boolean = false,
    weightUiState: WeightUiState = Weight(Date(), 0F).toWeightUiState(),
    onValueChange: (WeightUiState) -> Unit = {},
    onSaveClick: () -> Unit = {},
    onCancelButton: () -> Unit = {},
) {
    val openDialog = remember { mutableStateOf(false) }

    Column(
        Modifier
            .padding(16.dp),
    ) {
        Column(
            Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.add_your_weight),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 64.dp),
                textAlign = TextAlign.Center
            )
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    NumberField(
                        modifier = Modifier.weight(1f),
                        value = weightUiState.weight.let {
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
                    Text(
                        text = "kg",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 16.dp, end = 12.dp)
                    )
                }
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = {
                onCancelButton()
            }) {
                Text(stringResource(id = R.string.cancel))
            }
            TextButton(onClick = {
                onSaveClick()
            }) {
                Text(text = stringResource(id = R.string.validate))
            }
        }
    }
    if (openDialog.value) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = if (editingWeight) {
                weightUiState.date.time
            } else {
                Date().time
            }
        )
        DatePickerDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        onValueChange(weightUiState.copy(date = Date(datePickerState.selectedDateMillis!!)))
                    }
                ) {
                    Text("OK")
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
        ) {
            DatePicker(
                state = datePickerState,
                title = {
                    Text(
                        text = stringResource(R.string.Edit_date),
                        modifier = Modifier.padding(24.dp)
                    )
                })
        }
    }
}