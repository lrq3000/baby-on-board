package com.example.bob.ui.elements

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
import androidx.compose.ui.unit.dp
import com.example.bob.R
import com.example.bob.ui.viewModel.BobUiState
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MesureScreen(bobUiState: BobUiState) {

    val lastPeriodDate = if (bobUiState.userLastPeriodsDate !== "") {
        LocalDate.parse(bobUiState.userLastPeriodsDate)
    } else {
        LocalDate.now()
    }

    val userLastOvulationDate: String? = bobUiState.userLastOvulationDate
    val ovulationDate =
        if (userLastOvulationDate == null || userLastOvulationDate == "null" || userLastOvulationDate == "") {
            lastPeriodDate.plusWeeks(2)
        } else {
            LocalDate.parse(userLastOvulationDate)
        }

    val SGcount = ChronoUnit.DAYS.between(ovulationDate, LocalDate.now()) / 7
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val weeksInfos = when (pagerState.currentPage + 2) {
        2 -> listOf(R.drawable._2, "Un retard.. ?", "0,2mm", "/")
        3 -> listOf(R.drawable._3, "Une graine de pavot", "1mm", "/")
        4 -> listOf(R.drawable._4, "Une graine de sésame", "4mm", "/")
        5 -> listOf(R.drawable._5, "Une lentille", "6mm", "1gr")
        6 -> listOf(R.drawable._6, "Un petit pois", "1,5cm", "1,5gr")
        7 -> listOf(R.drawable._7, "Un pois chiche", "2cm", "2gr")
        8 -> listOf(R.drawable._8, "Un grain de raisin", "3cm", "3gr")
        9 -> listOf(R.drawable._9, "Une noix", "4,5cm", "10gr")
        10 -> listOf(R.drawable._10, "Une prune", "7,5cm", "7,5cm")
        11 -> listOf(R.drawable._11, "Un citron vert", "8,5cm", "30gr")
        12 -> listOf(R.drawable._12, "Un citron", "10cm", "45gr")
        13 -> listOf(R.drawable._13, "Une orange", "12cm", "65gr")
        14 -> listOf(R.drawable._14, "Une nectarine", "13cm", "110gr")
        15 -> listOf(R.drawable._15, "Une pomme", "15cm", "140gr")
        16 -> listOf(R.drawable._16, "Un avocat", "17cm", "160gr")
        17 -> listOf(R.drawable._17, "Une poire", "19cm", "200gr")
        18 -> listOf(R.drawable._18, "Un poivron", "20cm", "250gr")
        19 -> listOf(R.drawable._19, "Une grosse tomate", "21cm", "350gr")
        20 -> listOf(R.drawable._20, "Un artichaut", "23cm", "400gr")
        21 -> listOf(R.drawable._21, "Une papaye", "25cm", "450gr")
        22 -> listOf(R.drawable._22, "Un pamplemousse", "26cm", "500gr")
        23 -> listOf(R.drawable._23, "Une mangue", "28cm", "600gr")
        24 -> listOf(R.drawable._24, "Un épi de maïs", "30cm", "650gr")
        25 -> listOf(R.drawable._25, "Un melon", "31cm", "750gr")
        26 -> listOf(R.drawable._26, "Une salade", "32cm", "900gr")
        27 -> listOf(R.drawable._27, "Un chou fleur", "33cm", "1kg")
        28 -> listOf(R.drawable._28, "Une aubergine", "34cm", "1,2kg")
        29 -> listOf(R.drawable._29, "Une botte de céleri", "36cm", "1,3kg")
        30 -> listOf(R.drawable._30, "Un chou vert", "38cm", "1,5kg")
        31 -> listOf(R.drawable._31, "Une noix de coco", "40cm", "1,7kg")
        32 -> listOf(R.drawable._32, "Un butternut", "41cm", "2kg")
        33 -> listOf(R.drawable._33, "Une botte de poireaux", "42cm", "2,1kg")
        34 -> listOf(R.drawable._34, "Un ananas", "43cm", "2,3kg")
        35 -> listOf(R.drawable._35, "Une blette", "45cm", "2,4kg")
        36 -> listOf(R.drawable._36, "Un chou chinois", "46cm", "2,6kg")
        37 -> listOf(R.drawable._37, "Une courge cireuse", "48cm", "2,8kg")
        38 -> listOf(R.drawable._38, "Une pastèque", "50cm", "3,2kg")
        39 -> listOf(R.drawable._39, "Une citrouille", "50-52cm", "3,3-3,5kg")
        else -> listOf(R.drawable._40, "Une citrouille", "50 - 52cm", "3,3 - 3,5kg")
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
