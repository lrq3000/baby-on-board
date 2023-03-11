package com.example.bob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bob.ui.theme.BoBTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

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

@Composable
fun HomeScreen() {
    val date = "10 mars 2022"

    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), color = MaterialTheme.colorScheme.background) {
        Column() {
            Text(text = stringResource(R.string.we_are_the) + " " + date, modifier = Modifier.align(Alignment.End))
            Image(painter = painterResource(id = R.drawable.home_pic), contentDescription = "test" )
            Text(text = stringResource(R.string.amenorrhee_weeks))
            Text(text = stringResource(R.string.pregnancy_weeks))
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "1er trimestre",)
                Text(text = "Date du terme\n 07 juin 2022",)
            }
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