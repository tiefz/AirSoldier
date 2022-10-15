package br.com.insertkoin.airsoldier.ui.navigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.insertkoin.airsoldier.R
import br.com.insertkoin.airsoldier.data.models.User
import br.com.insertkoin.airsoldier.ui.home.HomeViewModel
import br.com.insertkoin.airsoldier.ui.user.CircularLevelBar
import br.com.insertkoin.airsoldier.ui.user.UserDetail

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit
) {
    val homeScreenViewModel = hiltViewModel<HomeViewModel>()
    homeScreenViewModel.getUser()
    val userData = homeScreenViewModel.user.observeAsState()
    val user = userData.value ?: User(
        id = 1,
        name = "Usuário",
        experience = 0,
        level = 1,
        picture = ""
    )
    var gameName by rememberSaveable { mutableStateOf("") }
    Column(
        modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Iniciar um novo jogo",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.height(16.dp))
        OutlinedTextField(
            value = gameName,
            singleLine = true,
            onValueChange = { gameName = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colors.primary
            ),
            label = {
                Text(
                    text = "Nome do jogo",
                    style = TextStyle(
                        color = MaterialTheme.colors.primary
                    )
                )
            }
        )
        Spacer(modifier = modifier.height(16.dp))
        Button(
            onClick = onStartClick,
            enabled = gameName.isEmpty().not(),
            elevation = ButtonDefaults.elevation(8.dp),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            Text(text = "INICIAR", style = MaterialTheme.typography.h4)
        }
        Spacer(modifier = modifier.height(16.dp))
        Surface(modifier = Modifier.padding(horizontal = 16.dp)) {
            Column(
                modifier = modifier
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Statistics(modifier = modifier, userData = user)
            }
        }
    }
}

@Composable
fun Statistics(modifier: Modifier, userData: User) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        UserDetail(user = userData)
        CircularLevelBar(
            percentage = 0.85f,
            level = 3,
            fontSize = 28.sp,
            radius = 30.dp,
            strokeWidth = 8.dp
        )
    }
    StatisticsTextField(modifier = modifier)
}

@Composable
fun StatisticsTextField(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.statistics_title),
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = modifier.height(16.dp))
        Text(text = "Jogos: 10")
        Text(text = "Tempo total de jogos: 06h:10m")
        Row(
        ) {
            Text(
                text = "Vitórias: 7",
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(text = "Derrotas: 3")
        }
        Row {
            Text(
                text = "Eliminações: 13",
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(text = "Mortes: 4")
        }
    }
}