package com.example.bob.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bob.BobApplication
import com.example.bob.ui.compose.contractions.ContractionsViewModel
import com.example.bob.ui.compose.notes.AddNoteViewModel
import com.example.bob.ui.compose.notes.NoteEditViewModel
import com.example.bob.ui.compose.notes.NoteViewModel
import com.example.bob.ui.viewModel.BobViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEntryViewModel
        initializer {
            AddNoteViewModel(bobApplication().appDataContainer.notesRepository)
        }

        initializer {
            NoteEditViewModel(this.createSavedStateHandle(), bobApplication().appDataContainer.notesRepository)
        }

        initializer {
            NoteViewModel(bobApplication().appDataContainer.notesRepository)
        }

        // Initializer for HomeViewModel
        initializer {
            BobViewModel(bobApplication().userInformationsRepository)
        }

        initializer {
            ContractionsViewModel(bobApplication().appDataContainer.contactionsRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */

fun CreationExtras.bobApplication(): BobApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BobApplication)
