package com.example.bob

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bob.ui.theme.light_Customcolor1
import com.example.bob.ui.viewModel.BobUiState
import com.example.bob.ui.viewModel.BobViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit
import java.util.*

@Composable
fun HomeScreen(
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

    val userLastOvulationDate: String? = bobUiState.userLastOvulationDate
    val ovulationDate = if (userLastOvulationDate == null || userLastOvulationDate == "null" || userLastOvulationDate == "") {
        lastPeriodDate.plusWeeks(2)
    } else {
        LocalDate.parse(userLastOvulationDate)
    }

    val term = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.FRANCE)
        .format(ovulationDate.plusWeeks(39))

    val month = ChronoUnit.MONTHS.between(ovulationDate, LocalDate.now()) + 1
    val stringDisplayMonth = if (month.equals(1)) {
        "$month" + "er"
    } else {
        "$month" + "ème"
    }
    val amenorrheaWeeks = ChronoUnit.DAYS.between(lastPeriodDate, LocalDate.now()) / 7
    val amenorrheaDaysLefts = ChronoUnit.DAYS.between(lastPeriodDate, LocalDate.now()) % 7
    val amenorrheaDaysLeftsString = if (amenorrheaDaysLefts != 0.toLong()) {
        " et $amenorrheaDaysLefts jours"
    } else {
        ""
    }
    val pregnancyWeeks = ChronoUnit.DAYS.between(ovulationDate, LocalDate.now()) / 7
    val pregnancyDaysLeft = ChronoUnit.DAYS.between(ovulationDate, LocalDate.now()) % 7
    val pregnancyDaysLeftString = if (pregnancyDaysLeft != 0.toLong()) {
        " et $pregnancyDaysLeft jours"
    } else {
        ""
    }
    val trimester = if (pregnancyWeeks <= 13) {
        "1er"
    } else if (pregnancyWeeks <= 26) {
        "2ème"
    } else {
        "3ème"
    }
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(top = 32.dp)) {
            Text(
                text = stringResource(R.string.we_are_the) + " " + todayDate,
                modifier = Modifier.align(Alignment.End).padding(bottom = 24.dp)
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
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }

        Column {
            Text(
                text = stringResource(
                    id = R.string.amenorrhea_weeks,
                    amenorrheaWeeks
                ) + amenorrheaDaysLeftsString,
                color = MaterialTheme.colorScheme.secondary,
            )
            Spacer(modifier = modifier.size(8.dp))
            Text(
                text = stringResource(
                    R.string.pregnancy_weeks,
                    pregnancyWeeks
                ) + pregnancyDaysLeftString,
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
            Column(horizontalAlignment = Alignment.End) {
                Text(text = stringResource(R.string.term_date), textAlign = TextAlign.End)
                Text(text = term, textAlign = TextAlign.End)
            }
        }
    }
}