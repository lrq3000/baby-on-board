package com.baldo.bob.ui.compose

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
import com.baldo.bob.R
import com.baldo.bob.ui.viewModel.BobUiState
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit

@Composable
fun HomeScreen(
    bobUiState: BobUiState,
    modifier: Modifier = Modifier
) {
    val todayDate = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.FULL)
        .format(LocalDateTime.now())

    val lastPeriodDate = if (bobUiState.userLastPeriodsDate != 0L) {
        LocalDateTime.ofEpochSecond(bobUiState.userLastPeriodsDate, 0, ZoneOffset.UTC)
    } else {
        LocalDateTime.now()
    }


//    val userLastOvulationDate: String? = bobUiState.userLastOvulationDate

    val ovulationDate =
        if (bobUiState.userLastOvulationDate == null || bobUiState.userLastOvulationDate == 0L) {
            lastPeriodDate.plusWeeks(2)
        } else {
            LocalDateTime.ofEpochSecond(bobUiState.userLastOvulationDate, 0, ZoneOffset.UTC)
        }

    val term = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.LONG)
        .format(ovulationDate.plusWeeks(39))

    val month = ChronoUnit.MONTHS.between(ovulationDate, LocalDateTime.now()) + 1
    val stringDisplayMonth = if (month.equals(1)) {
        "$month" + stringResource(R.string.st)
    } else {
        "$month" + stringResource(R.string.nd)
    }
    val amenorrheaWeeks = ChronoUnit.DAYS.between(lastPeriodDate, LocalDateTime.now()) / 7
    val amenorrheaDaysLefts = ChronoUnit.DAYS.between(lastPeriodDate, LocalDateTime.now()) % 7
    val amenorrheaDaysLeftsString = if (amenorrheaDaysLefts != 0.toLong()) {
        stringResource(R.string.et_jours, amenorrheaDaysLefts)
    } else {
        ""
    }
    val pregnancyWeeks = ChronoUnit.DAYS.between(ovulationDate, LocalDateTime.now()) / 7
    val pregnancyDaysLeft = ChronoUnit.DAYS.between(ovulationDate, LocalDateTime.now()) % 7
    val pregnancyDaysLeftString = if (pregnancyDaysLeft != 0.toLong()) {
        stringResource(R.string.et_jours, pregnancyDaysLeft)
    } else {
        ""
    }
    val trimester = if (pregnancyWeeks <= 13) {
        stringResource(R.string._1st)
    } else if (pregnancyWeeks <= 26) {
        stringResource(R.string._2nd)
    } else {
        stringResource(R.string._3rd)
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
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 24.dp)
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