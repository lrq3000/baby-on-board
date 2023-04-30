package com.example.bob.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LinearScale
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Insights
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bob.HomeScreen
import com.example.bob.InformationScreen
import com.example.bob.R
import com.example.bob.ui.elements.AddNoteScreen
import com.example.bob.ui.elements.MesureScreen
import com.example.bob.ui.elements.NoteScreen
import com.example.bob.ui.viewModel.BobViewModel

enum class BobScreen() {
    Home,
    Fruits,
    Chart,
    Calendar,
    CalendarList,
    Informations,
    AddNote
}

@Composable
fun BobTopAppBar(navController: NavController, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.title_app),
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            modifier = modifier.padding(start = 16.dp),
            color = Color.White
        )
        IconButton(onClick = { navController.navigate(BobScreen.Informations.name) }) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "Menu",
                tint = Color.White
            )
        }
    }
}

@Composable
fun BobBottomAppBar(navController: NavController, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.navigate(BobScreen.Home.name) }) {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = "home",
                tint = Color.White,
                modifier = modifier.size(76.dp)
            )
        }
        IconButton(onClick = { navController.navigate(BobScreen.Fruits.name) }) {
            Icon(
                imageVector = Icons.Filled.LinearScale,
                contentDescription = "Linear Scale",
                tint = Color.White,
                modifier = modifier.size(76.dp)
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Rounded.Insights,
                contentDescription = "Charts",
                tint = Color.White,
                modifier = modifier.size(76.dp)
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Rounded.CalendarMonth,
                contentDescription = "Fruit",
                tint = Color.White,
                modifier = modifier.size(76.dp)
            )
        }
        IconButton(onClick = { navController.navigate(BobScreen.CalendarList.name) }) {
            Icon(
                imageVector = Icons.Rounded.List,
                contentDescription = "List",
                tint = Color.White,
                modifier = modifier.size(76.dp)
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun BobApp(
    modifier: Modifier = Modifier,
    bobViewModel: BobViewModel = viewModel(factory = BobViewModel.Factory),
    navController: NavHostController = rememberNavController(),
) {
    val bobUiState by bobViewModel.uiState.collectAsState()
    Scaffold(
        topBar = { BobTopAppBar(navController = navController) },
        bottomBar = { BobBottomAppBar(navController = navController) },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BobScreen.Home.name,
            modifier = modifier.padding(paddingValues)
        ) {
            composable(route = BobScreen.Home.name) {
                HomeScreen(bobUiState)
            }

            composable(route = BobScreen.Informations.name) {
                InformationScreen(
                    bobViewModel,
                    bobUiState,
                    onSaveButtonClicked = { navController.navigate(BobScreen.Home.name) })
            }

            composable(route = BobScreen.Fruits.name) {
                MesureScreen(bobUiState)
            }

            composable(route = BobScreen.CalendarList.name) {
                NoteScreen(onAddButtonClicked = { navController.navigate(BobScreen.AddNote.name) })
            }

            composable(route = BobScreen.AddNote.name) {
                AddNoteScreen(onSaveButtonClicked = {navController.navigate(BobScreen.CalendarList.name)})
            }
        }
    }
}