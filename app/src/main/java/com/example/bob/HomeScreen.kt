package com.example.bob

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bob.ui.theme.light_Customcolor1

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
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