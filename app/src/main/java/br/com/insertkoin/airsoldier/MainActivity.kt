package br.com.insertkoin.airsoldier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.insertkoin.airsoldier.ui.theme.AirSoldierTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirSoldierTheme {
                AirSoldierApp()
            }
        }
    }
}

@Composable
private fun AirSoldierApp() {

}

@Preview
@Composable
fun PreviewAirSoldierApp() {
    MaterialTheme {
        AirSoldierApp()
    }
}