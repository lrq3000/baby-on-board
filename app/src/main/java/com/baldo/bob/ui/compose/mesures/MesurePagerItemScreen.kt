package com.baldo.bob.ui.compose.mesures

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baldo.bob.R

@Composable
fun MesurePagerItemScreen(image: Int, name: String, size: String, weight: String, SGcount: Int) {
//fun PagerItemScreen() {

//    val image: Int = R.drawable._2
//    val name: String = "test"
//    val size: String = "testtaille"
//    val weight: String = "testpoids"
//    val SGcount: Int = 12

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier) {
            Text(
                text = "$SGcount SG",
                Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
            )
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f)
                    .padding(vertical = 16.dp)
                    .clip(
                        RoundedCornerShape(12.dp),
                    ),
                contentScale = ContentScale.Crop
            )
            Text(
                text = name,
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,

                )
            Surface(
                modifier = Modifier
                    .fillMaxWidth(.94f)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(alignment = Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.surfaceVariant,
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.padding(16.dp),
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(R.string.size),
                            fontSize = 20.sp,
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = size,
                            fontSize = 20.sp,
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(R.string.weight),
                            fontSize = 20.sp,
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = weight,
                            fontSize = 20.sp,
                        )
                    }
                }
            }
        }
    }
}