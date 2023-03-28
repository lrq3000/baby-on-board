package com.example.bob

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bob.ui.theme.BoBTheme
import com.example.bob.ui.viewModel.BobUiState
import com.example.bob.ui.viewModel.BobViewModel

@OptIn(ExperimentalMaterial3Api::class)
/*@Preview(showBackground = true)*/
@Composable
fun InformationScreen(
    bobViewModel: BobViewModel,
    bobUiState: BobUiState,
    onSaveButtonClicked: () -> Unit = {},
) {
    BoBTheme() {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        )
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.15f)
                        .clip(RoundedCornerShape(8.dp)),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.welcome),
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = stringResource(R.string.congrats))
                    }
                }
                Spacer(modifier = Modifier.size(64.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = stringResource(R.string.my_name))
                    TextField(
                        value = bobUiState.userName,
                        onValueChange = { bobViewModel.updateUserName(it) },
                        label = { Text(stringResource(R.string.first_name)) },
                        singleLine = true,
                        isError = false,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {}
                        )
                    )
                    Spacer(modifier = Modifier.size(32.dp))
                    Text(text = stringResource(R.string.ask_last_periods))
                    TextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("Date des régles") },
                        singleLine = true,
                        isError = false,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {}
                        )
                    )
                    Spacer(modifier = Modifier.size(32.dp))
                    Column() {
                        Text(text = stringResource(R.string.ask_last_ovulation))
                        Text(
                            text = stringResource(R.string.only_if_sure),
                            fontStyle = FontStyle.Italic
                        )
                    }
                    TextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("Date d'ovulation") },
                        singleLine = true,
                        isError = false,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {}
                        )
                    )
                    Spacer(modifier = Modifier.size(32.dp))
                    Button(onClick = onSaveButtonClicked) {
                        Text(text = stringResource(R.string.save))
                    }

                }
            }
        }
    }
}
