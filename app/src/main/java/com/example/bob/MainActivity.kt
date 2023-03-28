package com.example.bob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LinearScale
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bob.ui.BobApp
import com.example.bob.ui.theme.BoBTheme
import com.example.bob.ui.theme.light_Customcolor1

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BobApp()
                }
            }
        }
    }
}

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        topBar = { BobTopAppBar(
            OpenMenu = {navController.navigate(BobScreen.Informations.name)}
        ) },
        bottomBar = { BobBottomAppBar() },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BobScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = BobScreen.Home.name){
                HomeBody(modifier = Modifier.padding(innerPadding))
            }

            composable(route = BobScreen.Informations.name){
                InformationSreen()
            }
        }

    }
}*/

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BoBTheme {
        BobApp()
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultDarkPreview() {
    BoBTheme(useDarkTheme = true) {
        HomeScreen()
    }
}*/
