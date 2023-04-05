package com.example.bob

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.bob.dataStore.UserInformationsRepository
import com.example.bob.ui.theme.BoBTheme
import com.example.bob.ui.viewModel.BobUiState
import com.example.bob.ui.viewModel.BobViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
/*@Preview(showBackground = true)*/
@Composable
fun InformationScreen(
    bobViewModel: BobViewModel,
    bobUiState: BobUiState,
    onSaveButtonClicked: () -> Unit = {},
    localContext: CompositionLocalContext
) {
    BoBTheme {
       var dataStore: UserInformationsRepository()

        var pickedDate by remember {
            mutableStateOf(LocalDate.now())
        }

        val formattedDate by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRANCE)
                    .format(pickedDate)
            }
        }

        var userName by rememberSaveable { mutableStateOf("") }

        val dateDialogState = rememberMaterialDialogState()

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
                        value = userName,
                        onValueChange = { userName = it },
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

                    Text(
                        text = stringResource(
                            R.string.ask_last_periods,
                        )
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { dateDialogState.show() }) {
                            Icon(
                                imageVector = Icons.Rounded.Edit,
                                contentDescription = "Edit last period date",
                            )
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = formattedDate)
                    }

                    /*TextField(
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
                    )*/

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
                    Button(onClick = lifecycleScope.launch{ dataStore. }) {
                        Text(text = stringResource(R.string.save))
                    }

                }
            }
        }
        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(text = "Valider")
                negativeButton(text = "Annuler") {
                }
            }
        ) {
            datepicker(
                initialDate = LocalDate.now(),
                title = "Pick a date",
                allowedDateValidator = {
                    it <= LocalDate.now()
                }
            ) {
                pickedDate = it
            }
        }
    }
}