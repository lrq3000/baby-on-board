package com.baldo.bob.ui.compose.notes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EditCalendar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.baldo.bob.R
import com.baldo.bob.ui.compose.notes.feelingModel.Feeling
import com.baldo.bob.ui.compose.notes.feelingModel.FeelingList
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.Date

@Composable
fun AddNoteScreen(
    navigateBack: () -> Unit,
    editingNote: Boolean = false,
    viewModel: AddNoteViewModel = viewModel(factory = AddNoteViewModel.Factory),
) {
    val coroutineScope = rememberCoroutineScope()

    AddNoteBody(
        editingNote = editingNote,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.saveNote()
                navigateBack()
            }
        },
        noteUiState = viewModel.noteUiState,
        onValueChange = viewModel::updateUiState,
        cancel = navigateBack
    )
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteBody(
    cancel: () -> Unit,
    editingNote: Boolean,
    noteUiState: NoteUiState,
    onValueChange: (NoteUiState) -> Unit = {},
    onSaveClick: () -> Unit
) {
    val openDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = if (editingNote) {
                    stringResource(R.string.edit_note)
                } else {
                    stringResource(R.string.new_note)
                }, fontSize = 20.sp, fontWeight = FontWeight.Medium
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = DateFormat.getDateInstance(DateFormat.LONG)
                    .format(noteUiState.date)
            )
            IconButton(onClick = { openDialog.value = true }) {
                Icon(
                    imageVector = Icons.Rounded.EditCalendar,
                    contentDescription = stringResource(id = R.string.Edit_date)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column {
                val data: List<Feeling> = FeelingList.feelingList
                Text(text = stringResource(R.string.how_do_you_feel))
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    data.forEach { selection ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = selection.icon, fontSize = 24.sp)
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(text = stringResource(id = selection.label))
                            Spacer(modifier = Modifier.size(8.dp))
                            RadioButton(
                                selected = (selection == data[noteUiState.feeling]),
                                onClick = {
                                    onValueChange(
                                        noteUiState.copy(
                                            feeling = data.indexOf(
                                                selection
                                            )
                                        )
                                    )
                                })
                        }
                    }
                }
            }
            Column {
                Text(text = stringResource(R.string.anything_else))
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = noteUiState.note,
                    onValueChange = { onValueChange(noteUiState.copy(note = it)) },
                    placeholder = { Text(text = "...") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 6
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                if (editingNote){
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = stringResource(R.string.Delete))
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    onClick = { cancel() },
                ) {
                    Text(stringResource(id = R.string.cancel))
                }
                TextButton(
                    onClick = { onSaveClick() },
                    enabled = noteUiState.note.isNotEmpty()
                ) {
                    Text(
                        text = if (editingNote) {
                            stringResource(id = R.string.save)
                        } else {
                            stringResource(R.string.Add)
                        }
                    )
                }
            }
        }
    }
    if (openDialog.value) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = if (editingNote) {
                noteUiState.date.time
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
                        onValueChange(noteUiState.copy(date = Date(datePickerState.selectedDateMillis!!)))
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
                title = { Text(text = stringResource(R.string.Edit_date), modifier = Modifier.padding(24.dp)) })
        }
    }
}