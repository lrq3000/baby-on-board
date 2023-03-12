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
import com.example.bob.ui.theme.BoBTheme
import com.example.bob.ui.theme.light_Customcolor1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { BobTopAppBar() },
        bottomBar = { BobBottomAppBar() },
    ) { paddingValues ->
        HomeBody(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun HomeBody(modifier: Modifier) {
    val date = "10 mars 2022"
    val name = "Ombline"
    val trimester = "1er"
    val term = "9 juin 2023"
    val amenorrheaWeeks = 23
    val pregnancyWeeks = 25

    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.we_are_the) + " " + date,
            modifier = Modifier.align(Alignment.End)
        )
        Box {
            Image(
                painter = painterResource(id = R.drawable.home_pic),
                contentDescription = "test",
                modifier = Modifier.clip(
                    RoundedCornerShape(8.dp)
                )
            )
            Text(
                text = stringResource(R.string.hello) + " " + name,
                modifier = Modifier.padding(10.dp),
                fontSize = 32.sp,
                color = Color.White
            )
        }
        Column {
            Text(
                text = stringResource(id = R.string.amenorrhea_weeks) + " " + amenorrheaWeeks,
                color = light_Customcolor1,
            )
            Text(
                text = stringResource(R.string.pregnancy_weeks) + " " + pregnancyWeeks,

                fontStyle = FontStyle.Italic
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = trimester + " " + stringResource(R.string.trimester))
            Text(text = stringResource(R.string.term_date) + " " + term, textAlign = TextAlign.End)
        }
    }
}

@Composable
fun BobTopAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.secondaryContainer),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.title_app),
            fontSize = 22.sp,
            modifier = modifier.padding(start = 16.dp),
            color = MaterialTheme.colorScheme.onSecondary
        )
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "Menu",
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

@Composable
fun BobBottomAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(color = MaterialTheme.colorScheme.primaryContainer),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = "home",
                tint = Color.White,
                modifier = modifier.size(76.dp)
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
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
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Rounded.List,
                contentDescription = "List",
                tint = Color.White,
                modifier = modifier.size(76.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BoBTheme {
        HomeScreen()
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
