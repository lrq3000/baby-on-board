package com.baldo.bob.ui.compose.mesures

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.baldo.bob.R
import com.baldo.bob.ui.viewModel.BobUiState
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MesureScreen(bobUiState: BobUiState) {

    val lastPeriodDate = if (bobUiState.userLastPeriodsDate != 0L) {
        LocalDateTime.ofEpochSecond(bobUiState.userLastPeriodsDate, 0, ZoneOffset.UTC)
    } else {
        LocalDateTime.now()
    }

    val ovulationDate =
        if (bobUiState.userLastOvulationDate == null) {
            lastPeriodDate.plusWeeks(2)
        } else {
            LocalDateTime.ofEpochSecond(bobUiState.userLastOvulationDate, 0, ZoneOffset.UTC)
        }

    val SGcount = ChronoUnit.DAYS.between(ovulationDate, LocalDateTime.now()) / 7
    val pagerState = rememberPagerState()

    val weeksInfos = when (pagerState.currentPage + 2) {
        2 -> listOf(R.drawable._2, stringResource(R.string.period_late), "0,2mm", "/")
        3 -> listOf(R.drawable._3, stringResource(R.string.poppy_seed), "1mm", "/")
        4 -> listOf(R.drawable._4, stringResource(R.string.sesame_seed), "4mm", "/")
        5 -> listOf(R.drawable._5, stringResource(R.string.green_lentil), "6mm", "1gr")
        6 -> listOf(R.drawable._6, stringResource(R.string.pea), "1,5cm", "1,5gr")
        7 -> listOf(R.drawable._7, stringResource(R.string.chickpea), "2cm", "2gr")
        8 -> listOf(R.drawable._8, stringResource(R.string.grape_seed), "3cm", "3gr")
        9 -> listOf(R.drawable._9, stringResource(R.string.nut), "4,5cm", "10gr")
        10 -> listOf(R.drawable._10, stringResource(R.string.plum), "7,5cm", "7,5cm")
        11 -> listOf(R.drawable._11, stringResource(R.string.green_lemon), "8,5cm", "30gr")
        12 -> listOf(R.drawable._12, stringResource(R.string.lemon), "10cm", "45gr")
        13 -> listOf(R.drawable._13, stringResource(R.string.orange), "12cm", "65gr")
        14 -> listOf(R.drawable._14, stringResource(R.string.nectarine), "13cm", "110gr")
        15 -> listOf(R.drawable._15, stringResource(R.string.apple), "15cm", "140gr")
        16 -> listOf(R.drawable._16, stringResource(R.string.avocado), "17cm", "160gr")
        17 -> listOf(R.drawable._17, stringResource(R.string.pear), "19cm", "200gr")
        18 -> listOf(R.drawable._18, stringResource(R.string.pepper), "20cm", "250gr")
        19 -> listOf(R.drawable._19, stringResource(R.string.big_tomato), "21cm", "350gr")
        20 -> listOf(R.drawable._20, stringResource(R.string.artichoke), "23cm", "400gr")
        21 -> listOf(R.drawable._21, stringResource(R.string.papaya), "25cm", "450gr")
        22 -> listOf(R.drawable._22, stringResource(R.string.grapefruit), "26cm", "500gr")
        23 -> listOf(R.drawable._23, stringResource(R.string.mango), "28cm", "600gr")
        24 -> listOf(R.drawable._24, stringResource(R.string.ear_of_corn), "30cm", "650gr")
        25 -> listOf(R.drawable._25, stringResource(R.string.melon), "31cm", "750gr")
        26 -> listOf(R.drawable._26, stringResource(R.string.salad), "32cm", "900gr")
        27 -> listOf(R.drawable._27, stringResource(R.string.cauliflower), "33cm", "1kg")
        28 -> listOf(R.drawable._28, stringResource(R.string.eggplant), "34cm", "1,2kg")
        29 -> listOf(R.drawable._29, stringResource(R.string.stalk_of_celery), "36cm", "1,3kg")
        30 -> listOf(R.drawable._30, stringResource(R.string.green_cabbage), "38cm", "1,5kg")
        31 -> listOf(R.drawable._31, stringResource(R.string.coconut), "40cm", "1,7kg")
        32 -> listOf(R.drawable._32, stringResource(R.string.butternut), "41cm", "2kg")
        33 -> listOf(R.drawable._33, stringResource(R.string.bunch_of_leeks), "42cm", "2,1kg")
        34 -> listOf(R.drawable._34, stringResource(R.string.pineapple), "43cm", "2,3kg")
        35 -> listOf(R.drawable._35, stringResource(R.string.swiss_chard), "45cm", "2,4kg")
        36 -> listOf(R.drawable._36, stringResource(R.string.chinese_cabbage), "46cm", "2,6kg")
        37 -> listOf(R.drawable._37, stringResource(R.string.winter_melon), "48cm", "2,8kg")
        38 -> listOf(R.drawable._38, stringResource(R.string.watermelon), "50cm", "3,2kg")
        39 -> listOf(R.drawable._39, stringResource(R.string.pumpkin), "50-52cm", "3,3-3,5kg")
        else -> listOf(R.drawable._40, stringResource(R.string.pumpkin), "50 - 52cm", "3,3 - 3,5kg")
    }

    val images = listOf(
        R.drawable._2,
        R.drawable._3,
        R.drawable._4,
        R.drawable._5,
        R.drawable._6,
        R.drawable._7,
        R.drawable._8,
        R.drawable._9,
        R.drawable._10,
        R.drawable._11,
        R.drawable._12,
        R.drawable._13,
        R.drawable._14,
        R.drawable._15,
        R.drawable._16,
        R.drawable._17,
        R.drawable._18,
        R.drawable._19,
        R.drawable._20,
        R.drawable._21,
        R.drawable._22,
        R.drawable._23,
        R.drawable._24,
        R.drawable._25,
        R.drawable._26,
        R.drawable._27,
        R.drawable._28,
        R.drawable._29,
        R.drawable._30,
        R.drawable._31,
        R.drawable._32,
        R.drawable._33,
        R.drawable._34,
        R.drawable._35,
        R.drawable._36,
        R.drawable._37,
        R.drawable._38,
        R.drawable._39,
        R.drawable._40,
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Row(
                Modifier
                    .fillMaxWidth(.95f)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp, start = 40.dp, end = 40.dp)
            ) {
                LinearProgressIndicator(
                    progress = ((pagerState.currentPage + 2).toFloat() / 40),
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(2.dp)
                        )
                        .fillMaxWidth(),
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
            HorizontalPager(
                pageCount = 39,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 32.dp)
            ) { page ->
                MesurePagerItemScreen(
                    image = images[page],
                    name = weeksInfos.get(1) as String,
                    size = weeksInfos.get(2) as String,
                    weight = weeksInfos.get(3) as String,
                    SGcount = pagerState.currentPage + 2
                )
            }
            LaunchedEffect(key1 = pagerState) {
                pagerState.scrollToPage(SGcount.toInt() - 2)
            }
        }
    }
}
