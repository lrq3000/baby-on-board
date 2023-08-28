package com.baldo.bob.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.baldo.bob.R
import com.baldo.bob.dataStore.AppSettings
import com.baldo.bob.ui.viewModel.AppUiState
import com.baldo.bob.ui.viewModel.BobViewModel

@Composable
fun SettingsScreen(
    bobViewModel: BobViewModel,
    appUiState: AppUiState
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(id = R.string.settings), fontWeight = FontWeight.Black)
        Spacer(modifier = Modifier.height(32.dp))
        ThemeSwitcher(bobViewModel, appUiState)
        Spacer(modifier = Modifier.height(32.dp))
        Text(stringResource(id = R.string.WIP_page))
    }
}

@Composable
fun ThemeSwitcher(bobViewModel: BobViewModel, appUiState: AppUiState) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(
                checked = appUiState.themeSetting == "system",
                onCheckedChange = {
                    bobViewModel.updateAppSettings(AppSettings(themeSetting = "system"))
                })
            Text(text = "System", Modifier.padding(start = 16.dp))
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(
                checked = appUiState.themeSetting == "light",
                onCheckedChange = {
                    bobViewModel.updateAppSettings(AppSettings(themeSetting = "light"))
                })
            Text(text = "Light", Modifier.padding(start = 16.dp))
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(
                checked = appUiState.themeSetting == "dark",
                onCheckedChange = {
                    bobViewModel.updateAppSettings(AppSettings(themeSetting = "dark"))
                })
            Text(text = "Dark", Modifier.padding(start = 16.dp))
        }
    }
}
