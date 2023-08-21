package com.baldo.bob.ui.compose.notes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.baldo.bob.R
import com.baldo.bob.data.Note
import com.baldo.bob.ui.AppViewModelProvider
import com.baldo.bob.ui.compose.notes.feelingModel.FeelingList
import java.text.DateFormat
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteScreen(
    onAddButtonClicked: () -> Unit = {},
    viewModel: NoteViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToNoteUpdate: (Int) -> Unit
) {
    val noteUiState by viewModel.noteUiState.collectAsState()

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onAddButtonClicked() }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add note")
        }
    }) {
        NoteBody(
            noteList = noteUiState.noteList.sortedByDescending { it.date },
            onModifyClick = navigateToNoteUpdate,
        )
    }
}

@Composable
fun NoteBody(noteList: List<Note>, onModifyClick: (Int) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.all_my_notes),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            if (noteList.isEmpty()) {
                DisplayNote(
                    Note(
                        id = 0,
                        feeling = 4,
                        date = Date(),
                        note = stringResource(R.string.note_exemple),
                    ),
                    onModifyClick = {}
                )
            } else {
                NoteList(noteList = noteList, onModifyClick = { onModifyClick(it.id) })
            }
        }
    }
}

@Composable
fun NoteList(noteList: List<Note>, onModifyClick: (Note) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = noteList, key = { it.id }) {
            DisplayNote(note = it, onModifyClick = onModifyClick)
        }
    }
}

@Composable
fun DisplayNote(note: Note, onModifyClick: (Note) -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(8.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = DateFormat.getDateInstance(DateFormat.LONG)
                        .format(note.date), fontWeight = FontWeight.SemiBold
                )
                TextButton(onClick = { onModifyClick(note) }) {
                    Text(text = stringResource(R.string.Edit))
                }
            }
            Text(
                text = FeelingList.feelingList[note.feeling].icon + " - " + stringResource(id = FeelingList
                    .feelingList[note.feeling].label) ,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(text = note.note)
        }
    }
}