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
import com.example.bob.ui.viewModel.BobUiState
import com.example.bob.ui.viewModel.BobViewModel
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit
import java.util.*

@Composable
fun HomeScreen(
    bobViewModel: BobViewModel,
    bobUiState: BobUiState,
    modifier: Modifier = Modifier
) {
    val todayDate = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRANCE)
        .format(LocalDate.now())

   val lastPeriodDate = if (bobUiState.userLastPeriodsDate !== "") {
        LocalDate.parse(bobUiState.userLastPeriodsDate)
    } else {
        LocalDate.now()
    }
    val term = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRANCE)
        .format(lastPeriodDate.plusWeeks(41))

    val month = ChronoUnit.MONTHS.between(lastPeriodDate, LocalDate.now())
    val stringDisplayMonth = if (month.equals(1)){"$month" + "er"}else{"$month" + "ème"}
    val amenorrheaWeeks = ChronoUnit.DAYS.between(lastPeriodDate, LocalDate.now()) /7
    val pregnancyWeeks = ChronoUnit.DAYS.between(lastPeriodDate.plusDays(14), LocalDate.now()) /7
    val trimester = if (amenorrheaWeeks <= 15){"1er"}else if(amenorrheaWeeks <= 28){"2ème"}else{"3ème"}
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.we_are_the) + " " + todayDate,
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
                text = stringResource(R.string.hello, bobUiState.userName),
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
            Column() {
                Text(text = stringResource(R.string.month, stringDisplayMonth))
                Text(text = stringResource(R.string.trimester, trimester))
            }
            Text(text = stringResource(R.string.term_date) + " " + term, textAlign = TextAlign.End)
        }
    }
}