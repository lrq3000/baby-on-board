package com.baldo.bob.ui.compose

import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.baldo.bob.R
import com.baldo.bob.ui.theme.BoBTheme

@Composable
fun SettingsScreen() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(id = R.string.settings), fontWeight = FontWeight.Black)
        Spacer(modifier = Modifier.height(32.dp))
        ThemeSwitcher()
        Spacer(modifier = Modifier.height(32.dp))
        Text(stringResource(id = R.string.WIP_page))
    }
}

data class ThemeData(
    val title: String,
    val value: Boolean
)

object ThemeMode {
    const val Dark = "Dark"
    const val Light = "Light"
    const val System = "System"
}

@Composable
fun ThemeSwitcher() {
    val themeMode = remember { mutableStateOf(ThemeData(ThemeMode.Light, false)) }
    BoBTheme(
        useDarkTheme = themeMode.value.value,
        content = {
            val systemTheme = isSystemInDarkTheme()
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Switch(
                        checked = themeMode.value.title == ThemeMode.System,
                        onCheckedChange = { themeMode.value = ThemeData(ThemeMode.System, systemTheme) })
                    Text(text = "System", Modifier.padding(start = 16.dp))
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Switch(
                        checked = themeMode.value.title == ThemeMode.Light,
                        onCheckedChange = { themeMode.value = ThemeData(ThemeMode.Light, false) })
                    Text(text = "Light", Modifier.padding(start = 16.dp))
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Switch(
                        checked = themeMode.value.title == ThemeMode.Dark,
                        onCheckedChange = { themeMode.value = ThemeData(ThemeMode.Dark, true) })
                    Text(text = "Dark", Modifier.padding(start = 16.dp))
                }
            }
        }
    )
}
