package com.example.bob.ui.compose.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bob.ui.viewModel.AddNoteViewModel
import com.example.bob.ui.viewModel.NoteUiState
import kotlinx.coroutines.launch

data class FeelingData(
    val icon: String,
    val label: String,
)

@Composable
fun AddNoteScreen(
    navigateBack: () -> Unit,
    viewModel: AddNoteViewModel = viewModel(factory = AddNoteViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    AddNoteBody(
        onSaveClick = {
            coroutineScope.launch {
                viewModel.saveNote()
                navigateBack()
            }
        },
        noteUiState = viewModel.noteUiState,
        onValueChange = viewModel::updateUiState
    )
}

@Composable
fun AddNoteBody(
    noteUiState: NoteUiState,
    onValueChange: (NoteUiState) -> Unit = {},
    onSaveClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(text = "Nouvelle note :", fontSize = 20.sp, fontWeight = FontWeight.Medium)
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {

//            Column() {
//                Text(text = "Votre poids")
//                Spacer(modifier = Modifier.size(16.dp))
//                TextField(
//                    value = "",
//                    onValueChange = {},
//                    placeholder = { Text(text = "65") },
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                    modifier = Modifier.fillMaxWidth(),
//                    suffix = { Text(text = "kg") }
//                )
//            }
            Column {
                val data: List<FeelingData> = listOf(
                    FeelingData("\uD83E\uDD75", "Terrible"),
                    FeelingData("\uD83D\uDE1E", "Pas bien"),
                    FeelingData("\uD83D\uDE10", "Bof"),
                    FeelingData("\uD83D\uDE42", "Ca va"),
                    FeelingData("\uD83D\uDE0D", "Au top !"),
                )
                Text(text = "Comment vous sentez-vous ?")
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val (selectedOption, onOptionSelected) = remember { mutableStateOf(data[0]) }
                    data.forEach { selection ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = selection.icon, fontSize = 24.sp)
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(text = selection.label)
                            Spacer(modifier = Modifier.size(8.dp))
                            RadioButton(
                                selected = (selection == selectedOption),
                                onClick = { onOptionSelected(selection) })
                        }
                    }
//                    Text(text = selectedOption.label)
                }
            }
            Column {
                Text(text = "Autre chose ?")
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = noteUiState.note,
                    onValueChange = { onValueChange(noteUiState.copy(note = it)) },
                    placeholder = { Text(text = "J'ai mangé des carottes") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 6
                )
            }
            Button(
                onClick = { onSaveClick() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Ajouter")
            }
        }
    }
}