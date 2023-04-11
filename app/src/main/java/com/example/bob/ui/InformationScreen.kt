package com.example.bob

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.EditCalendar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.bob.dataStore.UserInformations
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
@Composable
fun InformationScreen(
    bobViewModel: BobViewModel,
    bobUiState: BobUiState,
    onSaveButtonClicked: () -> Unit = {},
) {
    BoBTheme {
        var periodPickedDate by remember {
            mutableStateOf(
                if (bobUiState.userLastPeriodsDate !== "") {
                    LocalDate.parse(bobUiState.userLastPeriodsDate)
                } else {
                    LocalDate.now()
                },
            )
        }

        var ovulationPickedDate by remember {
            mutableStateOf(
                if (bobUiState.userLastOvulationDate == "null" || bobUiState.userLastOvulationDate == null || bobUiState.userLastOvulationDate == "") {
                    null
                } else {
                    LocalDate.parse(bobUiState.userLastOvulationDate)
                },
            )
        }

        val periodFormattedDate by remember {
            derivedStateOf {
                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRANCE)
                    .format(periodPickedDate)
            }
        }

        val ovulationFormattedDate by remember {
            derivedStateOf {
                if (ovulationPickedDate !== null) {
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRANCE)
                        .format(ovulationPickedDate)
                } else {
                    "Non défini"
                }

            }
        }

        var userName by rememberSaveable { mutableStateOf(bobUiState.userName) }

        val periodDateDialogState = rememberMaterialDialogState()
        val ovulationDateDialogState = rememberMaterialDialogState()

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
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
                    TextField(value = userName,
                        onValueChange = { userName = it },
                        label = { Text(stringResource(R.string.first_name)) },
                        singleLine = true,
                        isError = false,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onDone = {}))
                    Spacer(modifier = Modifier.size(32.dp))

                    Text(
                        text = stringResource(
                            R.string.ask_last_periods,
                        )
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { periodDateDialogState.show() }) {
                            Icon(
                                imageVector = Icons.Rounded.EditCalendar,
                                contentDescription = "Edit last period date",
                            )
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = periodFormattedDate)
                    }

                    Spacer(modifier = Modifier.size(32.dp))
                    Column() {
                        Text(text = stringResource(R.string.ask_last_ovulation))
                        Text(
                            text = stringResource(R.string.only_if_sure),
                            fontStyle = FontStyle.Italic
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { ovulationDateDialogState.show() }) {
                            Icon(
                                imageVector = Icons.Rounded.EditCalendar,
                                contentDescription = "Edit last ovulation date",
                            )
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = ovulationFormattedDate)
                        if (ovulationPickedDate != null) {
                            IconButton(onClick = { ovulationPickedDate = null }) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = "Clear last ovulation date",
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.size(32.dp))
                    val data = UserInformations(
                        userName, periodPickedDate.toString(), ovulationPickedDate.toString()
                    )
                    Button(onClick = {
                        bobViewModel.updateUserInformations(data)
                        onSaveButtonClicked()
                    }) {
                        Text(text = stringResource(R.string.save))
                    }

                }
            }
        }
        MaterialDialog(dialogState = periodDateDialogState, buttons = {
            positiveButton(
                text = stringResource(R.string.validate),
                TextStyle(color = MaterialTheme.colorScheme.primary)
            )
            negativeButton(
                text = stringResource(R.string.cancel),
                TextStyle(color = MaterialTheme.colorScheme.primary)
            ) {}
        }) {
            datepicker(
                initialDate = periodPickedDate,
                title = stringResource(R.string.pick_a_date),
                allowedDateValidator = {
                    it <= LocalDate.now()
                },
                locale = Locale.FRANCE,
                colors = com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults.colors(
                    headerBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    dateActiveBackgroundColor = MaterialTheme.colorScheme.primary,
                    headerTextColor = MaterialTheme.colorScheme.primary
                ),
            ) {
                periodPickedDate = it
            }
        }

        MaterialDialog(dialogState = ovulationDateDialogState, buttons = {
            positiveButton(
                text = stringResource(R.string.validate),
                TextStyle(color = MaterialTheme.colorScheme.primary)
            )
            negativeButton(
                text = stringResource(R.string.cancel),
                TextStyle(color = MaterialTheme.colorScheme.primary)
            ) {}
        }) {
            datepicker(
                initialDate = ovulationPickedDate ?: LocalDate.now(),
                title = stringResource(R.string.pick_a_date),
                allowedDateValidator = {
                    it <= LocalDate.now()
                },
                locale = Locale.FRANCE,
                colors = com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults.colors(
                    headerBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    dateActiveBackgroundColor = MaterialTheme.colorScheme.primary,
                    headerTextColor = MaterialTheme.colorScheme.primary
                ),
            ) {
                ovulationPickedDate = it
            }
        }
    }
}