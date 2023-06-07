package com.example.bob.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bob.R
import com.example.bob.ui.compose.CalendarScreen
import com.example.bob.ui.compose.HomeScreen
import com.example.bob.ui.compose.InformationScreen
import com.example.bob.ui.compose.SettingsScreen
import com.example.bob.ui.compose.WelcomeScreen
import com.example.bob.ui.compose.contractions.ContractionScreen
import com.example.bob.ui.compose.mesures.MesureScreen
import com.example.bob.ui.compose.notes.AddNoteScreen
import com.example.bob.ui.compose.notes.NoteEditScreen
import com.example.bob.ui.compose.notes.NoteScreen
import com.example.bob.ui.viewModel.AboutScreen
import com.example.bob.ui.viewModel.BobViewModel
import kotlinx.coroutines.launch

enum class BobScreen() {
    Home,
    Fruits,
    Calendar,
    CalendarList,
    UserData,
    AddNote,
    EditNote,
    Contraction,
    Settings,
    About,
    Welcome
}

@Composable
fun BobTopAppBar(
    navController: NavController,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
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
        IconButton(onClick = { openDrawer() }) {
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
    NavigationBar() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == "Home" } == true,
            onClick = { navController.navigate(BobScreen.Home.name) },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Home,
                    contentDescription = "Home"
                )
            })
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == "Fruits" } == true,
            onClick = { navController.navigate(BobScreen.Fruits.name) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.nutrition_icon),
                    contentDescription = "Fruits"
                )
            })
        NavigationBarItem(selected = currentDestination?.hierarchy?.any { it.route == "Calendar" } == true,
            onClick = { navController.navigate(BobScreen.Calendar.name) }, icon = {
                Icon(
                    imageVector = Icons.Rounded.CalendarMonth,
                    contentDescription = "Calendar"
                )
            })
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == "CalendarList" } == true,
            onClick = { navController.navigate(BobScreen.CalendarList.name) },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.EditNote,
                    contentDescription = "Notes"
                )
            })
        NavigationBarItem(
            currentDestination?.hierarchy?.any { it.route == "Contraction" } == true,
            onClick = { navController.navigate(BobScreen.Contraction.name) },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Timer,
                    contentDescription = "Contraction"
                )
            })
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
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    data class DrawerItems(
        val icon: ImageVector,
        @StringRes val title: Int,
        val destination: BobScreen
    )

    val items = arrayListOf(
        DrawerItems(
            Icons.Rounded.AccountCircle, R.string.my_data, BobScreen.UserData
        ),
        DrawerItems(
            Icons.Rounded.Settings, R.string.settings, BobScreen.Settings
        ),
        DrawerItems(
            Icons.Rounded.Info, R.string.about, BobScreen.About
        )
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet {
            Spacer(Modifier.height(24.dp))
            Text(
                stringResource(id = R.string.title_app),
                modifier = Modifier.padding(start = 24.dp)
            )
            Spacer(Modifier.height(12.dp))
            items.forEach { item ->
                NavigationDrawerItem(
                    icon = { Icon(item.icon, contentDescription = null) },
                    label = { Text(stringResource(id = item.title)) },
                    selected = currentDestination?.hierarchy?.any { it.route == item.destination.name } == true,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(item.destination.name)
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
    }) {
        Scaffold(
            topBar = {
                BobTopAppBar(
                    navController = navController,
                    openDrawer = { scope.launch { drawerState.open() } })
            },
            bottomBar = { BobBottomAppBar(navController = navController) },
        ) { paddingValues ->
            val startDestination = if (bobUiState.userName !== "") {
                BobScreen.Home.name
            } else {
                BobScreen.Welcome.name
            }
            NavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = modifier.padding(paddingValues)
            ) {
                composable(route = BobScreen.Home.name) {
                    HomeScreen(bobUiState)
                }

                composable(route = BobScreen.UserData.name) {
                    InformationScreen(
                        bobViewModel,
                        bobUiState,
                        onSaveButtonClicked = { navController.navigate(BobScreen.Home.name) })
                }

                composable(route = BobScreen.Fruits.name) {
                    MesureScreen(bobUiState)
                }

                composable(route = BobScreen.CalendarList.name) {
                    NoteScreen(
                        onAddButtonClicked = { navController.navigate(BobScreen.AddNote.name) },
                        navigateToNoteUpdate = {
                            navController.navigate("${BobScreen.EditNote.name}/${it}")
                        },
                    )
                }

                composable(
                    route = BobScreen.AddNote.name,
                ) {
                    AddNoteScreen(
                        navigateBack = { navController.popBackStack() }
                    )
                }

                composable(
                    route = "${BobScreen.EditNote.name}/{noteId}",
                    arguments = listOf(navArgument("noteId") { type = NavType.IntType })
                ) {
                    NoteEditScreen(navigateBack = { navController.popBackStack() })
                }

                composable(route = BobScreen.Contraction.name) {
                    ContractionScreen()
                }

                composable(route = BobScreen.Settings.name) {
                    SettingsScreen()
                }
                composable(route = BobScreen.About.name) {
                    AboutScreen()
                }
                composable(route = BobScreen.Calendar.name) {
                    CalendarScreen()
                }
                composable(route = BobScreen.Welcome.name) {
                    WelcomeScreen(onButtonStartClick = { navController.navigate(BobScreen.UserData.name) })
                }
            }
        }
    }
}