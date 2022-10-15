package br.com.insertkoin.airsoldier.ui.navigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.insertkoin.airsoldier.R
import br.com.insertkoin.airsoldier.ui.theme.AirSoldierTheme
import br.com.insertkoin.airsoldier.ui.user.EditUserProfilePicture
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    saveButton: (String) -> Unit,
    userPicture: String,
    userName: String,
    updateUserPicture: (String) -> Unit
) {

    var name by rememberSaveable { mutableStateOf(userName) }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Surface {
            Row(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            focusManager.clearFocus()
                            saveButton(name)
                            scaffoldState.snackbarHostState.showSnackbar("Nome alterado!")
                        }
                    },
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 15.dp,
                        disabledElevation = 0.dp
                    )
                ) {
                    Text(text = stringResource(R.string.save_button))
                }
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 48.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                Text(
                    text = stringResource(R.string.edit_profile_title),
                    style = MaterialTheme.typography.h4
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                EditUserProfilePicture(userPicture, updateUserPicture = updateUserPicture)
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    singleLine = true,
                    label = { Text(text = stringResource(R.string.edit_name)) }
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    AirSoldierTheme() {
        ProfileScreen(
            saveButton = {},
            "",
            updateUserPicture = {},
            userName = "Tief"
        )
    }
}