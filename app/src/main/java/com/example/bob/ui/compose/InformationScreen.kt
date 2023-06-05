package com.example.bob.ui.compose

import android.util.Log
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bob.R
import com.example.bob.dataStore.UserInformations
import com.example.bob.ui.theme.BoBTheme
import com.example.bob.ui.viewModel.BobUiState
import com.example.bob.ui.viewModel.BobViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
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
                if (bobUiState.userLastPeriodsDate != 0L) {
                    LocalDateTime.ofEpochSecond(bobUiState.userLastPeriodsDate, 0, ZoneOffset.UTC)
                } else {
                    LocalDateTime.now()
                },
            )
        }

        var ovulationPickedDate by remember {
            mutableStateOf(
                if (bobUiState.userLastOvulationDate == null) {
                    null
                } else {
                    LocalDateTime.ofEpochSecond(bobUiState.userLastOvulationDate, 0, ZoneOffset.UTC)
                }
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
                    "Inconnue"
                }

            }
        }

        var userName by rememberSaveable { mutableStateOf(bobUiState.userName) }

        val openDialog = remember { mutableStateOf(false) }

        var initialDate by remember { mutableStateOf<Long>(0) }

        var datePickerTypeIsPeriod by remember { mutableStateOf(false) }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Spacer(modifier = Modifier.size(8.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .fillMaxHeight(0.2f)
                        .clip(RoundedCornerShape(8.dp)),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.welcome),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center

                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = stringResource(R.string.congrats),
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = stringResource(R.string.my_name))
                    Spacer(modifier = Modifier.size(8.dp))
                    TextField(
                        value = userName,
                        onValueChange = { userName = it },
                        singleLine = true,
                        isError = false,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onDone = {}),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(32.dp))

                    Text(
                        text = stringResource(
                            R.string.ask_last_periods,
                        )
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {
                            initialDate = Instant.now().toEpochMilli()
                            Log.e(
                                "localdatetime",
                                (LocalDateTime.now()
                                    .toEpochSecond(ZoneOffset.UTC) * 1000).toString()
                            )
                            Log.e(
                                "localinstant",
                                (LocalDateTime.now().toInstant(ZoneOffset.UTC)
                                    .toEpochMilli()).toString()
                            )
                            datePickerTypeIsPeriod = true
                            openDialog.value = true
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.EditCalendar,
                                contentDescription = "Edit last period date",
                            )
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = periodFormattedDate,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.size(32.dp))
                    Column {
                        Text(text = stringResource(R.string.ask_last_ovulation))
                        Text(
                            text = stringResource(R.string.only_if_sure),
                            fontStyle = FontStyle.Italic
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {
                            datePickerTypeIsPeriod = false
                            openDialog.value = true
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.EditCalendar,
                                contentDescription = "Edit last ovulation date",
                            )
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = ovulationFormattedDate,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
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
                        userName,
                        periodPickedDate.toEpochSecond(ZoneOffset.UTC),
                        ovulationPickedDate?.toEpochSecond(
                            ZoneOffset.MAX
                        )
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                bobViewModel.updateUserInformations(data)
                                onSaveButtonClicked()
                            },
                        ) {
                            Text(text = stringResource(R.string.save))
                        }
                    }
                }
            }
        }
        if (openDialog.value) {
            val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialDate)
            DatePickerDialog(confirmButton = {
                TextButton(onClick = {
                    openDialog.value = false
                    if (datePickerTypeIsPeriod) {
                        periodPickedDate = datePickerState.selectedDateMillis?.let {
                            LocalDateTime.ofEpochSecond(
                                it / 1000, 0, ZoneOffset.UTC
                            )
                        }
                    } else {
                        ovulationPickedDate = datePickerState.selectedDateMillis?.let {
                            LocalDateTime.ofEpochSecond(
                                it / 1000, 0, ZoneOffset.UTC
                            )
                        }
                    }

                }) {
                    Text(text = "OK")
                }
            }, dismissButton = {
                TextButton(onClick = { openDialog.value = false }) {
                    Text("Annuler")
                }
            }, onDismissRequest = { openDialog.value = false }) {
                DatePicker(title = {
                    Text(
                        text = if (datePickerTypeIsPeriod) {
                            "Date de vos dernière règles"
                        } else {
                            "Date de votre ovulation"
                        }, modifier = Modifier.padding(24.dp)
                    )
                }, state = datePickerState, dateValidator = {
                    it <= LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000
                })
            }
        }
    }
}