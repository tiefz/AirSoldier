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
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.insertkoin.airsoldier.R
import br.com.insertkoin.airsoldier.data.models.Game
import br.com.insertkoin.airsoldier.data.models.Round
import br.com.insertkoin.airsoldier.ui.home.HomeViewModel

@Composable
fun RoundScreen(
    gameId: Int,
    finishGame: (Game) -> Unit
) {
    val homeScreenViewModel = hiltViewModel<HomeViewModel>()
    var currentGame = homeScreenViewModel.currentGame.observeAsState()
    val roundList = homeScreenViewModel.roundList.observeAsState()
    if (gameId > 0) {
        homeScreenViewModel.getGame(gameId)
    } else {
        val gameList = homeScreenViewModel.gameList.observeAsState()
        if (!gameList.value.isNullOrEmpty()) {
            gameList.value?.forEach {
                if (!it.isFinished) {
                    homeScreenViewModel.getGame(it.id)
                }
            }
        }
    }
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
            val loadOutList = listOf("Sniper", "Assalt", "DMR", "Support")
            var selectedText by remember { mutableStateOf(loadOutList.first()) }
            var textFieldSize by remember { mutableStateOf(Size.Zero) }
            val icon =
                if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
            Column(verticalArrangement = Arrangement.SpaceAround) {
                Text(text = "SAFE ZONE", style = MaterialTheme.typography.h3)
                Text(text = "Game:")
                Text(text = currentGame.value?.name.toString())
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
                    loadOutList.forEach { label ->
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
                    onClick = { homeScreenViewModel.startRound(3) },
                    shape = CircleShape,
                    modifier = Modifier.size(100.dp),
                    border = BorderStroke(5.dp, color = MaterialTheme.colors.error),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                }
                Button(onClick = {
                    currentGame.value?.let { game ->
                        game.isFinished = true
                        finishGame(
                            game
                        )
                    }
                }) {
                    Text(text = "Encerrar o jogo")
                }
            }
        }
    }
}

@Composable
fun roundListItem(
    modifier: Modifier = Modifier,
    round: Round
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "#${round.id}")
        Text(text = "Abates \uD83D\uDC80: ${round.kills}")
        Text(text = "Sniper")
        //icone caveira X
        //icone trofeu
        //icone editar
        //icone deletar
    }
}

@Preview(showBackground = true)
@Composable
fun roundListItemPreview() {
    roundListItem(
        modifier = Modifier, Round(
            id = 0,
            kills = 3,
            isDead = true,
            isTeamWinner = true,
            loadOut = 3,
            isFinished = false
        )
    )
}