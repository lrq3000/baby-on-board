package com.baldo.bob.ui.compose.notes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.baldo.bob.ui.AppViewModelProvider
import kotlinx.coroutines.launch

@Composable
fun NoteEditScreen(
    navigateBack: () -> Unit,
    viewModel: NoteEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    AddNoteBody(
        editingNote = true,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.updateNote()
                navigateBack()
            }
        },
        noteUiState = viewModel.noteUiState,
        onValueChange = viewModel::updateUiState,
        cancel = { navigateBack() }
    )
}