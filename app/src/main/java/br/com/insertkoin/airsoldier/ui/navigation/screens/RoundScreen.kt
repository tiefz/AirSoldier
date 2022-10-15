package br.com.insertkoin.airsoldier.ui.navigation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.insertkoin.airsoldier.R
import br.com.insertkoin.airsoldier.ui.theme.AirSoldierTheme

@Composable
fun RoundScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.desert_camo),
                contentScale = ContentScale.FillBounds
            ),
        contentAlignment = Alignment.Center
    ) {
        Surface {
            Text(text = "SAFE ZONE", style = MaterialTheme.typography.h3)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoundPreview() {
    AirSoldierTheme {
        RoundScreen()
    }
}