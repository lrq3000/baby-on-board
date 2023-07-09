package com.baldo.bob.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.baldo.bob.R

@Composable
@Preview(showBackground = true)
fun WelcomeScreen(onButtonStartClick: () -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.welcome_on_BoB, "👋"), fontSize = 28.sp)
        Column(
            Modifier
                .fillMaxWidth(.8f)
                .clip(RoundedCornerShape(8.dp)),
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.BoB_support),
                        textAlign = TextAlign.Justify,
                    )
                    Text(
                        text = stringResource(R.string.BoB_protect, "\uD83D\uDC23"),
                        textAlign = TextAlign.Justify,
                    )
                }
                Text(
                    text = stringResource(R.string.BoB_how),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
                Text(
                    text = stringResource(R.string.BoB_ecolo, "\uD83D\uDE0D"),
                    fontStyle = FontStyle.Italic,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            }
        }
        Button(onClick = { onButtonStartClick() }) {
            Text(text = stringResource(R.string.start))
        }
        Text(
            text = stringResource(R.string.BoB_gafam, "\uD83D\uDE31"),
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Justify,
            fontSize = 12.sp,
            lineHeight = 1.5.em,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(.8f)
        )
    }
}