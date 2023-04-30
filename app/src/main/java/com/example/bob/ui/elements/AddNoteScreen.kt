package com.example.bob.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview(showBackground = true)
fun AddNoteScreen(onSaveButtonClicked: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(text = "Nouvelle note :", fontSize = 24.sp, fontWeight = FontWeight.Medium)
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {

            Column() {
                Text(text = "Votre poids")
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(text = "65") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    suffix = { Text(text = "kg") }
                )
            }
            Column() {
                Text(text = "Comment vous sentez-vous ?")
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(text = "Bien") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
            Column() {
                Text(text = "Autre chose ?")
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(text = "J'ai mangé des carottes") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Button(
                onClick = { onSaveButtonClicked() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Ajouter")
            }
        }
    }
}