package br.com.insertkoin.airsoldier.ui.navigation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
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
        contentAlignment = Alignment.Center,
    ) {
        Surface {
            var expanded by remember { mutableStateOf(false) }
            val loadoutList = listOf("Sniper", "Assalt", "DMR", "Support")
            var selectedText by remember { mutableStateOf(loadoutList.first()) }
            var textFieldSize by remember { mutableStateOf(Size.Zero) }
            val icon =
                if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
            Column(verticalArrangement = Arrangement.SpaceAround) {
                Text(text = "SAFE ZONE", style = MaterialTheme.typography.h3)
                Text(text = "Game:")
                Text(text = "Alcatraz")
                Text(text = "Loadout Selecionado:")
                OutlinedTextField(
                    value = selectedText,
                    onValueChange = { selectedText = it },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { cordinates ->
                            textFieldSize = cordinates.size.toSize()
                        },
                    trailingIcon = {
                        Icon(icon, "Description", Modifier.clickable { expanded = !expanded })
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                ) {
                    loadoutList.forEach { label ->
                        DropdownMenuItem(onClick = {
                            selectedText = label
                            expanded = false
                        }) {
                            Text(text = label)
                        }
                    }
                }
                Text(text = "Ir para o combate!")
                Button(
                    onClick = { /*TODO*/ },
                    shape = CircleShape,
                    modifier = Modifier.size(100.dp),
                    border = BorderStroke(5.dp, color = MaterialTheme.colors.error),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                }
            }
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