package com.example.bob.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.sp
import com.example.bob.R

@Composable
@Preview(showBackground = true)
fun WelcomeScreen(onButtonStartClick: () -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.welcome_on_BoB), fontSize = 32.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Surface(
            Modifier
                .fillMaxWidth(.8f)
                .clip(RoundedCornerShape(8.dp)),
        ) {
            Column() {
                Text(
                    text = "Baby on Board vous accompagne tout au long de votre grossesse et jusqu'a votre accouchement.",
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
                Text(
                    text = "Son défi ? Le faire en protégeant votre vie privée et celle de votre bébé \uD83D\uDC23.",
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
                Text(
                    text = "Comment ? BoB ne recolte aucune de vos données. Elles sont toutes stockées localement sur votre téléphone et ne sont accessibles pour personne.",
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(16.dp)
                )

                Text(
                    text = "Petit bonus: c'est aussi beaucoup plus écolo \uD83D\uDE0D !",
                    fontStyle = FontStyle.Italic,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }
        Button(onClick = { onButtonStartClick() }) {
            Text(text = stringResource(R.string.start))
        }
        Text(
            text = "PS: Savez-vous que toutes les applications de suivi de grossesse transmettent leurs données aux GAFAM qui créent un profil psychologique de votre bébé avant même sa naissance ?",
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Justify,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(.8f)
        )
    }
}